<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%!
    /** 메서드나 상수를 정의하기 위한 선언블록. 블록을 시작하는 괄호에 "!"가 포함된다. */
    public String getMessage() {
        String msg = "Hello JSP";
        return msg;
    }
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <%
        String msg1 = getMessage();
        out.print("<h1>" + msg1 + "</h1>");
    %>

    <% out.print("<h1>" + getMessage() + "</h1>"); %>

    <h1><%=getMessage()%></h1>
</body>
</html>
