package study.java.myschool.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.java.myschool.model.Student;
import study.java.myschool.service.StudentService;

public class StudentServiceImpl implements StudentService{
	
	/** MyBatis의 Mapper를 호출하기 위한 SqlSession 객체 */
	// --> import org.apache.ibatis.session.SqlSession;
	private SqlSession sqlSession;
	Logger logger;
	
	/** 생성자. SqlSession을 전달받는다. */
	public StudentServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}

	/** 학생 저장하기 -> 저장 후 저장된 결과를 조회하여 리턴한다. */
	@Override
	public void addStudent(Student stud) 
			throws NullPointerException, Exception {
		try {
			int result = sqlSession.insert("StudentMapper.insertStudentItem", stud);
				if (result == 0) {
					throw new NullPointerException();
				}
			} catch(NullPointerException e) {
				sqlSession.rollback();
				throw new Exception("저장된 데이터가 없습니다.");
			} catch(Exception e) {
				sqlSession.rollback();
				logger.error(e.getLocalizedMessage());
				throw new Exception("데이터 저장에 실패했습니다.");
			} finally {
				logger.debug("저장된 데이터 >> " + stud.toString());
				sqlSession.commit();
				sqlSession.close();
				
			}
		
	}
	
	
	/** 학생 저장하기 -> 수정된 결과를 조회하여 리턴한다. */
	@Override
	public void editStudent(Student stud) 
			throws NullPointerException, Exception {
		try {
			int result = sqlSession.update("StudentMapper.updateStudentItem", stud);
			if(result==0) {
				throw new NullPointerException();
			} 
		} catch (NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("변경된 데이터가 없습니다.");
		} catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 수정에 실패했습니다.");
		} finally {
			logger.debug("수정된 데이터 >> " + stud.toString());
			sqlSession.commit();
			sqlSession.close();
		}
	}
	
	@Override
	public void deleteStudent(Student stud) throws NullPointerException, Exception {
		// 3) 학과 데이터 삭제
		try {
			int result = sqlSession.delete("StudentMapper.deleteStudentItem", stud);
			if (result == 0) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("삭제된 데이터가 없습니다.");
		} catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 삭제에 실패했습니다.");
		} finally {
			
			sqlSession.commit();
			sqlSession.close();
		}
		
	}


	/** 학과 데이터 조회하기 (단일 행) */
	@Override
	public Student getStudentItem(Student stud) 
			throws NullPointerException, Exception {
		Student result = null;
		
		try {
			result = sqlSession.selectOne("StudentMapper.selectStudentItem", stud);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("조회된 데이터가 없습니다.");
		} catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		} 
		return result;
	}

	/** 학과 데이터 조회하기 (다중 행)*/
	@Override
	public List<Student> getStudentList() 
			throws NullPointerException, Exception {
		List<Student> result = null;
		
		try {
			result = sqlSession.selectList("StudentMapper.selectStudentList", null);
			if (result ==null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			sqlSession.rollback();
			throw new Exception("조회된 데이터가 없습니다.");
		} catch(Exception e) {
			sqlSession.rollback();
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		} finally {
			
		}
		
		return result;
	}

	

}
