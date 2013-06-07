<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="questionWrapper" class="span4">

	<a href="#modalQuestion" role="button" data-toggle="modal"> <img
		src="img/question.png" width="400" /></a>

	<div id="modalQuestion" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="H2">Задай въпрос!</h3>
		</div>
		<form class="form-horizontal" action="AskQuestionServlet" method="post" accept-charset="UTF-8">
			<div class="modal-body">
				<div class="control-group">
					<label class="control-label" for="headline">Заглавие на
						въпроса</label>
					<div class="controls">
						<input type="text" class="headlineQuestion" id="headline"
							placeholder="short headline" name="title" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="description">Описание на
						въпроса</label>
					<div class="controls">
						<textarea id="description" placeholder="description"
							name="description"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label for="date" class="control-label">Дата на падеж</label>
					<div class="controls">

						<input type="datetime-local" id="date" name="duedate" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Затвори</button>
				<input class="btn btn-primary" value="Задай въпроса!" type="submit" />
			</div>
		</form>
	</div>
</div>




