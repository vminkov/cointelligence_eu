<%@page import="java.util.List"%>
<%@page import="eu.cointelligence.controller.Constants"%>
<%@page import="eu.cointelligence.controller.users.UserManagerImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div id="enterForm" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="false">×</button>
				<h3 id="H1">Редактиране на потребителските профили</h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
				<% UserManagerImpl userManager = (UserManagerImpl) application.getAttribute(Constants.USER_MANAGER );
				%>
					<table class="table">
						<tr>
							<td style="padding: 8px;vertical-align: center;">
								Потребителско име
							</td>
							<td style="padding: 8px;vertical-align: center;">
								Деактивирай
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<a href="./index.jsp"><button> Начало</button></a><input type="button" class="btn-primary btn" value="Затвори">
			</div>
	</div>