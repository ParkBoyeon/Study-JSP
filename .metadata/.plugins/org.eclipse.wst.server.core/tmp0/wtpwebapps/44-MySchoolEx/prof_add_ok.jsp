<%@page import="study.java.myschool.service.impl.ProfessorServiceImpl"%>
<%@page import="study.java.myschool.service.ProfessorService"%>
<%@page import="study.java.myschool.MyBatisConnectionFactory"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="study.java.myschool.model.Professor"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String userid = request.getParameter("userid");
	String position = request.getParameter("position");
	String sal = request.getParameter("sal");
	String hiredate = request.getParameter("hiredate");
	String comm = request.getParameter("comm");
	String deptno = request.getParameter("deptno");

	Logger logger = LogManager.getFormatterLogger("prof_add_ok.jsp");

	Professor prof = new Professor();
	prof.setName(name);
	prof.setUserid(userid);
	prof.setPosition(position);
	prof.setSal(Integer.parseInt(sal));
	prof.setHiredate(hiredate);
	prof.setComm(Integer.parseInt(comm));
	prof.setDeptno(Integer.parseInt(deptno));
	

	SqlSession sqlSession = MyBatisConnectionFactory.getSqlSession();

	ProfessorService professorService = new ProfessorServiceImpl(sqlSession, logger);

	try {
		professorService.addProfessor(prof);
	} catch (NullPointerException e) {
		logger.error("저장된 데이터가 없습니다.");
	} catch (Exception e) {
		logger.error("데이터 저장에 실패했습니다." + e.getMessage());
	}
	
	sqlSession.close();
	
	response.sendRedirect("prof_view.jsp?profno=" + prof.getProfno());
%>
