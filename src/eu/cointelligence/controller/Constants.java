package eu.cointelligence.controller;

public class Constants {
	public static String URI_ROOT = "/cointelligence_eu";
	
	public static final Long DEFAULT_PRICE = new Long(50);
	public static final Long DEFAULT_COINTELS = new Long(2000);
	public static final String USERNAME_REQUEST_PARAM_NAME = "username";
	public static final String PASSWORD_REQUEST_PARAM_NAME = "password";
	public static final String USER_INFO_SESSION_ATTR_NAME = "_user_info_session";
	public static final String MESSAGE_REQUEST_ATTR_NAME = "_message";

	public static final String MAIN_PAGE = "/index.jsp";
	public static final String LOGIN_PAGE = "/login.jsp";
	public static final String LOGOUT_PAGE = "/logout.jsp";
	public static final String INVALID_AUTHENTICATION_PAGE = "/loginfail.jsp";
	
	public static final String AUTHENTICATION_FILTER_EXCLUDE_URI_REGEX_PARAM_NAME = "exclude-uri-regex";

	public static final String RELEASE_INIT_PARAM = "release";

	public static final Long MAX_PRICE_FOR_A_STATMENT = new Long(100);

	public static final String QUESTION_TITLE_REQUEST_PARAM_NAME = "title";

	public static final String QUESTION_DESCR_REQUEST_PARAM_NAME = "description";

	public static final String QUESTION_VOTE_REQUEST_PARAM_NAME = "voteStarted";

	public static final String QUESTION_DUEDATE_REQUEST_PARAM_NAME = "duedate";

	public static final String QUESTION_START_PRICE_REQUEST_PARAM_NAME = "startPrice";
}