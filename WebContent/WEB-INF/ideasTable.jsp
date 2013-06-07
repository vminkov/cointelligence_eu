<%@page import="eu.cointelligence.controller.Constants"%>
<%@page import="eu.cointelligence.controller.dao.IdeasDao"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="java.util.List"%>
<%@page import="eu.cointelligence.model.Idea"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!List<Idea> ideas;%>
<%
	ideas = (List<Idea>) session.getAttribute(Constants.IDEAS);
	if (ideas == null) {
%> 
<jsp:forward page="/shareIdea" />
<%
	} 
%>
<div id="questionHeadline" class="span6">
	<h1 class="questionsHeader">Bсички идеи!</h1>
	<div class="scroller">
		<table id="ideas">
			<tr id="questionTableHeadline">
				<td class="statementDivs" id="tableTitle">На кратко</td>
				<td class="statementDivs" id="tableDescription">В детайли</td>
				<td class="statementDivs" id="tableDescription">Компания</td>
			</tr>
			<%
				for (Idea idea : ideas) {
			%>
			<tr>
				<td class="statementDivs"><%=idea.getTitle()%></td>
				<td class="statementDivs"><%=idea.getDescription()%></td>
				<td class="statementDivs"><%=idea.getCompany()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			session.removeAttribute(Constants.IDEAS); 
		%>
	</div>
	<script>
	(function fixIdeaTable() {
	    var title = document.getElementById("tableTitle");
	    var description = document.getElementById("tableDescription");
	    var company = document.getElementById("company");
	    company.style.width = 100 + "px";
	    description.style.width = 500 + "px";
	    $("tr:even").css("background-color", "#4D86C3");
	    $("tr:odd").css("background-color", "#465662");
	    $("tr td").css("color", "#ffffff");
	    $("tr,td").css("border", "1px solid #2D3941");
	    $("tr td").css("text-align", "center");
	    $("tr:first-of-type").css("background-color", "#DA8818");

	}());</script>
</div>