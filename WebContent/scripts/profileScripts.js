function getCookie(name) {
  var regexp = new RegExp("(?:^" + name + "|;\\s*"+ name + ")=(.*?)(?:;|$)", "g");
  var result = regexp.exec(document.cookie);
  return (result === null) ? null : result[1];
}
  
(function profileJS() {
    var path = 'rest/users/portfolio';
    var statementsPath = "rest/statements";
    var username = getCookie("username");
    var token = getCookie("jsession");
    var statements = {};
    
    var promise = $.ajax({'url': statementsPath});
  
    $.ajax({'url': path, 'method': "post", 'data': {'username': username, 'token': token}}).success( function (user) {
    	promise.success(
    	function(statementsFromServer){
	    	$.each(statementsFromServer, function(index, statement) {
	    		statements[statement.id] = statement;
	    	});
	    	
            var avatar = document.createElement("img");
            var avatarWrapper = document.getElementById("avatar");
            var infoWrapper = document.getElementById("infoUser");
            var nickName = document.createElement("p");
            var fullName = document.createElement("p");
            var department = document.createElement("p");
            var cointels = document.createElement("p");
            var summaryPoints = document.createElement("p");
            summaryPoints.innerHTML = "Богатство : " + user.wealth;
            cointels.innerHTML = "Cointels : " + user.cointels;
            department.innerHTML = "Отдел : " + user.department;
            fullName.innerHTML = "Трите имена :" + user.fullname;
            nickName.innerHTML = "Псевдоним : " + user.username;
            avatar.src = "img/avatar.jpg";
            avatarWrapper.appendChild(avatar);

//            summaryPoints.className = "btn btn-info customBtn";
//            cointels.className = "btn btn-info customBtn";
//            department.className = "btn btn-info customBtn";
//            fullName.className = "btn btn-info customBtn";
//            nickName.className = "btn btn-info customBtn";

            infoWrapper.appendChild(nickName);
            infoWrapper.appendChild(fullName);
            infoWrapper.appendChild(department);
            infoWrapper.appendChild(cointels);
            infoWrapper.appendChild(summaryPoints);
            
            var div = "";
            $.each(statements, function (statementId, statement) {
            	if(!statements || statements.length == 0){
            		$("#beforeAccordion").text("Все още нямаш направени транзакции. Търгувай :) !")	;
            	}
//                    $.each(user.statementsInPossession, function (portfolioIndex, countInPossession) {
//            	var statement = statements[portfolioIndex];
            	if(user.statementsInPossession[statementId] || user.shortSellsInPossession[statementId] ) {
	                div += "<h3 class='customPortfolioBorder btn btn-info'>" + statement.title + "</h3>";
	                div += "<div class='portfolioContent'> \
	 <table> \
	    <tr> \
	                            <td class='span3 btn btn-success customPriceButton'>Описание</td> \
	                           <td class='span1 btn btn-success customPriceButton'>Текуща цена</td> \
	                            <td class='span1 btn btn-success customPriceButton'>Купени акции</td> \
	                            <td class='span2 btn btn-success customPriceButton'>Акции на къса позиция</td> \
	    </tr> \
	    <tr> \
	                        <td class='span3 btn btn-primary'>" + statement.description + "</td> \
	                           <td class='span1 btn btn-primary customPriceButton'>" + statement.currentValue + "</td> \
	                            <td class='span1 btn btn-primary customPriceButton'>" + (user.statementsInPossession[statementId] ? user.statementsInPossession[statementId] : "0") + "</td> \
	                            <td class='span1 btn btn-primary customPriceButton'>" + (user.shortSellsInPossession[statementId] ? user.shortSellsInPossession[statementId] : "0") + "</td> \
	    </tr> \
	 </table></div>";
            	}
            	

        
            });
            
            
            var accordion = document.getElementById("beforeAccordion");
            accordion.id = "accordion";
            console.log(div);
            $("#accordion").html(div).accordion();    
    	});
    }).fail(function () {
        console.log("fail");
    });
  
}());