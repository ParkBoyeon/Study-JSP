<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="study.helloweb.model.Department"%>
<%
    request.setCharacterEncoding("UTF-8");          //파라미터 수신에 사용할 문자셋 설정
    String deptno = request.getParameter("deptno"); // 파라미터 추출
    Department department = null;                   // 검색 결과를 저장할 객체

    // 검색어에 따른 처리
    // -> 최초 실행시 파라미터가 없는 경우를 대비해야 하므로 deptno값이 존재하는 경우만 수행한다.
    if (deptno != null && !deptno.equals("")) {
        // 데이터를 저장할 객체를 할당한다.
        department = new Department();

        // deptno가 정해진 범위 안에서만 동작하도록 if문을 구성.
        if (deptno.equals("101")) {
            department.setDeptno(101);
            department.setDname("컴퓨터공학과");
            department.setLoc("1호관");
        } else if (deptno.equals("102")) {
            department.setDeptno(102);
            department.setDname("멀티미디어학과");
            department.setLoc("2호관");
        } else if (deptno.equals("201")) {
            department.setDeptno(201);
            department.setDname("전자공학과");
            department.setLoc("3호관");
        } else if (deptno.equals("202")) {
            department.setDeptno(202);
            department.setDname("기계공학과");
            department.setLoc("4호관");
        }
    }
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>학과정보</h1>

    <!-- 검색폼 영역 -->
    <form method="post" action="department.jsp">
        <div>
            <label for="deptno">학과번호: </label>
            <input type="text" name="deptno" value="<%=deptno%>" />
            <button type="submit">검색</button>
        </div>
    </form>
    <hr />

    <!-- 결과표시 영역 -->
    <% if (department == null) { %>
    <h2>학과 번호로 검색하세요.</h2>
    <% } else { %>
    <table>
        <tr>
            <th>학과번호</th>
            <td><%=department.getDeptno()%></td>
        </tr>
        <tr>
            <th>학과이름</th>
            <td><%=department.getDname()%></td>
        </tr>
        <tr>
            <th>학과위치</th>
            <td><%=department.getLoc()%></td>
        </tr>
    </table>
    <% } %>
</body>
</html>
