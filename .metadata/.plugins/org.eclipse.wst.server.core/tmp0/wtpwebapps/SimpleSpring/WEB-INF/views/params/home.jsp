<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang='ko'>
<head>
    <meta charset='utf-8' />
    <title>Spring Example</title>
</head>
<body>    
    <!-- GET 파라미터 전송 테스트 -->
    <h1>100+200=?</h1>
    <a href='${pageContext.request.contextPath}/params/get.do?answer=100'>100</a>
    <a href='${pageContext.request.contextPath}/params/get.do?answer=200'>200</a>
    <a href='${pageContext.request.contextPath}/params/get.do?answer=300'>300</a>
    <a href='${pageContext.request.contextPath}/params/get.do?answer=400'>400</a>
    <a href='${pageContext.request.contextPath}/params/get.do?answer=500'>500</a>
    
    <hr />
    
    <!-- POST 파라미터 전송 테스트 -->
    <h1>당신의 이름과 나이를 입력해 주세요.</h1>
    <form name='form' method='post' action='${pageContext.request.contextPath}/params/post.do'>
        <label for='user_name'>이름</label>
        <input type='text' name='user_name' id='user_name' />
        &nbsp;
        <label for='user_age'>나이</label>
        <input type='text' name='user_age' id='user_age' />
        &nbsp;
        <button type='submit'>전송</button>
    </form>
    
    <hr />
    
    
    <!--  PATH 파라미터 전송 테스트 -->
    <h1>PATH 파라미터 전송 테스트 </h1>
    <div>
        <a href='${pageContext.request.contextPath}/params/path1/hello/world/1234'>
            hello/world/1234</a>
    </div>
    <div>
        <a href='${pageContext.request.contextPath}/params/path2/foo/bar/5678'>
            foo/bar/5678</a>
    </div>
    
</body>
</html>


