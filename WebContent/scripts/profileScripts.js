(function profileJS() {
    var path = 'testJSON/profile.js';
    var user = $.getJSON(path, function (data) {
        $.each(data,function(index,user){

        });
        $.each(data, function (index, user) {
            var row = $('<tr class="questionRow"/>');
            row.append("<td class='idCol questionCol'>" + user.id + "</td>");
            row.append("<td class='titleCol questionCol'> " + user.title + "</td>");
            row.append("<td class='descCol questionCol'><i class='icon-info-sign'></i> <a href='#statement" + user.id + "' role='button' data-toggle='modal'>" + user.description + "</a></td>");
            row.append("<td class='priceCol questionCol'>" + user.currentValue + "</td>");
            $('#statementsPortfolioInfo').append(row);
            var modalQuestion = document.createElement("div");
            setModalProperties(modalQuestion, user);
            $('#wrapper').append(modalQuestion);
            var button = document.getElementById("sendFormButton" + user.id);
            button.onclick = function () {
                var newuser = user;
                usersTest(newuser, event);
            };
            var quontitityFields = document.getElementsByClassName("selectQuontity");
            for (var i = 0; i < quontitityFields.length; i++) {
                quontitityFields[i].onclick = function () {
                    showStetementQuontity(event);
                };
            }

        });
    }).fail(function () {
        console.log("fail");
    });
}());