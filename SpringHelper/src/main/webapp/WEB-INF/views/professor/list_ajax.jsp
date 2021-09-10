<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP</title>
</head>
<body>
	<h1>교수관리</h1>
	<a href="${pageContext.request.contextPath}/professor_ajax/add.do">[교수추가]</a>
	
	<!-- 검색폼 -->
	<form method="get" action="${pageContext.request.contextPath}/professor_ajax/list.do">
        <label for="keyword">검색어: </label>
        <input type="search" name="keyword" id="keyword" placeholder="이름 검색" value="${keyword}" />
        <button type="submit">검색</button>
    </form>

    <hr />
	
	<!-- 조회 결과 목록 -->
	<table border="1">
        <thead>
            <tr>
                <th width="80" align="center">교수번호</th>
                <th width="100" align="center">교수이름</th>
                <th width="100" align="center">아이디</th>
                <th width="80" align="center">직급</th>
                <th width="50" align="center">급여</th>
                <th width="100" align="center">입사일</th>
                <th width="70" align="center">보직수당</th>
                <th width="130" align="center">소속학과이름</th>
                <th width="100" align="center">소속학과위치</th>
            </tr>
        </thead>
        <tbody id="list">
            <c:choose>
                <%-- 조회결과가 없는 경우 --%>
                <c:when test="${output == null || fn:length(output) == 0}">
                    <tr>
                        <td colspan="9" align="center">조회결과가 없습니다.</td>
                    </tr>
                </c:when>
                <%-- 조회결과가 있는 경우 --%>
                <c:otherwise>
                    <%-- 조회 결과에 따른 반복 처리 --%>
                    <c:forEach var="item" items="${output}" varStatus="status">
                        <%-- 출력을 위해 준비한 교수이름 변수 --%>
                        <c:set var="name" value="${item.name}" />

                        <%-- 검색어가 있다면? --%>
                        <c:if test="${keyword != ''}">
                            <%-- 검색어에 <mark> 태그를 적용하여 형광팬 효과 준비 --%>
                            <c:set var="mark" value="<mark>${keyword}</mark>" />
                            <%-- 출력을 위해 준비한 교수이름에서 검색어와 일치하는 단어를 형광팬 효과로 변경 --%>
                            <c:set var="name" value="${fn:replace(name, keyword, mark)}" />
                        </c:if>
                        
                        <%-- 상세페이지로 이동하기 위한 URL --%>
                        <c:url value="/professor_ajax/view.do" var="viewUrl">
                            <c:param name="profno" value="${item.profno}" />
                        </c:url>
                        
                        <tr>
                            <td align="center">${item.profno}</td>
                            <td align="center"><a href="${viewUrl}">${name}</a></td>
                            <td align="center">${item.userid}</td>
                            <td align="center">${item.position}</td>
                            <td align="center">${item.sal}</td>
                            <td align="center">${item.hiredate}</td>
                            <td align="center">${item.comm}</td>
                            <td align="center">${item.dname}</td>
                            <td align="center">${item.loc}</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
	
	<!-- 전체 페이지 수가 2페이지 이상인 경우 "더보기" 버튼 노출 -->
	<c:if test="${pageData.totalPage > 1}">
	<button id="btnMore">더보기</button>
	</c:if>
	
	<!-- Handlebar 템플릿 코드 -->
	<script id="prof-list-tmpl" type="text/x-handlebars-template">	
		{{#each item}}
		<tr>
			<td align="center">{{profno}}</td>
			<td align="center">
				<a href="${pageContext.request.contextPath}/professor_ajax/view.do?profno={{profno}}">{{name}}</a>
			</td>
			<td align="center">{{userid}}</td>
			<td align="center">{{position}}</td>
			<td align="center">{{sal}}</td>
			<td align="center">{{hiredate}}</td>
			<td align="center">{{comm}}</td>
			<td align="center">{{dname}}</td>
			<td align="center">{{loc}}</td>
		</tr>
		{{/each}}
	</script>
	
	<!--Google CDN 서버로부터 jQuery 참조 -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Handlebar CDN 참조 -->
    <script src="//cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.4.2/handlebars.min.js"></script>
    <!-- jQuery Ajax Setup -->
    <script src="${pageContext.request.contextPath}/assets/plugins/ajax/ajax_helper.js"></script>

	<!-- USer code -->
	<script>
		let nowPage = 1;		// 현재 페이지의 기본값
		
		$(function() {
			/** 더보기 버튼에 대한 이벤트 정의 */
			$("#btnMore").click(function() {
				// 다음 페이지를 요청하기 위해 페이지 변수 1 증가
				nowPage++;
				
				// Restfun API에 GET방식 요청
				$.get("${pageContext.request.contextPath}/professor", {
					"page": nowPage		// 페이지 번호는 GET 파라미터로 전송한다.
				}, function(json) {
					var source = $("#prof-list-tmpl").html();	// 템플릿 코드 가져오기
					var template = Handlebars.compile(source);	// 템플릿 코드 컴파일
					var result = template(json);	// 템플릿 컴파일 결과물에 json 전달
					$("#list").append(result);		// 최종 결과물을 #list 요소에 추가한다.
					
					// 현재 페이지 번호가 전체 페이지 수에 도달했다면 더보기 버튼을 숨긴다.
					if (json.meta.totalPage <= nowPage) {
						$("#btnMore").hide();
					}
				});
			});
		});
	</script>
</body>
</html>