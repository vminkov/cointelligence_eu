  function customJS() {

	//adding sortBy to the prototype of the array
	  function dynamicSort(property) {
	      var sortOrder = -1;
	      if (property[0] === "-") {
	          sortOrder = -1;
	          property = property.substr(1, property.length - 1);
	      }
	      return function (a, b) {
	          var result = (a[property] < b[property]) ? -1 : (a[property] > b[property]) ? 1 : 0;
	          return result * sortOrder;
	      };
	  }
	  Array.prototype.sortBy = function (property) {
	      return this.sort(dynamicSort(property))
	  };
	  
    var paths = {};
    paths.stetements = 'rest/statements';
    paths.users = 'rest/users/ranking'; 
//    counter = 0;
//    function nalqvo(){
//    	counter--;
//    }
//    function nadqsno()	{
//    	conter++;
//    }
//    
//    
//    for(i = 0 ; i<7; i++){
//    	index = (counterPages % 4) *7 + i;
//    	obj = data[index];
//    }
    var readQuestions = $.getJSON(paths.stetements, function (data) {
        data.sortBy("currentValue");
        $.each(data, function (index, obj) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='titleCol questionCol'> " + obj.title + "</td>");
            row.append("<td class='descCol questionCol'><i class='icon-info-sign'></i>" + obj.description + "</td>");
            row.append("<td class='priceCol questionCol'><p class='btn btn-info customSellButton'>" + obj.currentValue + "</p></td>");
            row.append("<td class='priceCol questionCol'> <a class='btn btn-success customSellButton' href='#statement" + obj.id + "' role='button' data-toggle='modal'>" + "Търгувай</a></td>");
            $('#statementsInfo').append(row);
            var modalQuestion = document.createElement("div");
            setModalProperties(modalQuestion,obj);
            $('#wrapper').append(modalQuestion);
            var button = document.getElementById("sendFormButton" + obj.id);
            button.onclick = function () {
                var newObj = obj;
                usersTest(newObj,event);
            };
            var quontititySelector = document.getElementsByClassName("selectQuontity");
            for (var i = 0; i < quontititySelector.length; i++) {
                quontititySelector[i].onclick = function () {
                    showStetementQuontity(event);
                };
            }

            var yesQuontity = document.getElementById("yesQuontity" + obj.id);

            yesQuontity.onkeyup = function () {
                var question = obj;
                showPotentialWinAndLost(event, question);
            }

            var noQuontity = document.getElementById("noQuontity" + obj.id);
            noQuontity.onkeyup = function () {
                var question = obj;
                showPotentialWinAndLost(event, question);
            }
            var shortSale = document.getElementById("shortSale" + obj.id);
            shortSale.onkeyup = function () {
                var question = obj;
                showPotentialWinAndLost(event, question);
            }


        });
    }).fail(function () {
        console.log("fail");
    });

    var readPlayersRanking = $.getJSON(paths.users, function (data) {
      data.sortBy("total");
        $.each(data, function (index, obj) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='nicknameCol questionCol'><i class='icon-user'></i>" + obj.username + "</td>");
            row.append("<td class='priceCol questionCol'>" + obj.cointels + "</td>");
            row.append("<td class='priceCol questionCol'>" + obj.total + "</td>");
            $('#rankTable').append(row);
        });
    }).fail(function () {
        console.log("fail");
    });

    (function activeNavButton() {
        var nav = document.getElementById("mainUl");
        var navElements = nav.children;
        function onNavItemClick(event) {
            event.stopPropagation();
            //event.preventDefault();
            for (var i = 0; i < navElements.length; i++) {
                navElements[i].className = "";
            }
            var currentElement = event.target;
            currentElement.parentNode.className = "active";
        }

        for (var i = 0; i < navElements.length; i++) {
            navElements[i].addEventListener("click", onNavItemClick, false)
        }
    }());

    (function subscribeEventsInformationSend() {
        var regButtons = document.getElementsByClassName("regButton");
        for (var i = 0; i < regButtons.length; i++) {
            regButtons[i].addEventListener("click", function () { sendTheForm(); }, false);
        }
        function sendTheForm() {

            var data = {};
            var regForm = {};

            var time = new Date();
            var timestamp = "";
            timestamp += time.getHours();
            timestamp += time.getMinutes();

            regForm.username = document.getElementById("inputNickname").value;
            regForm.fullname = document.getElementById("inputName").value;
            regForm.password = document.getElementById("inputPassword").value;
            regForm.email = document.getElementById("inputEmail").value;

            var department = document.getElementById("department");
            regForm.department = department.options[department.selectedIndex].text;;
            regForm.gender = $('input[name=optionsRadios]:checked').val();
            
            
            data.regForm = JSON.stringify(regForm);

            url = "http://localhost:8080/cointelligence_eu/rest/login/register";

            function usersTest() {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": data
                }).success(function (data) {
                	console.log(data);
                });
            }
        }

        var enterButtons = document.getElementsByClassName("enterButton");
        for (var i = 0; i < enterButtons.length; i++) {
            enterButtons[i].addEventListener("click", function () { enterTheSite()}, false);
        }

        function enterTheSite() {
           var data = {};
           var regForm = {};

           var time = new Date();
           var timestamp = "";
            timestamp += time.getHours();
            timestamp += time.getMinutes();

            regForm.username = document.getElementById("nickname").value;
          
            regForm.password = document.getElementById("pass").value;

            data.regForm = JSON.stringify(regForm);

            url = "http://localhost:8080/cointelligence_eu/rest/login/register";

            function usersTest() {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": data
                }).success(function (data) {
                    console.log(data)
                });
            }
        }

        var askQuestionButton = document.getElementById("askQuestion");
            askQuestionButton.addEventListener("click", function () { askTheQuestion();}, false);
        function askTheQuestion() {
            var data = {};
            var questionForm = {};

            questionForm.headline = document.getElementById("headline").value
            questionForm.description = document.getElementById("description").value;
            questionForm.date = document.getElementById("date").value;
            data.questionForm = JSON.stringify(questionForm);

            url = "http://localhost:8080/cointelligence_eu/rest/login/register";

            function usersTest() {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": data
                }).success(function (data) {
                    console.log(data)
                });
            }

        }

        var putIdeaButton = document.getElementById("putIdea");
        putIdeaButton.addEventListener("click", function () { putTheIdea(); }, false);
        function putTheIdea() {
            var data = {};
            var questionForm = {};

            questionForm.headline = document.getElementById("ideaHeadline").value
            questionForm.description = document.getElementById("ideaDescription").value;
            data.questionForm = JSON.stringify(questionForm);

            url = "http://localhost:8080/cointelligence_eu/rest/login/register";

            function usersTest() {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": data
                }).success(function (data) {
                    console.log(data)
                });
            }

        }
    }());

    function setModalProperties(modalQuestion, obj) {
        modalQuestion.id = "statement" + obj.id;
        modalQuestion.className = "modal hide fade";
        modalQuestion.tabIndex = "-1";
        modalQuestion.setAttribute("role", "dialog");
        modalQuestion.setAttribute("aria-labelledby", "myModalLabel");
        modalQuestion.setAttribute("aria-hidden", "true");
        modalQuestion.innerHTML = setQuestionsInnerHTML(obj);
    }

    function setQuestionsInnerHTML(obj) {
        return "<div class='modal-header'>" +
                "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>" +
                "<h3 id='H2'>Търгувай с въпроса!</h3>" +
                "</div>" +
                "<div class='modal-body'>" +
                "<form class='form-horizontal'>" +
                "<div class='control-group'>" +
                "<label class='control-label'>Заглавие на въпроса</label>" +
                "<div class='controls'>" +
                "<p>" + obj.title + "<p/>" +
                "</div>" +
                "</div>" +
                "<div class='control-group'>" +
                "<label class='control-label'>Описание на въпроса</label>" +
                "<div class='controls'>" +
                "<p>" + obj.description + "<p/>" +
                "</div>" +
                "</div>" +
                 "<div class='control-group'>" +
                "<label class='control-label'>Текуща цена на въпроса:</label>" +
                "<div class='controls'>" +
                "<p class='btn btn-success' id='currentvalue"+obj.id+"'>" + obj.currentValue + " cointels за акция" + "</p>" +
                "</div>" +
                "</div>" +
                "<div class='control-group'>" +
                "<label class='control-label'>Избери твърдерние с определена цена:</label>" +
                "<div class='controls stetementContainer'>" +
                "<label><i class='icon-hand-up'></i>" +
                "<input type='radio' id='yesStetement" + obj.id + "' class='selectQuontity' name='stetement' value='buy' '> Да, ще се случи и искам да купя:" +
                "<input type='text' id='yesQuontity" + obj.id + "' class='quontityField'  style='width:50px;display:none' /></label><br/><br/>" +
                "<label><i class='icon-hand-down'></i>" +
                 "<input type='radio'id='noStetement" + obj.id + "' class='selectQuontity' name='stetement' value='sell'> Не,няма да се случи и искам да купя:" +
                 "<input type='text' id='noQuontity" + obj.id + "' class='quontityField' style='width:50px;display:none'/></label><br/>" +
                "<label><i class='icon-hand-down'></i>" +
                 "<input type='radio'id='shortSaleStetement" + obj.id + "' class='selectQuontity' name='stetement' value='shortSell'>Продавам за определено време, като се задължавам да си го купя обратно, максимум след два дни!" +
                 "<input type='text' id='shortSale"+ obj.id + "' class='quontityField' style='width:50px;display:none'/></label><br/>" +
                "</div>" +
                "</div>" +
                 "<div class='control-group'>" +
                "<label class='control-label'>Възможна печалба и загуба:</label>" +
                "<div class='controls'>" +
                "<span style='margin-right:10px' class='btn btn-success optional-price" + obj.id + "' id=win" + obj.id + ">" + 0 + " печалба" + "</span>" +
                "<span class='btn btn-danger optional-price" + obj.id + "' id=loss" + obj.id + ">" + 0 + " загуба" + "</span></br></br>" +
                "</div>" +
                "</div>" +
                   "<div class='control-group'>" +
                "<label class='control-label'>Общ брой налични Cointels в момента:</label>" +
                "<div class='controls'>" +
                "<p class='btn btn-info'>" + 10000 + " cointels" + "</p>" +
                "</div>" +
                "</div>" +
                "</form>" +
                "</div>" +
                "<div class='modal-footer'>" +
                "<button class='btn' data-dismiss='modal' aria-hidden='true'>Затвори</button>" +
                "<button  data-dismiss='modal' aria-hidden='true'  class='btn btn-primary sendForm' id='sendFormButton" + obj.id + "'>Направи сделката!</button></div>";
    }

    function usersTest(obj,event) {
        var dealForm = {};
        var yesStatement = document.getElementById("yesStetement" + obj.id);
        var noStatement = document.getElementById("noStetement" + obj.id);
        var shortSellStatement = document.getElementById("shortSaleStetement" + obj.id);
        dealForm.statementId = obj.id;
        dealForm.currentValue = obj.currentValue;

       
        if (yesStatement.checked==true) {
            var selected = yesStatement;
            var selectedInput = yesStatement.value;
        }else if (noStatement.checked == true) {
            var selected = noStatement;
            var selectedInput = noStatement.value;
        } else if (shortSellStatement.checked == true) {
            var selected = shortSellStatement;
            var selectedInput = shortSellStatement.value;
        }
        
        
        dealForm.selectedStatement = selectedInput;// buy sell shortSell
        if (selected) {
            if (selected.nextElementSibling.value<1) {
                alert("Броя акции трябва да е положителен!")
            }
            if (!(parseInt(selected.nextElementSibling.value))) {
                alert("Броя акции трябва да е число по възможност!")
            }
            dealForm.quantity = selected.nextElementSibling.value | 0;
        }else{
            alert("Избери опция за да търгуваш с твърдението!")
        }
        dealForm.username = userrmation.username; //TODO uncomment when comit js
        
        dealForm.password = userrmation.password; //TODO uncomment when comit js

        url = "rest/trader/"+selectedInput;
        $.ajax({
            "url": url,
            "type": "POST",
            "data": dealForm
        }).success(function (data) {
            deleteOldRows();
           customJS();
        });
    }

    function showStetementQuontity(event) {
        var stementsContainer = document.getElementsByClassName("selectQuontity");
        for (var i = 0; i < stementsContainer.length; i++) {
            stementsContainer[i].nextElementSibling.style.display = "none";
        }
        var currentSelected = event.target;
        if (currentSelected.nextElementSibling.value) {
            currentSelected.nextElementSibling.value = "";
        }
        currentSelected.nextElementSibling.style.display = "inline-block";
    }

    function showPotentialWinAndLost(event,question) {

        
        var bittedValue = event.target.value;
        if (!parseFloat(bittedValue)) {
            return;
        }
        else if (parseFloat(bittedValue) < 1) {
            return;
        }

        var allPrices = document.getElementsByClassName("optional-price");
        for (var i = 0; i < allPrices.length; i++) {
            allPrices[i].innerHTML = "0";
        }

        if (event.target.id == ("yesQuontity" + question.id) || event.target.id==("noQuontity" + question.id)) {
            var potentialWinsAndLosses = document.getElementsByClassName("optional-price" + question.id);
            for (var i = 0; i < potentialWinsAndLosses.length; i++) {
                potentialWinsAndLosses[i].innerHTML = (question.currentValue * parseFloat(bittedValue));
                if (!event.target.value) {
                    potentialWinsAndLosses[i].innerHTML = "0";
                }
            }

        } else {//TODO:
            var potentialWin = document.getElementById("win" + question.id);
            potentialWin.innerHTML = "win";
            var potentialLoss = document.getElementById("loss" + question.id);
            potentialLoss.innerHTML = "loss";
        }
    }
    function deleteOldRows() {
        var questionsTable = document.getElementById("statementsInfo");
        var questionRows = questionsTable.children[0].children;
        var questionLength = questionRows.length;
        for (var i = 0; i < questionLength - 1; i++) {
            questionsTable.children[0].deleteRow();
        }

        var rankingTable = document.getElementById("rankTable");
        var rankingRows = rankingTable.children[0].children;
        var rankingLength = rankingRows.length;
        for (var i = 0; i < rankingLength - 1; i++) {
            rankingTable.children[0].deleteRow();
        }
    }

};
  customJS();








//var paths = {};
//paths.stetements = '../testJSON/testStatements.js';
//paths.users = '../testJSON/users.js';
//var test = $.getJSON(paths.stetements, function (data) {
//    console.log(data);
//    var items = [];
//    var ids = $('#id');
//    var titles = $('#title');
//    var descriptions = $('#description');
//    var prices = $('#price');
//    $.each(data, function (index, obj) {
//        $('#id').append("<p>" + obj.id + "<p>");
//        $('#title').append("<p>" + obj.title + "<p>");
//        $('#description').append("<p><a href=#>" + obj.description + "</a><p>");
//        $('#price').append("<p>" + obj.currentValue + "<p>");
//    });
//}).fail(function () {
//    console.log("fail");
//});