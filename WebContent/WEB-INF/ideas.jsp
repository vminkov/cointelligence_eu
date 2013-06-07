<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="ideaWrapper" class="span4">
	<a href="#modalIdea" role="button" data-toggle="modal"> <img
		src="img/idea.png" width="400" /></a>

	<div id="modalIdea" class="modal hide fade " tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="H3">Сподели идея!</h3>
		</div>
		<form class="form-horizontal" method="post" action="shareIdea">
			<div class="modal-body">
				<div class="control-group">
					<label class="control-label" for="ideaHeadline">Заглавие на
						идеята</label>
					<div class="controls">
						<input type="text" class="headlineQuestion" id="ideaHeadline"
							placeholder="short headline" name="title" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="ideaDescription">Описание
						на идеята</label>
					<div class="controls">
						<textarea id="ideaDescription" placeholder="idea description"
							name="description"></textarea>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="ideaCompany">Компания</label>
					<div class="controls">
						<input class="headlineQuestion" type="text" placeholder="company"
							name="company" />
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Затвори</button>
				<input type="submit" class="btn btn-primary" value="Сподели!">
			</div>
		</form>
	</div>
</div>