<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

	<h3>뉴스</h3>
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/contents/contents_news.do">
			/contents/contents_news.do</a>
		</li>
	</ul>

	
</body>
</html>
