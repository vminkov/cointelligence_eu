package eu.cointelligence.controller.filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.cointelligence.controller.Constants;
import eu.cointelligence.model.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {


	protected String mExcludeUriRegex;

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		if (isPathExcluded(httpServletRequest.getRequestURI())) {
			// simply pass on
			chain.doFilter(request, response);
		} else {
			HttpSession session = httpServletRequest.getSession(false);
			if (session != null) {
				User userInfo = (User) session.getAttribute(Constants.USER_INFO_SESSION_ATTR_NAME);
				if (userInfo != null) {
					// pass on the user is present
					chain.doFilter(request, response);
					return;
				}
			}
			// show the forbidden page
			request.getRequestDispatcher(Constants.LOGIN_PAGE).forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		mExcludeUriRegex = fConfig
				.getInitParameter(Constants.AUTHENTICATION_FILTER_EXCLUDE_URI_REGEX_PARAM_NAME);
	}

	protected boolean isPathExcluded(String aPath) {
		Pattern pattern = Pattern.compile(mExcludeUriRegex);
		Matcher matcher = pattern.matcher(aPath);

		if (matcher.find()) {
			return true;
		}
		return false;
	}

}
