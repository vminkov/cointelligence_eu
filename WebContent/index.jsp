<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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


<body>
	<div id="wrapper">

		<div id="tablesPart">
			<!--  insert statements table -->
			<%@include file="WEB-INF/statementsTable.jsp"%>

			<!--  insert rankings table -->
			<%@include file="WEB-INF/rankingsTable.jsp"%>

			<!-- end of first section-->


		</div>
	</div>
	<%@include file="WEB-INF/foot.jsp"%>
	<script src="scripts/scripts.js"></script>
	
</body>

</html>