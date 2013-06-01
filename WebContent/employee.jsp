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

		<!-- ЗА ДА ВЛЕЗЕШ В МЕНИДЖЪРСКИЯ ПАНЕЛ НАТИСНИ ГОРЕ "МЕНИДЖЪР" -->
		<!-- ЗА ДА ВЛЕЗЕШ В СЛУЖИТЕЛСКИЯ ПАНЕЛ НАТИСНИ ГОРЕ "СЛУЖИТЕЛ" -->
		<div id="tablesPart">
			<%@include file="WEB-INF/ideas.jsp"%>
			<%@include file="WEB-INF/statementsTable.jsp"%>

			<!--  insert rankings table -->
			<%@include file="WEB-INF/rankingsTable.jsp"%>

			<!-- end of first section-->


		</div>
	</div>
			<%@include file="WEB-INF/foot.jsp"%>
</body>

</html>