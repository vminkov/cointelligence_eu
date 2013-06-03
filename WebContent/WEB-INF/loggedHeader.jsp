<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="eu.cointelligence.model.User"%>
<%@page import="eu.cointelligence.controller.Constants"%>
<header>

	<%!User user;%>
	<%
		user = (User) ((HttpSession) session)
				.getAttribute(Constants.USER_INFO_SESSION_ATTR_NAME);
	%>
	<nav id="mainNav">
		<script>var userrmation = {};
				userrmation.username = "<%=user.getUserName()%>";
				userrmation.password = "<%=user.getPasswordHash()%>";
		</script>
		<ul id="mainUl" class="nav nav-tabs">
			<li class="active"><a href="#">Начало</a></li>
			<li><a href="#tablesPart">Въпроси</a></li>
			<li><a href="manager.jsp">Мениджър</a></li>
			<li><a href="employee.jsp">Служител</a></li>
			<li><a href="#">Портфолио</a></li>
			<li><a href="#">За нас</a></li>
		</ul>
		<form id="logout"
			action="<%=request.getContextPath() + Constants.LOGOUT_PAGE%>">
			<div>
				<span>Здравей, <%=user.getFullName()%></span> <input type="submit"
					class="btn btn-primary" value="Изход" />
			</div>
		</form>
	</nav>
	<h1>
		<img src="img/koki-logo.png" width="170" height="90" alt="koki-logo"
			id="logo" />
	</h1>
</header>