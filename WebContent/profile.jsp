<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--  insert head  -->
<%@include file="head.jsp"%>
<%@include file="WEB-INF/loggedHeader.jsp"%>


<body>
	<section>
	<div id="profileWrapper">
		<!--left part-->

		<div id="userInformation" class="span5">
			<h1 id="userInfoHeadline">Инфомация</h1>
			<div id="avatar" class="span2"></div>
			<div id="infoUser" class="span3"></div>
			<div id="winReward">
				<img src="img/sign.png" />
			</div>
		</div>

		<!--right part-->
		<div id="portfolio" class="span6">

			<h1 id="portfolioHeadline">Портфолио на сделките</h1>
			<div id="beforeAccordion"></div>
		</div>
	</div>
	</section>
	<%@include file="WEB-INF/foot.jsp"%>
	<script src="scripts/profileScripts.js"></script>

</body>

</html>