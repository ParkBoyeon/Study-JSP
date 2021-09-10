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
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.css" />
</head>
<body>
	<h1>${date} 박스오피스</h1>
	
	<!-- 검색폼 -->
	<form name="form1" method="get" action="${pageContext.request.contextPath}/retrofit/daily_box_office.do">
		<label for="targetDt">검색일: </label>
		<input type="date" id="date" name="date" value="${date}" />
		<input type="submit" value="검색" />
	</form>
	
	<hr />
	
	<!-- 검색 결과 -->
	<table border="1">
		<thead>
			<tr>
				<th>순위</th>
				<th>제목</th>
				<th>개봉일</th>
				<th>누적매출액</th>
				<th>오늘관객수</th>
				<th>누적관객수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<%-- 검색 결과가 존재할 경우 목록을 표 형식으로 출력 --%>
				<c:when test="${list !=null && list.size() > 0}">
					<!-- list에 담긴 내용을 반복문으로 출력 -->
					<c:forEach var="item" items="${list}" varStatus="status">
						<tr>
							<td>${item.rank}</td>
							<td>${item.movieNm}</td>
							<td>${item.openDt}</td>
							<td><fmt:formatNumber value="${item.salesAcc}" pattern="#,###" />원</td>
							<td><fmt:formatNumber value="${item.audiCnt}" pattern="#,###" />명</td>
							<td><fmt:formatNumber value="${item.audiAcc}" pattern="#,###" />명</td>
						</tr>
					</c:forEach>
				</c:when>
				<%-- 검색 결과가 존재하지 않을 경우 --%>
				<c:otherwise>
					<tr>
						<td colspan="6" align="center">
							조회내역이 없습니다.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<hr />
	
	<!-- 그래프를 표시할 위치 -->
	<div style="width: 1028px; height: 740px">
		<canvas id="myChart"></canvas>
	</div>

	<!-- chartjs cdn 참조 -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
	
	<!-- 데이터 시각화 구현 -->
	<script>
		var ctx = document.getElementById('myChart').getContext('2d');
		var myChart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: [${movieNmStr}],			// 각각의 bar에 표시할 x축 텍스트들 (영화제목)
				datasets: [{						
					label: '${date} 박스오피스',		// 범주
					data: [${audiCntStr}],			// 각 bar에 대한 y축 좌표 데이터 (관람객 수)
					backgroundColor: 'rgba(255, 99, 132, 0.2)',		// 각 bar의 배경 색상
					borderColor: 'rgba(255, 99, 132, 1)',			// 각 bar의 테두리 색상
					borderWidth: 1									// 각 bar의 테두리 굵기
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						}
					}]
				}
			}
		});
	</script>
</body>
</html>