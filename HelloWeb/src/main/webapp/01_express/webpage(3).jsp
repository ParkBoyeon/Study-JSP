<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    String[] list = { "PHP", "JSP", "ASP" };
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <ul>
        <%
            for (int i = 0; i < list.length; i++) {
                out.println("<li>" + list[i] + "</li>");
            }
        %>
    </ul>
    <ol>
        <% for (int i = 0; i < list.length; i++) { %>
        <li><%=list[i]%></li>
        <% } %>
    </ol>
</body>
</html>
