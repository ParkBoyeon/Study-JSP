<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>학과추가</h1>
    <form method="post" action="${pageContext.request.contextPath}/department/add_ok.do">
        <div>
            <label for="dname">학과명: </label>
            <input type="text" name="dname" id="dname" />
        </div>
        <div>
            <label for="loc">위치: </label>
            <input type="text" name="loc" id="loc" />
        </div>
        <hr />
        <button type="submit">저장하기</button>
        <button type="reset">다시작성</button>
    </form>
</body>
</html>

