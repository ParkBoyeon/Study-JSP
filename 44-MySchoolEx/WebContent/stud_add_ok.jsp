<%@page import="study.java.myschool.service.impl.StudentServiceImpl"%>
<%@page import="study.java.myschool.service.StudentService"%>
<%@page import="study.java.myschool.MyBatisConnectionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="study.java.myschool.model.Student"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String userid = request.getParameter("userid");
	int grade = Integer.parseInt(request.getParameter("grade"));
	String idnum = request.getParameter("idnum");
	String birthdate = request.getParameter("birthdate");
	String tel = request.getParameter("tel");
	int height = Integer.parseInt(request.getParameter("height"));
	int weight = Integer.parseInt(request.getParameter("weight"));
	int deptno = Integer.parseInt(request.getParameter("deptno"));
	int profno = Integer.parseInt(request.getParameter("profno"));

	Logger logger = LogManager.getFormatterLogger("stud_add_ok.jsp");

	Student stud = new Student();
	stud.setName(name);
	stud.setUserid(userid);
	stud.setGrade(grade);
	stud.setIdnum(idnum);
	stud.setBirthdate(birthdate);
	stud.setTel(tel);
	stud.setHeight(height);
	stud.setWeight(weight);
	stud.setDeptno(deptno);
	stud.setProfno(profno);
	
	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();

	StudentService studentService = new StudentServiceImpl(sqlSession, logger);
	Student result = null;
	
	try {
		studentService.addStudent(stud);
	} catch (NullPointerException e) {
		logger.error("저장된 데이터가 없습니다.");
	} catch (Exception e) {
		logger.error("데이터 저장에 실패했습니다." + e.getMessage());
	}
	
	sqlSession.close();
	
	response.sendRedirect("stud_view.jsp?studno=" + stud.getStudno());
%>
