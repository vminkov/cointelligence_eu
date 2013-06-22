<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div id="enterForm" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="false">×</button>
				<h3 id="H1">Моля, изберете действие.</h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
					<table>
						<tr>
							<td style="padding: 8px;vertical-align: center;" colspan='2'>
								<p style="font-size:20px; text-align: center;">
									<a class="info" href="./editEmployees.jsp">Редактиране на потребителските профили</a>
								</p>
							</td>
						</tr>
						<tr>
							<td style="padding: 8px;vertical-align: center;" colspan='2'>
								<p style="font-size:20px; text-align: center;">
									<a class="info" href="./editStatements.jsp">Редактиране на въпросите</a>
								</p>
							</td>
						</tr>
						<tr>
							<td style="padding: 8px;vertical-align: center;" colspan='2'>
								<p style="font-size:20px; text-align: center;">
									<a class="info" href="./editIdeas.jsp">Редактиране на идеите</a>
								</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn-primary btn" value="Затвори">
			</div>
	</div>