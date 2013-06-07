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
		<ul id="mainUl" class="nav nav-tabs">
			<li><a href="index.jsp">Начало</a></li>
			<li><a href="manager.jsp">Задай въпрос</a></li>
			<li><a href="employee.jsp">Дай идея</a></li>
			<li><a href="profile.jsp">Профил</a></li>
			<li><a href="aboutUs.jsp">За нас</a></li>
			<li><a href="tutorial.jsp">Tуториал</a></li>
		</ul>
		<form id="logout"
			action="<%=request.getContextPath() + Constants.LOGOUT_PAGE%>">
			<div>
				<table>
					<tr>
						<td>Здравей, <%=user.getFullName()%></td> <td><input type="submit"
							class="btn btn-primary" value="Изход" /></td>
					</tr>
				</table>
			</div>
		</form>
	</nav>
	<h1>
		<img src="img/koki-logo.png" width="170" height="90" alt="koki-logo"
			id="logo" />
	</h1>
</header>