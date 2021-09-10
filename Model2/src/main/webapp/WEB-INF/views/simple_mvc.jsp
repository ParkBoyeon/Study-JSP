<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>여기는 SimpleMVC에 의해서 로드되는 simple_mvc.jsp 입니다.</h1>
	
	<h2>Get data 전송하기</h2>
	<a href="sub.do?num1=100&num2=200">go to sub.do</a>
	
	<h2>Post data 전송하기</h2>
	<form action="sub.do" method="post">
		<input type="text" name="user_name" />
		<button type="submit">send data</button>
	</form>
</body>
</html>