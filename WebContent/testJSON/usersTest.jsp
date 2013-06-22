<%@page import="eu.cointelligence.controller.Constants"%>
<%@page import="eu.cointelligence.model.User"%>
<%@page import="eu.cointelligence.controller.users.IUserManager"%>
<%@page import="eu.cointelligence.controller.entity.Gender"%>
<%@page import="java.util.ArrayList"%>
<%@page import="eu.cointelligence.controller.entity.beans.RegisterForm"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

	url = "http://localhost:8080/cointelligence_eu/rest/users/register";

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
<%!private String getMD5Hash(String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		byte[] passwordAsBytes = password.getBytes("UTF-8");
		MessageDigest md = null;
		md = MessageDigest.getInstance("MD5");
		byte[] theDigest = md.digest(passwordAsBytes);

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < theDigest.length; i++) {
			String hex = Integer.toHexString(0xff & theDigest[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

	private String passGen() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		return getMD5Hash(
				new String("meri ima agynce" + System.currentTimeMillis()))
				.toString().substring(0, 6);
	}
	List<RegisterForm> regForms;
	List<String> departments = new ArrayList<String>();
	Gson  gson = new Gson();
	List<RegisterForm> testUsers;
	IUserManager userManager;
	%>

<%
	userManager = (IUserManager) application.getAttribute(Constants.USER_MANAGER);
	testUsers = (List<RegisterForm>) application.getAttribute("testUsers");
	if(testUsers == null && userManager.getAllUsers().size() < 10){
		departments.add("Маркетинг");
		departments.add("Финанси");
		departments.add("Човешки ресурси");
		departments.add("ИТ");
		departments.add("Мениджмънт");
		regForms = new ArrayList<RegisterForm>();
		String broiStr = request.getParameter("broiUseri");
		int broi = Integer.valueOf(broiStr);
		
		for(int i = 0; i<broi; i++){
			RegisterForm user = new RegisterForm();
			user.setAge(21);
			String dep = departments.get((int)(Math.random() * ((double) departments.size())));
			user.setDepartment(dep);
			user.setFullname("Тестов Потребител " + i);
			user.setGender(Gender.MALE);
			user.setPassword(passGen());
			user.setUsername("user"+i);
			
			
			user.setEmail(user.getUsername()+"@cointelligence.eu");
			
			User returned = userManager.createNewUser(user.getUsername(), user.getPassword());
			returned.setDepartment(user.getDepartment());
			returned.setEmail(user.getEmail());
			returned.setFullName(user.getFullname());
			returned.setGender(user.getGender());
			returned.setUserName(user.getUsername());
			userManager.updateUserInfo(returned);
			
			regForms.add(user);
		}
		testUsers = regForms;
		application.setAttribute("testUsers", testUsers);
	}
	%>

</head>

<body>
	<script>
	</script>
	<button onclick="usersTest()">Test it</button>
	<table>
		<tr>
			<td>Username</td>
			<td>Password</td>
		</tr>
		<%
			for (RegisterForm reg : testUsers) {
		%>
		<tr>
			<td><%= reg.getUsername()%></td>
			<td><%= reg.getPassword()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>