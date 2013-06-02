(function profileJS() {
    var path = 'testJSON/profile.js';
    var user = $.getJSON(path, function (data) {
        $.each(data, function (index, user) {
            var avatar = document.createElement("img");
            var avatarWrapper = document.getElementById("avatar");
            var infoWrapper = document.getElementById("infoUser");
            var nickName = document.createElement("p");
            var fullName = document.createElement("p");
            var department = document.createElement("p");
            var cointels = document.createElement("p");
            var summaryPoints = document.createElement("p");
            summaryPoints.innerHTML = "Брой акции и 'Cointels' : " + user.summary;
            cointels.innerHTML = "Cointels : " + user.cointels;
            department.innerHTML = "Отдел : " + user.department;
            fullName.innerHTML = "Трите имена :" + user.fullName;
            nickName.innerHTML = "Псевдоним : " + user.nickname;
            avatar.src = user.picture;
            avatarWrapper.appendChild(avatar);

            summaryPoints.className = "btn btn-info customBtn";
            cointels.className = "btn btn-info customBtn";
            department.className = "btn btn-info customBtn";
            fullName.className = "btn btn-info customBtn";
            nickName.className = "btn btn-info customBtn";

            infoWrapper.appendChild(nickName);
            infoWrapper.appendChild(fullName);
            infoWrapper.appendChild(department);
            infoWrapper.appendChild(cointels);
            infoWrapper.appendChild(summaryPoints);
            
            var div = ""
            $.each(user.portfolio, function (portfolioIndex, statement) {
                div += "<h3 class='customPortfolioBorder btn btn-info'>" + statement.title + "</h3>";
                div += "<div class='portfolioContent'> \
 <table> \
    <tr> \
                            <td class='span3 btn btn-success customPriceButton'>Описание</td> \
                           <td class='span1 btn btn-success customPriceButton'>Текуща цена</td> \
                            <td class='span1 btn btn-success customPriceButton'>Купени акции</td> \
                            <td class='span2 btn btn-success customPriceButton'>Прададени акции</td> \
    </tr> \
    <tr> \
                        <td class='span3 btn btn-primary'>" + statement.description + "</td> \
                           <td class='span1 btn btn-primary customPriceButton'>" + statement.currentValue + "</td> \
                            <td class='span1 btn btn-primary customPriceButton'>" + statement.currentBought + "</td> \
                            <td class='span1 btn btn-primary customPriceButton'>" + statement.currentSold + "</td> \
    </tr> \
 </table> \
                           </div>";
                
            });
            var accordion = document.getElementById("beforeAccordion");
            accordion.id = "accordion";
            $("#accordion").html(div).accordion();
        });
       
    }).fail(function () {
        console.log("fail");
    });
  
}());