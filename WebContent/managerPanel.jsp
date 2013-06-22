<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--  insert head  -->
<%@include file="head.jsp"%>


<%!User userInfo;%>
<%
	userInfo = (User) ((HttpSession) session)
			.getAttribute(Constants.USER_INFO_SESSION_ATTR_NAME);
%>
<!--  insert header  -->
<%
	if (userInfo != null) {
%>
<%@include file="WEB-INF/loggedHeader.jsp"%>
<%
	} else {
%>
<%@include file="WEB-INF/header.jsp"%>

<%
	}
%>
<body>x	
<%
	if (userInfo != null && user.getRole().equals(UserRole.MANAGER)) {
%>
<%@include file="WEB-INF/managerOptions.jsp"%>
<%
	} else {
%>
<%-- <!--//<%@include // file="WEB-INF/notAllowed.jsp"%>--> --%>

<%
	}
%>


</body>
</html>