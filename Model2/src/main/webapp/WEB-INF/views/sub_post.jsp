<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// request 객체에 포함된 user_name 값을 꺼낸다.
	// 이 값들은 Servlet에서 이 JSP페이지로 forward 되기 전에 request 객체에 추가된다.
	String user_name = (String) request.getAttribute("user_name");
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1><%=user_name %>님 안녕하세요.</h1>
</body>
</html>