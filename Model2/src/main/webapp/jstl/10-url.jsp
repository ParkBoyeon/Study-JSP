<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
</head>
<body>
	<h3>
		<c:url value="${contextPath}/list.jsp" var="next_url">
			<c:param name="keyword" value="JSP공부"></c:param>
			<c:param name="page" value="1"></c:param>
		</c:url>
	</h3>
	<a href="${next_url}">다음으로 이동하기</a>
</body>
</html>