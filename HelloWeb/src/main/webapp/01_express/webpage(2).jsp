<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    String color = "blue";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <%
        if (color.equals("red")) {
            out.println("<h1 style='color: red'>Hello JSP</h1>");
        } else {
            out.println("<h1 style='color: blue'>Hello JSP</h1>");
        }
    %>

    <% if (color.equals("red")) { %>
    <h2 style='color: red'>안녕하세요. JSP</h2>
    <% } else { %>
    <h2 style='color: blue'>안녕하세요. JSP</h2>
    <% } %>
</body>
</html>
