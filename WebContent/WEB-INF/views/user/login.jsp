<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>login</h1>
	<hr>
	<form action="login" method="post">
		<spring:message code="user.name"/><input type="text" name="name"><br>
		<spring:message code="user.password"/><input type="password" name="password"><br>
		<spring:message code="user.verifyCode"/><input type="text" name="verifyCode">
		<img alt='<spring:message code="user.wait"/>' src="verifyCode"><br>
		<spring:message code="user.rememberMe"/><input type="checkbox" name="rememberMe"><br>
		<input type="submit" value='<spring:message code="user.login" />'>
	</form>
</body>
</html>