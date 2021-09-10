<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// WebHelper의 객체를 생성하는 것 만으로 실행중인 페이지가 log에 기록된다.
	WebHelper webHelper = WebHelper.getInstance(request, response);
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>Q1) 당신의 직업은 무엇입니까?</h1>
	
	<ul>
		<!-- 숫자값이나 영문은 별다른 처리 없이 파라미터 전달 가능 -->
		<li><a href="q02.jsp?ans1=Backend">백엔드 프로그래머</a></li>
		<li><a href="q02.jsp?ans1=Frontend">프론트엔드 프로그래머</a></li>
		<li><a href="q02.jsp?ans1=Designer">디자이너</a></li>
		<li><a href="q02.jsp?ans1=PM">프로젝트관리</a></li>
	</ul>
</body>
</html>