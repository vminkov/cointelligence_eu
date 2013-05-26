 <div id="qestionBaloons">
            <div id="questionWrapper" class="span5">

                <a href="#modalQuestion" role="button" data-toggle="modal">
                    <img src="img/question.png" width="400" /></a>

                <div id="modalQuestion" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="H2">Задай въпрос!</h3>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="headline">Заглавие на въпроса</label>
                                <div class="controls">
                                    <input type="text" class="headlineQuestion" id="headline" placeholder="short headline" name="title"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="description">Описание на въпроса</label>
                                <div class="controls">
                                    <textarea id="description" placeholder="description"></textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <label for="date" class="control-label">Дата на падеж</label>
                                <div class="controls">

                                    <input type="datetime-local" id="date" />
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Затвори</button>
                        <button class="btn btn-primary">Задай въпроса!</button>
                    </div>
                </div>
            </div>



            <div id="ideaWrapper" class="span5">
                <a href="#modalIdea" role="button" data-toggle="modal">
                    <img src="img/idea.png" width="400" /></a>

                <div id="modalIdea" class="modal hide fade " tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="H3">Пусни идея!</h3>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="ideaHeadline">Заглавие на идеята</label>
                                <div class="controls">
                                    <input type="text" class="headlineQuestion" id="ideaHeadline" placeholder="short headline" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="ideaDescription">Описание на идеята</label>
                                <div class="controls">
                                    <textarea id="ideaDescription" placeholder="idea description"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Затвори</button>
                        <button class="btn btn-primary">Влез!</button>
                    </div>
                </div>
            </div>

        </div>