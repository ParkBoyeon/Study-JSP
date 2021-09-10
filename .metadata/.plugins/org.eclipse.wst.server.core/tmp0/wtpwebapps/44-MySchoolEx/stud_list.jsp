<%@page import="study.java.myschool.model.Student"%>
<%@page import="java.util.List"%>
<%@page import="study.java.myschool.service.impl.StudentServiceImpl"%>
<%@page import="study.java.myschool.service.StudentService"%>
<%@page import="study.java.myschool.MyBatisConnectionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	Logger logger = LogManager.getFormatterLogger("stud_list.jsp");

	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();
	
	StudentService studentService = new StudentServiceImpl(sqlSession, logger);
	
	List<Student> list = null;
	Student stud = null;
	
	try {
		list = studentService.getStudentList();
	} catch (NullPointerException e) {
		logger.error("조회된 데이터가 없습니다.");
	} catch (Exception e) {
		logger.error("데이터 조회에 실패했습니다." + e.getMessage());
	}
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>My JSP Page</title>
	<!-- Twitter Bootstrap3 & jQuery -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
	<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1 class="page-header">학생목록</h1>
		<p class="text-right">
			<a href="stud_add.jsp" class="btn btn-primary">학생추가</a>
		</p>
		<ul class="list-group">
		<%
			if(list != null) {
				for (int i= 0; i<list.size(); i++) {
					Student temp = list.get(i);
					logger.debug(String.format("조회결과[%d] >> %s", i, temp.toString()));
		%>
		
		<li class="list-group-item">
				<a href="stud_view.jsp?studno=<%=temp.getStudno()%>">
					<%=temp.getStudno()%> / <%=temp.getName()%> / <%=temp.getUserid()%>
					/ <%=temp.getGrade()%> / <%=temp.getIdnum()%> / <%=temp.getBirthdate()%>
					/ <%=temp.getTel()%> / <%=temp.getHeight()%> / <%=temp.getWeight()%>
					/ <%=temp.getDeptno()%> / <%=temp.getProfno()%>
				</a>
			</li>
			
		<%
				}
			}
		%>
		</ul>
	</div>
</body>
</html>
<%
	sqlSession.close();
%>
