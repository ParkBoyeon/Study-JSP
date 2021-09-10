<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// request객체에 포함된 num1, num2, num3 값을 꺼낸다.
	// 이 값들은 Servlet에서 이 JSP페이지로 forward 되기 전에 request 객체에 추가된다.
	int num1 = (Integer) request.getAttribute("num1");
	int num2 = (Integer) request.getAttribute("num2");
	int num3 = (Integer) request.getAttribute("num3");

%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h1><%=num1 %> + <%=num2 %> = <%=num3 %></h1>
</body>
</html>