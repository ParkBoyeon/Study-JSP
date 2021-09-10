<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>교수수정</h1>
    <form id="editForm" action="${pageContext.request.contextPath}/professor">
        <% /* action 페이지에서 사용할 WHERE 조건값을 hidden 필드로 숨겨서 전송한다. */ %>
        <input type="hidden" name="profno" value="${output.profno}" />

        <div>
            <label for="dname">교수이름: </label>
            <input type="text" name="name" id="name" value="${output.name}" />
        </div>
        <div>
            <label for="userid">아이디: </label>
            <input type="text" name="userid" id="userid" value="${output.userid}" />
        </div>
        <div>
            <label for="position1">직급: </label>

            <label><input type="radio" name="position" id="position1" value="교수" <c:if test="${output.position == '교수'}">checked</c:if>/>교수</label>
            <label><input type="radio" name="position" id="position2" value="부교수" <c:if test="${output.position == '부교수'}">checked</c:if>/>부교수</label>
            <label><input type="radio" name="position" id="position3" value="전임강사" <c:if test="${output.position == '전임강사'}">checked</c:if>/>전임강사</label>
            <label><input type="radio" name="position" id="position4" value="조교" <c:if test="${output.position == '조교'}">checked</c:if>/>조교</label>
        </div>
        <div>
            <label for="sal">급여: </label>
            <input type="number" name="sal" id="sal" value="${output.sal}" />
        </div>
        <div>
            <label for="hiredate">입사일: </label>
            <input type="date" name="hiredate" id="hiredate" value="${output.hiredate}" />
        </div>
        <div>
            <label for="comm">보직수당: </label>
            <input type="range" name="comm" id="comm" min="0" max="100" value="0" value="${output.comm}" />
        </div>
        <div>
            <label for="deptno">소속학과: </label>
            <select name="deptno" id="deptno">
                <%-- 조회 결과에 따른 반복 처리 --%>
                <c:forEach var="item" items="${deptList}" varStatus="status">
                    <option value="${item.deptno}" <c:if test="${item.deptno == output.deptno}">selected</c:if>>${item.dname}</option>
                </c:forEach>
            </select>
        </div>
        <hr />
        <button type="submit">저장하기</button>
        <button type="reset">다시작성</button>
    </form>
    
     <!--Google CDN 서버로부터 jQuery 참조 -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- jQuery Ajax Form plugin CDN -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
    <!-- jQuery Ajax Setup -->
    <script src="${pageContext.request.contextPath}/assets/plugins/ajax/ajax_helper.js"></script>
    <!-- User Code -->
    <script>
    $(function() {
        // #editForm에 대한 submit이벤트를 가로채서 Ajax요청을 전송한다.
        $("#editForm").ajaxForm({
            // 전송 메서드 지정
            method: "PUT",
            // 서버에서 200 응답을 전달한 경우 실행됨
            success: function(json) {
                console.log(json);
                
                // json에 포함된 데이터를 활용하여 상세페이지로 이동한다.
                if (json.rt == "OK") {
                	window.location = "${pageContext.request.contextPath}/professor_ajax/view.do?profno=" + json.item.profno;
                }
            }
        });
    });
    </script>
</body>
</html>

