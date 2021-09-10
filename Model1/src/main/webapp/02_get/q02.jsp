<%@page import="java.net.URLEncoder"%>
<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// WebHelper 객체 생성
	WebHelper webHelper = WebHelper.getInstance(request, response);

	// GET 파라미터 수신
	String ans1 = request.getParameter("ans1");
	
	if(ans1 == null || ans1.equals("")){
		// 비정상 파라미터가 수신된 경우 이전페이지로 되돌아간다.
		webHelper.redirect(null, "첫 번째 문항에 대한 응답이 없습니다.");
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
	<h1>Q2) 당신의 취미는 무엇입니까?</h1>
	
	<ul>
		<!-- 이전 페이지에서 전달된 파라미터를 누적한 상태로 새로운 변수값 추가 -->
		<!-- 한글이나 공백이 포함된 값을 GET으로 전송할 경우 반드시 URLEncode 적용해야함 -->
		<li><a href="q03.jsp?ans1=<%=ans1%>&ans2=<%=URLEncoder.encode("음악감상", "utf-8")%>">음악감상</a></li>
		<li><a href="q03.jsp?ans1=<%=ans1%>&ans2=<%=URLEncoder.encode("스포츠", "utf-8")%>">스포츠</a></li>
		<li><a href="q03.jsp?ans1=<%=ans1%>&ans2=<%=URLEncoder.encode("사진촬영", "utf-8")%>">사진촬영</a></li>
		<li><a href="q03.jsp?ans1=<%=ans1%>&ans2=<%=URLEncoder.encode("등산", "utf-8")%>">등산</a></li>
	</ul>
</body>
</html>