
  function getCookie(name) {
  var regexp = new RegExp("(?:^" + name + "|;\\s*"+ name + ")=(.*?)(?:;|$)", "g");
  var result = regexp.exec(document.cookie);
  return (result === null) ? null : result[1];
}
  

function customJS() {
	var globalUser = {};
	globalUser.username = getCookie("username");
	globalUser.token = getCookie("jsession");
	
    var paths = {};
   // paths.stetements = 'testJSON/testStatements.js'; //local
    //paths.users = 'testJSON/users.js';
    paths.stetements = 'rest/statements'; //on server
    paths.users = 'rest/users/ranking';
    paths.possession = 'rest/users/portfolio';
    
    function updateStatements()	{
    	var promise = $.ajax({
    		  dataType: "json",
    		  method : "post",
    		  url: paths.possession,
    		  data: {"username": globalUser.username, "token": globalUser.token}
    	});
		$.getJSON(paths.stetements).success( function (data) {
			promise.done(function(userData){
//				console.log(userData);
				$.each(data, function(index, statement){
					$('#currentvalue'+statement.id).text(statement.currentValue || 0 + " cointels за акция");
					$('#myCointels'+statement.id).text(userData.cointels || 0 + " cointels");
					$('#myBought'+statement.id).text("Притежавани: " + (userData.statementsInPossession[statement.id] ? userData.statementsInPossession[statement.id] : 0) + " акции");
					$('#myShort'+statement.id).text("Продадени на къса позиция: " + (userData.shortSellsInPossession[statement.id] ? userData.shortSellsInPossession[statement.id] : 0) + " акции");
					if(userData.shortSellsInPossession[statement.id]){
//						$('#noHolder'+statement.id).css("display", "none");
					}
					
					
					
					//console.log(statement.id);
				});
			});
			
		}).done(function (data) {
			//console.log(data.length);
			setTimeout(updateStatements, 3000);
		});
		
	}
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
	      return this.sort(dynamicSort(property));
	  };

    
    $.getJSON(paths.stetements, function (data) {
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
            $("#sendFormButton" + obj.id).on("click", function (event) {
                var newObj = obj;
                usersTest(newObj,event);
            });
            var quontititySelector = document.getElementsByClassName("selectQuontity");
            for (var i = 0; i < quontititySelector.length; i++) {
                quontititySelector[i].onclick = function (event) {
                        showStetementQuontity(event);
                    
                };
            }

            var yesXXXXXXY = document.getElementById("yesXXXXXXY" + obj.id);

            yesXXXXXXY.onkeyup = function (event) {
                var question = obj;
                showPotentialWinAndLost(event, question);
            };

            var noXXXXXXXY = document.getElementById("noXXXXXXXY" + obj.id);
            noXXXXXXXY.onkeyup = function (event) {
                var question = obj;
                showPotentialWinAndLost(event, question);
            };
            var shortSale = document.getElementById("shortSellY" + obj.id);
            shortSale.onkeyup = function (event) {
                var question = obj;
                showPotentialWinAndLost(event, question);
            };
            updateStatements();
//            setInterval(updateStatements,5000);

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
//        var regButtons = document.getElementsByClassName("regButton");
//        for (var i = 0; i < regButtons.length; i++) {
//            regButtons[i].addEventListener("click", function () { sendTheForm(); }, false);
//        }
//        function sendTheForm() {
//
//            var data = {};
//            var regForm = {};
//
//            regForm.username = document.getElementById("inputNickname").value;
//            regForm.fullname = document.getElementById("inputName").value;
//            regForm.password = document.getElementById("inputPassword").value;
//            regForm.email = document.getElementById("inputEmail").value;
//
//            var department = document.getElementById("department");
//            regForm.department = department.options[department.selectedIndex].text;;
//            regForm.gender = $('input[name=optionsRadios]:checked').val();
//            
//            
//            data.regForm = JSON.stringify(regForm);
//
//            url = "http://localhost:8080/cointelligence_eu/rest/login/register";
//
//            function usersTest() {
//                $.ajax({
//                    "url": url,
//                    "type": "POST",
//                    "data": data
//                }).success(function (data) {
////                	console.log(data);
//                });
//            }
//        }

//        var enterButtons = document.getElementsByClassName("enterButton");
//        for (var i = 0; i < enterButtons.length; i++) {
//            enterButtons[i].addEventListener("click", function () { enterTheSite()}, false);
//        }
//
//        function enterTheSite() {
//           var data = {};
//           var regForm = {};
//
//            regForm.username = document.getElementById("nickname").value;
//          
//            regForm.password = document.getElementById("pass").value;
//
//            data.regForm = JSON.stringify(regForm);
//
//            url = "http://localhost:8080/cointelligence_eu/rest/login/register";
//
//            function usersTest() {
//                $.ajax({
//                    "url": url,
//                    "type": "POST",
//                    "data": data
//                }).success(function (data) {
////                    console.log(data)
//                });
//            }
//        }

//        var askQuestionButton = document.getElementById("askQuestion");
//        askQuestionButton.addEventListener("click", function () { askTheQuestion(); }, false);
//
//        function askTheQuestion() {
//            var data = {};
//            var questionForm = {};
//
//            questionForm.headline = document.getElementById("headline").value
//            questionForm.description = document.getElementById("description").value;
//            questionForm.date = document.getElementById("date").value;
//            data.questionForm = JSON.stringify(questionForm);
//
//            url = "http://localhost:8080/cointelligence_eu/rest/login/register";
//
//            function usersTest() {
//                $.ajax({
//                    "url": url,
//                    "type": "POST",
//                    "data": data
//                }).success(function (data) {
////                    console.log(data);
//                });
//            }
//
//        }
//
//        var putIdeaButton = document.getElementById("putIdea");
//        putIdeaButton.addEventListener("click", function () { putTheIdea(); }, false);
//        function putTheIdea() {
//            var data = {};
//            var questionForm = {};
//
//            questionForm.headline = document.getElementById("ideaHeadline").value
//            questionForm.description = document.getElementById("ideaDescription").value;
//            data.questionForm = JSON.stringify(questionForm);
//
//            url = "http://localhost:8080/cointelligence_eu/rest/login/register";
//
//            function usersTest() {
//                $.ajax({
//                    "url": url,
//                    "type": "POST",
//                    "data": data
//                }).success(function (data) {
////                    console.log(data);
//                });
//            }
//
//        }
    }());

    function setModalProperties(modalQuestion, obj) {
        modalQuestion.id = "statement" + obj.id;
        modalQuestion.className = "modal hide fade modalPosition";
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
                "<p class='btn btn-info' id='myBought" + obj.id + "'>Притежавани: " + 0 + " акции" + "</p><br><br>" +
                "<p class='btn btn-info' id='myShort" + obj.id + "'>Продадени на къса позиция: " + 0 + " акции" + "</p>" +
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
                "<label id='yesHolder"+obj.id+"'><i class='icon-hand-up'></i>" +
                "<input type='radio' id='yesXXXXXX" + obj.id + "' class='selectQuontity' name='stetement' value='buy' '><p id='yesLabel" + obj.id + "'> Да, ще се случи и искам да купя:</p>" +
                "<input type='text' id='yesXXXXXXY" + obj.id + "' class='quontityField'  style='width:50px;display:none; autofocus=\"autofocus\"' /></label><br/><br/>" +
                "<label id='noHolder"+obj.id+"'><i class='icon-hand-down'></i>" +
                 "<input type='radio'id='noXXXXXXX" + obj.id + "' class='selectQuontity' name='stetement' value='sell'><p id='noLabel" + obj.id + "'> Не, няма да се случи и искам да продам:</p>" +
                 "<input type='text' id='noXXXXXXXY" + obj.id + "' class='quontityField' style='width:50px;display:none; autofocus=\"autofocus\"' /></label><br/>" +
                "<label id='shortHolder"+obj.id+"'><i class='icon-hand-down'></i>" +
                 "<input type='radio'id='shortSell" + obj.id + "' class='selectQuontity' name='stetement' value='shortSell'><p id='shortLabel" + obj.id + "'>Продавам за определено време, като се задължавам да си го купя обратно!</p>" +
                 "<input type='text' id='shortSellY"+ obj.id + "' class='quontityField' style='width:50px;display:none; autofocus==\"autofocus\"' /></label><br/>" +
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
                "<p class='btn btn-info' id='myCointels" + obj.id + "'>" + 0 + " cointels" + "</p>" +
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
        var yesStatement = document.getElementById("yesXXXXXX" + obj.id);
        var noStatement = document.getElementById("noXXXXXXX" + obj.id);
        var shortSellStatement = document.getElementById("shortSell" + obj.id);
        dealForm.statementId = obj.id;
        dealForm.currentValue = obj.currentValue;

        var selected;
        var selectedStatement;
        if (yesStatement.checked==true) {
            selected = yesStatement;
            var selectedInput = yesStatement.value;
            selectedStatement = document.getElementById(selected.id.substring(0,"shortSell".length)+"Y"+obj.id);// buy sell shortSell
        }else if (noStatement.checked == true) {
            selected = noStatement;
            var selectedInput = noStatement.value;
            selectedStatement =  document.getElementById(selected.id.substring(0,"shortSell".length)+"Y"+obj.id);// buy sell shortSell
        } else if (shortSellStatement.checked == true) {
            selected = shortSellStatement;
            var selectedInput = shortSellStatement.value;
            selectedStatement =  document.getElementById(selected.id.substring(0,"shortSell".length)+"Y"+obj.id);// buy sell shortSell
        } 

        if (selected) {
            if (selectedStatement.value<1) {
                alert("Броят акции трябва да е положителен!")
            }
            if (!(parseInt(selectedStatement.value))) {
                alert("Броят акции трябва да е число!")
            }
            dealForm.quantity = selectedStatement.value | 0;
        }else{
            alert("Избери опция за да търгуваш с твърдението!")
        }
        dealForm.username = globalUser.username; 
        
        dealForm.password = globalUser.token; 

        url = "rest/trader/"+selectedInput;
        $("#statementForm"+obj.id).attr('action', url);
        $.ajax({
            "url": url,
            "type": "POST",
            "data": dealForm
        }).success(function (data) {
//            deleteOldRows();
        	console.log(data);
//           customJS();
        	if("true" == data.toString()){
        		window.location = window.location;
        	} else {
        		if(data == 1 || data == 2){
        			alert("Сделката е отказана! Моля, презареди страницата!");
        		} else if(data == 3){
        			alert("Твоите cointels не са достатъчни за тази сделка!");
        		} else if(data == 4) {
        			alert("Твоите акции не са достатъчни за тази сделка!");
        		} else {
        			alert("Системна грешка! Моля провери профила си дали сделката се е изпълнила!");
        		}
        	}
        });
    }

    function showStetementQuontity(event) {
        //event support IE
        if (!event) {
            event = window.event;
        }
        var inputField = document.getElementsByClassName("quontityField");
        for(var i=0; i < inputField.length; i++){
        	inputField[i].style.display = "none";
        	
        }
//        var stementsContainer = document.getElementsByClassName("selectQuontity");
//        for (var i = 0; i < stementsContainer.length; i++) {
//            //ie and firefox support 
//            if (stementsContainer[i].nextElementSibling) {
//                stementsContainer[i].nextElementSibling.style.display = "none";
//            } else {
//                var el = nextElementSibling(stementsContainer[i]);
//                el.style.display = "none";
//            }
//            
//        }
        if (event.target) {
            var currentSelected = event.target;
        } else {
            var currentSelected = event.srcElement;
        }
        var id = currentSelected.id; 
        var statementId = id.substring("shortSell".length);
        var otherId = id.substring(0, "shortSell".length) + "Y" + statementId;
        
//        if (currentSelected.nextElementSibling) {
//            if (currentSelected.nextElementSibling.value) {
//                currentSelected.nextElementSibling.value = "";
//            }
            document.getElementById(otherId).style.display = "inline-block";
//        } else {
//            var el = nextElementSibling(currentSelected);
//            if (el.value) {
//                currentSelected.nextElementSibling.value = "";
//            }
//            el.style.display = "inline-block";
//        }
        
    }

    function showPotentialWinAndLost(event,question) {
        if (!event) {
            event = window.event
        }
        
        if (!event.target) {
            var eventTarget = event.srcElement;
        } else {
            var eventTarget = event.target;
        }
        var bittedValue = eventTarget.value;
        
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

        if (eventTarget.id == ("yesXXXXXXY" + question.id)) {
            var potentialWin = document.getElementById("win" + question.id);
            potentialWin.innerHTML = (100 - parseFloat(question.currentValue)) * (parseFloat(bittedValue));
            var potentialLoss = document.getElementById("loss" + question.id);
            potentialLoss.innerHTML = (question.currentValue * parseFloat(bittedValue));
            if (!eventTarget.value) {
                potentialWin.innerHTML = "0";
                potentialLoss.innerHTML = "0";
            }
        } else if (eventTarget.id == ("noXXXXXXXY" + question.id)) {
            var potentialWin = document.getElementById("win" + question.id);
            potentialWin.innerHTML = (question.currentValue * parseFloat(bittedValue));
            var potentialLoss = document.getElementById("loss" + question.id);
            potentialLoss.innerHTML = (100 - parseFloat(question.currentValue)) * (parseFloat(bittedValue));
            if (!eventTarget.value) {
                potentialWin.innerHTML = "0";
                potentialLoss.innerHTML = "0";
            }
        }else {//TODO:
            var potentialWin = document.getElementById("win" + question.id);
            potentialWin.innerHTML = (question.currentValue * parseFloat(bittedValue));
            var potentialLoss = document.getElementById("loss" + question.id);
            potentialLoss.innerHTML = (100 - parseFloat(question.currentValue)) * (parseFloat(bittedValue));
            if (!eventTarget.value) {
                potentialWin.innerHTML = "0";
                potentialLoss.innerHTML = "0";
            }
        }
    }
    function deleteOldRows() {
        var questionsTable = document.getElementById("statementsInfo");

        questionsTable.innerHTML = "<tr id='questionTableHeadline'> \
                            <td class='statementDivs' id='tableTitle'>Заглавие</td> \
                            <td class='statementDivs' id='tableDescription'>Описание</td> \
                            <td class='statementDivs' id='tablePrice'>Цена</td> \
                            <td class='statementDivs' id='saleColumn'>Търгувай</td> \
                        </tr>";

        var rankingTable = document.getElementById("rankTable");
        rankingTable.innerHTML = "  <tr> \
                            <td class='statementDivs' id='userName'>Псевдоним</td> \
                            <td class='statementDivs' id='cointels'>Cointels</td> \
                            <td class='statementDivs' id='wealth'>Богатсво</td> \
                        </tr>";
    }
    function nextElementSibling(el) {
        do { el = el.nextSibling } while (el && el.nodeType !== 1);
        return el;
    }

  };
  customJS();

