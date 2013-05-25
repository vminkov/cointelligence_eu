(function index() {
    var paths = {};
    paths.stetements = 'testJSON/testStatements.js';
    paths.users = 'testJSON/users.js';
    var test = $.getJSON(paths.stetements, function (data) {
        $.each(data, function (index, obj) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='idCol questionCol'>" + obj.id + "</td>");
            row.append("<td class='titleCol questionCol'> " + obj.title + "</td>");
            row.append("<td class='descCol questionCol'><i class='icon-info-sign'></i> <a href=#>" + obj.description + "</a></td>");
            row.append("<td class='priceCol questionCol'>" + obj.currentValue + "</td>");
            $('#statementsInfo').append(row);
        });
    }).fail(function () {
        console.log("fail");
    });

    var test = $.getJSON(paths.users, function (data) {
        $.each(data, function (index, obj) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='nicknameCol questionCol'><i class='icon-user'></i>" + obj.userName + "</td>");
            row.append("<td class='moneyCol questionCol'> " + obj.account.money + "</td>");
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
        event.preventDefault();
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