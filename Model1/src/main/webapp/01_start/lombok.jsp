<%@page import="study.jsp.model1.model.HelloModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// 모델 객체 생성하기
	HelloModel helloModel1 = new HelloModel();
	helloModel1.setId(123);
	helloModel1.setName("헬로");
	helloModel1.setEmail("hello@itpaper.co.kr");
	helloModel1.printLog();
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1><%=helloModel1.toString() %></h1>
</body>
</html>