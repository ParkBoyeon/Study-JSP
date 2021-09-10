<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>${my_message}</h1>
	<h1>${my_num1} + ${my_num2} = ${my_num1+mynum2}</h1>
	<h1>${my_member.name}</h1>
	<h1>${my_member.age}</h1> 
</body>
</html>