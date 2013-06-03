package eu.cointelligence.controller.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.cointelligence.controller.Constants;
import eu.cointelligence.controller.ITrader;
import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.controller.entity.beans.StatementBean;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.User;

/**
 * Servlet implementation class AskQuestionServlet
 */
@WebServlet("/AskQuestionServlet")
public class AskQuestionServlet extends HttpServlet {
	@EJB
	private ITrader trader;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AskQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String title = 	request
				.getParameter(Constants.QUESTION_TITLE_REQUEST_PARAM_NAME);
		String description = request
				.getParameter(Constants.QUESTION_DESCR_REQUEST_PARAM_NAME);
		String voteStartedStr = request
				.getParameter(Constants.QUESTION_VOTE_REQUEST_PARAM_NAME);
		String dueDateStr = request
				.getParameter(Constants.QUESTION_DUEDATE_REQUEST_PARAM_NAME);
		String startingPriceStr = request
				.getParameter(Constants.QUESTION_START_PRICE_REQUEST_PARAM_NAME);

		Long startingPrice = null;
		Boolean voteStarted = null;
		Date dueDate = null;
		try {
		
			startingPrice = new Long(startingPriceStr);
			voteStarted = Boolean.valueOf(voteStartedStr);
			dueDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dueDateStr);
		} catch (Throwable e) {
			dueDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7); //+ one week
			startingPrice = new Long(Constants.DEFAULT_PRICE);
			voteStarted = true;
			e.printStackTrace();
		}
		
		
		StatementBean statement = new StatementBean();
		statement.setTitle(title);
		statement.setDescription(description);
		statement.setDuedate(dueDate);
		statement.setVoteStarted(voteStarted);
		statement.setCurrentValue(startingPrice);
		//set id?
		
		this.trader.submitStatement(username, password, statement);
		
		request.getRequestDispatcher(Constants.MAIN_PAGE).forward(request, response);
	}

}
