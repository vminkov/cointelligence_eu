<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a form used for testing the users services</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script>
	data = {};
	regForm = {};
	
	time = new Date();
	timestamp = "";
	timestamp += time.getHours();
	timestamp += time.getMinutes();
	
	regForm.username = "testio" + timestamp;
	regForm.fullname = "pylnoto";
	regForm.password = "testev";
	regForm.age = 21;
	regForm.gender = "MALE";
	regForm.department = "testers";
	regForm.email = "test@cointelligence.eu";
	data.regForm = JSON.stringify(regForm);

	url = "http://localhost:8080/cointelligence_eu/rest/login/register";

	function usersTest() {
		$.ajax({
			"url" : url,
			"type" : "POST",
			"data" : data
		}).success(function(data) {
			console.log(data)
		});
	}
</script>
</head>

<body>
	<button onclick="usersTest()">Test it</button>

</body>
</html>