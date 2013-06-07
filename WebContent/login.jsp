<%@page import="eu.cointelligence.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="eu.cointelligence.util.JspUtils"%>
<%@page import="eu.cointelligence.controller.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="styles/jquery.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<link href="styles/bootstrap.css" rel="stylesheet" />
<link href="styles/bootstrap-responsive.css" rel="stylesheet" />
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script src="scripts/widjest.js"></script>

<script src="scripts/bootstrap.js"></script>
<link href="styles/design.css" rel="stylesheet" />
</head>

<%!User userInfo;%>
<%
	userInfo = (User) ((HttpSession) session)
			.getAttribute(Constants.USER_INFO_SESSION_ATTR_NAME);
%>
<!--  insert header  -->
<%
	if (userInfo != null) {
		response.sendRedirect(request.getContextPath() + Constants.MAIN_PAGE);
	} else {
	}
%>


<body>
	<header> <nav id="mainNav">
	<ul id="mainUl" class="nav nav-tabs">
		<li class="active"><a href="#">Начало</a></li>
		<li><a href="#">Мениджър</a></li>
		<li><a href="#">Служител</a></li>
		<li><a href="#">Профил</a></li>
		<li><a href="#">Портфолио</a></li>
		<li><a href="#">За нас</a></li>
	</ul>
	</nav>
	<h1>
		<img src="img/koki-logo.png" width="170" height="90" alt="koki-logo"
			id="logo" />
	</h1>
	</header>
	<div id="enterForm" class="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<form class="form-horizontal" method="post" action="userLogin">
	<%
		String message = (String) request
				.getAttribute(Constants.MESSAGE_REQUEST_ATTR_NAME);
		if (message != null) {
	%>
	<span style="color: red;"><%=JspUtils.escapeForHTML(message)%></span>
	<%
		}
	%>
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="false">×</button>
				<h3 id="H1">Влез!</h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
					<label class="control-label" for="inputNickname">Потребителско име</label>
					<div class="controls">
						<input type="text" id="Text1" placeholder="username"
							name="username" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="pass">Парола</label>
					<div class="controls">
						<input type="password" id="pass" placeholder="password"
							name="password" />
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="checkbox"> <input type="checkbox"
							name="rememberMe" />Запомни ме!
							<input type='hidden' name='statementId' value=''>
						</label>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<input type="submit" class="btn btn-primary" value="Влез!" />
			</div>
		</form>
	</div>
</body>

</html>