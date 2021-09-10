<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	WebHelper webHelper = WebHelper.getInstance(request, response);

	// 메시지 없이 이전 페이지로 이동
	// webHelper.redirect(null, null);
	
	// 메시지 표시 후 이전 페이지로 이동
	// webHelper.redirect(null, "이전 페이지로 돌아갑니다.");
	
	// 메시지 없이 webhelper.jsp 페이지로 이동
	 // webHelper.redirect("webhelper.jsp", null);
	
	// 메시지 표시 후 webhelper.jsp 페이지로 이동
	 webHelper.redirect("webhelper.jsp", "Hello World");
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
</head>
<body>
	<a href="after.jsp">after.jsp로 이동</a>
</body>
</html>