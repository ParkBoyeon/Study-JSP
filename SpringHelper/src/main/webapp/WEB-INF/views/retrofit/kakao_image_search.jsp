<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP</title>
</head>
<body>
	<!-- 1) 제목과 검색 폼 -->
	<h1>카카오 이미지 검색</h1>
	<form name="form1" method="get" action="${pageContext.request.contextPath}/retrofit/kakao_image_search.do">
		<label for="query">검색어: </label>
		<input type="search" id="query" name="query" value="${query}" />
		<input type="submit" value="검색" />
	</form>
	<hr />
	
	<!-- 2) 검색 결과 처리 -->
	<c:choose>
		<%-- 검색 결과가 존재할 경우 목록을 표 형식으로 출력 --%>
		<c:when test="${image != null && image.documents.size() > 0}">
			<table border="1">
				<%-- 검색 결과에서 List에 해당하는 부분을 반목문으로 출력 --%>
				<c:forEach var="item" items="${image.documents}" varStatus="status">
					<c:if test="${status.index % 6 == 0}">
						<tr>
					</c:if>
					
					<td>
						<a href="${item.docUrl}">
							<img src="${item.thumbnail_url}">
						</a>
					</td>
					
					<c:if test="${status.index + 1 % 6 == 0}">
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:when>
		<%-- 검색 결과가 존재하지 않을 경우 --%>
		<c:otherwise>
			<h3>검색결과가 없습니다.</h3>
		</c:otherwise>
	</c:choose>
	
</body>
</html>