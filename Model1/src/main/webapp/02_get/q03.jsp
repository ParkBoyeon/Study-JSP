<%@page import="java.net.URLEncoder"%>
<%@page import="study.jsp.model1.helper.WebHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// WebHelper 객체 생성
	WebHelper webHelper = WebHelper.getInstance(request, response);

	// GET 파라미터 수신 --> URLEncoder가 적용된 파라미터는 수신과정에서 자동으로 디코딩 됨
	String ans1 = request.getParameter("ans1");
	String ans2 = request.getParameter("ans2");
	
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
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1>Q3) 당신의 성별은 무엇입니까?</h1>
	<ul>
		<li>
			<!-- 디코딩된 ans2값을 재전송하기 위해서는 다시 한번 인코딩이 필요함 -->
			<a href="result.jsp?ans1=<%=ans1%>&ans2=<%=URLEncoder.encode(ans2, "utf-8")%>&ans3=M">남자</a>
		</li>
		<li>
			<a href="result.jsp?ans1=<%=ans1%>&ans2=<%=URLEncoder.encode(ans2, "utf-8")%>&ans3=F">여자</a>
		</li>
	</ul>
</body>
</html>