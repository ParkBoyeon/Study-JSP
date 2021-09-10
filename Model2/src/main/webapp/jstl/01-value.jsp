<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="study.jsp.model2.model.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// 문자열 변수를 View에 전달함
	String message = "Hello JSTL";
	request.setAttribute("my_message", message);
	
	// 정수형 변수를 View에 전달함
	int num1 = 7;
	int num2 = 9;
	request.setAttribute("my_num1", num1);
	request.setAttribute("my_num2", num2);
	
	// Pojo 클래스에 대한 객체를 View에 전달함
	Member member = new Member();
	member.setName("학생");
	member.setAge(20);
	request.setAttribute("my_member", member);
	
%>
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