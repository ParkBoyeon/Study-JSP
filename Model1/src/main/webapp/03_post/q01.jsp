<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// WebHelper 객체 생성
	WebHelper webHelper = WebHelper.getInstance(request, response);
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>간단 설문조사 1/3</h1>
	<h2>기본정보 입력</h2>
	
	<form action="<%=request.getContextPath()%>/03_post/q02.jsp" name="myform" method="post">
		<div>
			<label for="user_name">이름</label>
			<input type="text" name="user_name" id="user_name" />
		</div>
		<div>
			<label for="user_email">이메일</label>
			<input type="email" name="user_email" id="user_email" />
		</div>
		<div>
			<label for="user_tel">연락처</label>
			<input type="tel" name="user_tel" id="user_tel" />
		</div>
		<button type="submit">다음단계</button>
	</form>
</body>
</html>