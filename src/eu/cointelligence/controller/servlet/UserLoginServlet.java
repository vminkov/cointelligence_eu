package eu.cointelligence.controller.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.cointelligence.controller.Constants;
import eu.cointelligence.controller.log.AuditLog;
import eu.cointelligence.controller.users.IUserManager;
import eu.cointelligence.controller.users.NoSuchUserException;
import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.controller.users.exceptions.UserCreationException;
import eu.cointelligence.controller.users.exceptions.UserExistsException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
    private IUserManager userManager;
	
	private AuditLog log;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	if(userManager.getAllUsers().size() == 0) {
    		try {
    			System.out.println("Created ADMIN account with user/pass: " + "admin / kokikoki");
				userManager.createNewUser("admin", "kokikoki");
			} catch (UserExistsException e) {
				System.out.println("This could never happen");
				e.printStackTrace();
			} catch (UserCreationException e) {
				System.out.println("that's bad - there is no admin account - create one from the database");
				e.printStackTrace();
			}
    	}
    	log = (AuditLog) this.getServletContext().getAttribute("auditLog");
    	if (log == null) {
    		System.out.println("AUDIT LOG NOT INITIALIZED!!");
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request
				.getParameter(Constants.USERNAME_REQUEST_PARAM_NAME);
		String password = request
				.getParameter(Constants.PASSWORD_REQUEST_PARAM_NAME);
		
		String message = null;

		try {
			if (username != null && password != null && username.length() > 0
					&& password.length() > 0) {
				try {
					User loginBean = userManager.login(username, password, UserRole.USER);
	
					if (loginBean != null) {
						// successful login
						User userInfo = new User();
						userInfo.setUserName(loginBean.getUserName());
						userInfo.setFullName(loginBean.getFullName());
						userInfo.setEmail(loginBean.getEmail());
						
						HttpSession session = request.getSession();
						session.setAttribute(Constants.USER_INFO_SESSION_ATTR_NAME,
								userInfo);
						
//						response.addCookie(new Cookie("jsession", userInfo.getPasswordHash()));
						this.log.log("login", username, true, request.getRemoteAddr());
						response.sendRedirect(this.getServletContext().getContextPath() + Constants.MAIN_PAGE);
						return;
					}
				} catch(WrongPasswordException e) {
					this.log.log("login", username, false, request.getRemoteAddr(), "wrong password");
					message = "Wrong username / password!";
				} catch (NoSuchUserException e) {
					this.log.log("login", username, false, request.getRemoteAddr(), "no such user");
					message = "Wrong username / password!";
				} catch (javax.resource.spi.SecurityException e) {
					this.log.log("login", username, false, request.getRemoteAddr(), "role violation");
					message = "You are not allowed to do this!";
				}
			}
			
			//works for forwarding only
			request.setAttribute(Constants.MESSAGE_REQUEST_ATTR_NAME, message);

			response.sendRedirect(this.getServletContext().getContextPath() + Constants.LOGIN_PAGE);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error", e);
		}
	}

}
