<%@page import="eu.cointelligence.model.User"%>
<%@page import="eu.cointelligence.controller.Constants"%>
        <header>
            <nav id="mainNav">
                <ul id="mainUl" class="nav nav-tabs">
                    <li class="active"><a href="#">Начало</a></li>
                    <li><a href="manager.jsp">Мениджър</a></li>
                    <li><a href="employee.jsp">Служител</a></li>
                    <li><a href="#">Профил</a></li>
                    <li><a href="#">Портфолио</a></li>
                    <li><a href="#">За нас</a></li>
                </ul>
                <div id="signButtons" class="btn-group">
                    <a href="#regForm" role="button" class="btn" data-toggle="modal">Регистрирай се!</a>
                    <a href="#enterForm" role="button" class="btn" data-toggle="modal">Влез</a>
                </div>
                <!-- modal sign up -->
                <div id="regForm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Регистрирай се!</h3>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" action="registerServlet" method="post">
                            <div class="control-group">
                                <label class="control-label" for="inputNickname">Псевдоним</label>
                                <div class="controls">
                                    <input type="text" id="inputNickname" placeholder="nickname" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="inputName">Трите имена</label>
                                <div class="controls">
                                    <input type="text" id="inputName" placeholder="full name" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="inputEmail">Email</label>
                                <div class="controls">
                                    <input type="text" id="inputEmail" placeholder="email" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="inputPassword">Парола</label>
                                <div class="controls">
                                    <input type="password" id="inputPassword" placeholder="password" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="department">Отдел</label>
                                <div class="controls">
                                    <select id="department">
                                        <option value="1">Финансов отдел</option>
                                        <option>Маркетингов отдел</option>
                                        <option>Човешки ресурси</option>
                                        <option>Продажби</option>
                                        <option>Поддръжка</option>
                                    </select>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="male">Пол</label>
                                <div class="controls">
                                    <label class="radio">
                                        <input type="radio" name="optionsRadios" id="male" value="мъж" />
                                        Мъж
                                    </label>
                                    <label class="radio">
                                        <input type="radio" name="optionsRadios" id="female" value="мъж" />
                                        Жена
                                    </label>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Затвори</button>
                        <button class="btn btn-primary">Регистрирай се!</button>
                    </div>
                </div>
                <!-- modal sign ip -->
                <!-- Modal -->
                <div id="enterForm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="H1">Влез!</h3>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="inputNickname">Псевдоним</label>
                                <div class="controls">
                                    <input type="text" id="Text1" placeholder="nickname" />
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="pass">Парола</label>
                                <div class="controls">
                                    <input type="text" id="pass" placeholder="password" />
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <label class="checkbox">
                                        <input type="checkbox" />Запомни ме!</label>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Затвори</button>
                        <button class="btn btn-primary">Влез!</button>
                    </div>
                </div>
            </nav>
            <h1>
                <img src="img/koki-logo.png" width="170" height="90" alt="koki-logo" id="logo" /></h1>
        </header>