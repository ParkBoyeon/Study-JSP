<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" />
</head>
<body>
    <h1>Hello world!</h1>
    
    <!-- 홈 컨트롤러로 접근하기 위한 링크 -->
    <h2>HomeController</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}">home 페이지</a></li>
        <li><a href="${pageContext.request.contextPath}/user_agent.do">UserAgent 확인 예제</a></li>
    </ul>
    
    <!-- Get/Post 테스트 -->
    <h2>ParamController</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/params/home.do">Get/Post 테스트</a></li>
    </ul>
    
    <!-- Cookie 테스트 -->
    <h2>CookieController</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/cookie/write.do">Cookie 테스트</a></li>
    </ul>
    
    <!-- Session 테스트 -->
    <h2>SessionController</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/session/write.do">Session 테스트</a></li>
    </ul>
    
    <!-- AOP 테스트 -->
    <h2>APOController</h2>
    <ul>
    	<li><a href="${pageContext.request.contextPath}/aop/calc.do">AOP 테스트</a></li>
    </ul>
    
    <!-- service -->
    <h2>Service</h2>
    <ul>
    	<li><a href="${pageContext.request.contextPath}/service/professor.do">Service 테스트</a></li>
    </ul>
    
    <!-- 데이터베이스 연동 -->
    <h2>Database</h2>
    <ul>
    	<li><a href="${pageContext.request.contextPath}/department/list.do">학과관리 웹 페이지</a></li>
    </ul>
    
    
    <script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</body>
</html>
