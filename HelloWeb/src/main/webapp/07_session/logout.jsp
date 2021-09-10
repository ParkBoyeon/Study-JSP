<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	// 모든 세션값을 삭제하고 돌아가기
	session.invalidate();
	//response.sendRedirect("login.jsp");
%>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<script type="text/javascript">
		alert("안녕히가세요.");
	</script>
	<!-- HTML을 활용한 또 다른 페이지 강제 이동 방법 -->
	<meta http-equiv="refresh" content="0; url=login.jsp" />
</head>
</html>