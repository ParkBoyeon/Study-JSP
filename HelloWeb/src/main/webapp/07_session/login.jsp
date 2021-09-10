<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="study.helloweb.model.Member"%>
<%
    // 세션값 가져오기
    Member loginInfo = (Member) session.getAttribute("login_info");
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>My JSP Page</title>
</head>
<body>
    <%
        if (loginInfo == null) {
            // 세션에서 획득한 객체가 없는 경우는 로그인 상태 아님
            // --> 아이디, 비밀번호 입력폼을 표시
    %>
    <form method="post" action="login_ok.jsp">
        <div>
            <input type="text" name="user_id" placeholder="아이디" />
        </div>
        <div>
            <input type="password" name="user_pw" placeholder="비밀번호" />
        </div>
        <button type="submit">로그인</button>
    </form>
    <%
        } else {
            // 그렇지 않은 경우(세션정보가 존재할 경우)는 로그인 중.
            // --> 세션에서 획득한 Member 객체로 원하는 정보 표시
    %>
    <h2>안녕하세요 <%=loginInfo.getUserName()%>님</h2>
    <h3><%=loginInfo.getEmail()%></h3>
    <a href="logout.jsp" class="btn btn-warning">로그아웃</a>
    <% }  %>
</body>
</html>