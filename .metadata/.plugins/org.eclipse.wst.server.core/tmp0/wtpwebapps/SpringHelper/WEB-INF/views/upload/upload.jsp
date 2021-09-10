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
	<h1>JSP 파일 업로드</h1>
	<form method="post" action="${pageContext.request.contextPath}/upload/upload_ok.do" enctype="multipart/form-data">
		<div>
			<label for="subject">사진제목</label>
			<input type="text" name="subject" id="subject" />
		</div>
		<div>
			<label for="photo">사진선택</label>
			<input type="file" name="photo" id="photo" />
		</div>
		<button type="submit">업로드하기</button>
	</form>

</body>
</html>