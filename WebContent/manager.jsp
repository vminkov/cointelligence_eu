<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--  insert head  -->
<%@include file="head.jsp"%>
<!--  insert header  -->
<%@include file="WEB-INF/loggedHeader.jsp"%>

<body>
	<div id="wrapper">

		<div id="tablesPart">
			<%@include file="WEB-INF/questions.jsp"%>
			<%@include file="WEB-INF/statementsTable.jsp"%>
		</div>
	</div>
	<%@include file="WEB-INF/foot.jsp"%>
	<script src="scripts/scripts.js"></script>

</body>

</html>