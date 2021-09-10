<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>학과정보</h1>
    <p>학과번호: ${output.deptno}</p>
    <p>학과이름: ${output.dname}</p>
    <p>학과위치: ${output.loc}</p>
    <hr />
    <a href="${pageContext.request.contextPath}/department/list.do">[목록보기]</a>
    <a href="${pageContext.request.contextPath}/department/add.do">[학과추가]</a>
    <a href="${pageContext.request.contextPath}/department/edit.do?deptno=${output.deptno}">[학과수정]</a>
    <a href="${pageContext.request.contextPath}/department/delete_ok.do?deptno=${output.deptno}">[학과삭제]</a>
</body>
</html>
