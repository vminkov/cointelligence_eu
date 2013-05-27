(function index() {
    var paths = {};
    paths.stetements = 'testJSON/testStatements.js';
    paths.users = 'testJSON/users.js';
    var test = $.getJSON(paths.stetements, function (data) {
        $.each(data, function (index, obj) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='idCol questionCol'>" + obj.id + "</td>");
            row.append("<td class='titleCol questionCol'> " + obj.title + "</td>");
            row.append("<td class='descCol questionCol'><i class='icon-info-sign'></i> <a href='#statement" + obj.id + "' role='button' data-toggle='modal'>" + obj.description + "</a></td>");
            row.append("<td class='priceCol questionCol'>" + obj.currentValue + "</td>");
            $('#statementsInfo').append(row);
            var modalQuestion = document.createElement("div");
            modalQuestion.id = "statement" + obj.id;
            modalQuestion.className = "modal hide fade";
            modalQuestion.tabIndex = "-1";
            modalQuestion.setAttribute("role","dialog");
            modalQuestion.setAttribute("aria-labelledby", "myModalLabel");
            modalQuestion.setAttribute("aria-hidden", "true");
            modalQuestion.innerHTML ="<div class='modal-header'>"+
                "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>" +
                "<h3 id='H2'>Търгувай с въпроса!</h3>" +
                "</div>" +
                "<div class='modal-body'>" +
                "<form class='form-horizontal'>" +
                "<div class='control-group'>" +
                "<label class='control-label'>Заглавие на въпроса</label>" +
                "<div class='controls'>" +
                "<p>"+obj.title + "<p/>" +
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
                "<p class='btn btn-info'>" + obj.currentValue + " cointels за акция" + "<p/>" +
                "</div>" +
                "</div>" +
                "<div class='control-group'>" +
                "<label class='control-label'>Избери твърдерние с определена цена:</label>" +
                "<div class='controls stetementContainer'>" +
                "<i class='icon-hand-up'></i>" +
                "<input type='radio' id='yesStetement' name='stetement' value='yes'> Да, ще се случи и искам да си купя:" +
                "<input type='text' id='yesQuontity'  style='width:50px' /><br/><br/>" +
                "<i class='icon-hand-down'></i>" +
                 "<input type='radio'id='noStetement' name='stetement' value='no'> Не, няма да се случи и искам да си купя:" +
                 "<input type='text' id='noQuontity'  style='width:50px'/><br/>" +
                "</div>" +
                "</div>" +
                   "<div class='control-group'>" +
                "<label class='control-label'>Общ брой налични Cointels в момента:</label>" +
                "<div class='controls'>" +
                "<p class='btn btn-info'>" + 1000 + " cointels" + "<p/>" +
                "</div>" +
                "</div>" +
                "</form>" +
                "</div>" +
                "<div class='modal-footer'>" +
                "<button class='btn' data-dismiss='modal' aria-hidden='true'>Затвори</button>" +
                "<button class='btn btn-primary'>Направи сделката!</button></div>";
            $('#wrapper').append(modalQuestion);
        });
    }).fail(function () {
        console.log("fail");
    });

    var test = $.getJSON(paths.users, function (data) {
        $.each(data, function (index, obj) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='nicknameCol questionCol'><i class='icon-user'></i>" + obj.userName + "</td>");
            //row.append("<td class='moneyCol questionCol'> " + obj.account.money + "</td>");
            row.append("<td class='priceCol questionCol'>" + obj.account.cointels + "</td>");
            $('#rankTable').append(row);
        });
    }).fail(function () {
        console.log("fail");
    });
}());



(function activeNav() {
    var nav = document.getElementById("mainUl");
    var navElements = nav.children;
    function onNavItemClick(event) {
        event.stopPropagation();
        //event.preventDefault();
        for (var i = 0; i < navElements.length; i++) {
            navElements[i].className = "";
        }
        var currentElement = event.target;
        currentElement.parentNode.className= "active";
    }
    
    for (var i = 0; i < navElements.length; i++) {
        navElements[i].addEventListener("click", onNavItemClick, false)
    }
}());




//function setStetementQuontity() {
//    var stementsContainer = document.getElementsByClassName;
//    for (var i = 0; i < stementsContainer.length; i++) {
//        var stetementYesRadio = document.getElementById("yesStetement");
//        if (stetementYesRadio.selected="selected") {
//            var quontityYes = document.getElementById("yesQuontity");
//            quontityYes.style.display = "block";
//        }
//        var stetementNoRadio = document.getElementById("noStetement");
//        if (stetementNoRadio.selected = "selected") {
//            var quontityYes = document.getElementById("noQuontity");
//            quontityYes.style.display = "block";
//        }
//    }

//};

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