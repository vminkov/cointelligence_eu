<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--  insert head  -->
<%@include file="head.jsp"%>


<%!User userInfo;%>
<%
	userInfo = (User) ((HttpSession) session)
			.getAttribute(Constants.USER_INFO_SESSION_ATTR_NAME);
%>
<!--  insert header  -->
<%
	if (userInfo != null) {
%>
<%@include file="WEB-INF/loggedHeader.jsp"%>
<%
	} else {
%>
<%@include file="WEB-INF/header.jsp"%>

<%
	}
%>


<body>
 <section>
            <div id="profileWrapper">
                <!--left part-->

                <div id="userInformation" class="span7">
                    <h1 id="userInfoHeadline">Инфомация</h1>
                    <div id="avatar" class="span2"></div>
                    <div id="infoUser" class="span3"></div>
                </div>

                <!--right part-->
                <div id="portfolio" class="span7">

                    <h1 >Портфолио на сделките</h1>
                    <div id="accordion">
                       

                    </div>
                </div>
            </div>
    </section>
	<%@include file="WEB-INF/foot.jsp"%>
	    <script src="scripts/profileScripts.js"></script>
	
</body>

</html>