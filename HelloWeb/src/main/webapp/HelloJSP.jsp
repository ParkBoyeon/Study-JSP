<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    // 문자열 변수 생성
    String message_eng = "Hello JSP";
    String message_kor = "안녕하세요. 웹 프로그래밍";

    // 이클립스의 콘솔에 출력
    System.out.println(message_eng + "/" + message_kor);
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>

<body>
    <h1> <% out.println(message_eng); %> </h1>
    <h2> <%=message_kor%> </h2>
</body>
</html>

