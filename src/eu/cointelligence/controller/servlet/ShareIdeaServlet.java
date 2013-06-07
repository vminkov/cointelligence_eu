package eu.cointelligence.controller.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.cointelligence.controller.Constants;
import eu.cointelligence.controller.dao.IdeasDao;
import eu.cointelligence.model.Idea;
import eu.cointelligence.model.User;
import eu.cointelligence.util.JspUtils;

/**
 * Servlet implementation class ShareIdeaServlet
 */
@WebServlet("/shareIdea")
public class ShareIdeaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private static IdeasDao ideasDao;
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareIdeaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false) != null && request.getSession(false).getAttribute("ideas") == null){
			List<Idea> ideas = ideasDao.getAll();
			request.getSession(false).setAttribute("ideas", ideas);
			
			request.getRequestDispatcher(Constants.IDEAS_PAGE).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session == null){
			response.sendError(405);
			return;
		}
		User user = (User) session.getAttribute(Constants.USER_INFO_SESSION_ATTR_NAME);
		
		String password = user.getPasswordHash();
		String username = user.getUserName();
		
		if(username == null || password == null)
			return;
		
		String titleUnescaped = 	request
				.getParameter(Constants.QUESTION_TITLE_REQUEST_PARAM_NAME);
		
		String descriptionUnescaped = request
				.getParameter(Constants.QUESTION_DESCR_REQUEST_PARAM_NAME);
		
		String companyStr = request
				.getParameter(Constants.IDEA_COMPANY_REQUEST_PARAM_NAME);
		

		try { // Decode the cyrillic parameter value
			titleUnescaped = java.net.URLDecoder.decode(titleUnescaped,
					Constants.ENCODING);
		} catch (Exception e) {
			System.out.println("could not decode from utf-8!!");
			e.printStackTrace();
		}
		try { // Decode the cyrillic parameter value
			descriptionUnescaped = java.net.URLDecoder.decode(descriptionUnescaped,
					Constants.ENCODING);
		} catch (Exception e) {
			System.out.println("could not decode from utf-8!!");
			e.printStackTrace();
		}
		try { // Decode the cyrillic parameter value
			companyStr = java.net.URLDecoder.decode(companyStr,
					Constants.ENCODING);
		} catch (Exception e) {
			System.out.println("could not decode from utf-8!!");
			e.printStackTrace();
		}
		
		
		String title = JspUtils.escapeForHTML(titleUnescaped);
		String description = JspUtils.escapeForHTML(descriptionUnescaped);
		String company = JspUtils.escapeForHTML(companyStr);
		
		Idea newIdea = ideasDao.create(new Idea());
		newIdea.setUser(user);
		newIdea.setCompany(company);
		newIdea.setDescription(description);
		newIdea.setTitle(title);
		ideasDao.update(newIdea);
		
		response.sendRedirect(request.getContextPath() + Constants.IDEAS_PAGE);
	}

}
