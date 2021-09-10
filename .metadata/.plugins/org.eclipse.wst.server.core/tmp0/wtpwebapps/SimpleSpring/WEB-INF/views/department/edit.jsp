<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>학과수정</h1>
    <form method="post" action="${pageContext.request.contextPath}/department/edit_ok.do">
        <% /* action 페이지에서 사용할 WHERE 조건값을 hidden 필드로 숨겨서 전송한다. */ %>
        <input type="hidden" name="deptno" value="${output.deptno}" />
        <div>
            <label for="dname">학과명: </label>
            <input type="text" name="dname" id="dname" value="${output.dname}" />
        </div>
        <div>
            <label for="loc">위치: </label>
            <input type="text" name="loc" id="loc" value="${output.loc}" />
        </div>
        <hr />
        <button type="submit">저장하기</button>
        <button type="reset">다시작성</button>
    </form>
</body>
</html>