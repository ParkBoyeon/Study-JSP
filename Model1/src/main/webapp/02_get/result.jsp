<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// WebHelper 객체 생성
	WebHelper webHelper = WebHelper.getInstance(request, response);

	// 지금까지 누적된 모든 GET 파라미터 수신 
	String ans1 = request.getParameter("ans1");
	String ans2 = request.getParameter("ans2");
	String ans3 = request.getParameter("ans3");
	
	// 지금까지 누적된 모든 파라미터에 대한 유효성 검사
	if(ans1 == null || ans1.equals("")){
		// 비정상 파라미터가 수신된 경우 이전페이지로 되돌아간다.
		webHelper.redirect(null, "첫 번째 문항에 대한 응답이 없습니다.");
		return;
	}
	
	if(ans2 == null || ans2.equals("")){
		// 비정상 파라미터가 수신된 경우 이전페이지로 되돌아간다.
		webHelper.redirect(null, "두 번째 문항에 대한 응답이 없습니다.");
		return;
	}
	
	if(ans3 == null || ans3.equals("")){
		// 비정상 파라미터가 수신된 경우 이전페이지로 되돌아간다.
		webHelper.redirect(null, "세 번째 문항에 대한 응답이 없습니다.");
		return;
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>응답 결과</h1>
	<p>직업: <%=ans1 %></p>
	<p>취미: <%=ans2 %></p>
	<p>성별: <%=ans3 %></p>
</body>
</html>