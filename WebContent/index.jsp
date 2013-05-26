
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--  insert head  -->
<%@include file="head.jsp"%>
<!--  insert header  -->
<%@include file="WEB-INF/loggedHeader.jsp"%>

<body>
	<div id="wrapper">

		<!-- insert submit questions and ideas .jsp -->
		<%@include file="WEB-INF/questionsAndIdeas.jsp"%>
		<div id="tablesPart">
			<!--  insert statements table -->
			<%@include file="WEB-INF/statementsTable.jsp"%>

			<!--  insert rankings table -->
			<%@include file="WEB-INF/rankingsTable.jsp"%>

			<!-- end of first section-->


		</div>
	</div>
</body>

</html>