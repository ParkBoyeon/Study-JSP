package study.java.myschool.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.java.myschool.model.Professor;
import study.java.myschool.service.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService{
	
	/** MyBatis의 Mapper를 호출하기 위한 SqlSession 객체 */
	// --> import org.apache.ibatis.session.SqlSession;
	SqlSession sqlSession;
	Logger logger;

	/** 생성자. SqlSession을 전달받는다. */
	public ProfessorServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}
	
	/** 교수 저장하기 -> 저장 후 저장된 결과를 조회하여 리턴한다. */
	@Override
	public void addProfessor(Professor professor) throws Exception {
		try {
		int result = sqlSession.insert("ProfessorMapper.insertProfessorItem", professor);
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
			logger.debug("저장된 데이터 >> " + professor.toString());
			sqlSession.commit();
			sqlSession.close();
			
		}
		
	}
	/** 교수 저장하기 -> 수정된 결과를 조회하여 리턴한다. */
	@Override
	public void editProfessor(Professor professor) 
			throws Exception {
		try {
			int result = sqlSession.update("ProfessorMapper.updateProfessorItem", professor);
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
			logger.debug("수정된 데이터 >> " + professor.toString());
			sqlSession.commit();
			sqlSession.close();
		}
		
		
	}
	
	/** 교수 삭제하기 */
	@Override
	public void deleteProfessor(Professor professor) 
			throws Exception {
		try {
			int result = sqlSession.delete("ProfessorMapper.deleteProfessorItem", professor);
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

	/** 교수 데이터 조회하기 (단일 행) */
	@Override
	public Professor getProfessorItem(Professor professor) 
			throws Exception {
		Professor result = null;
		
		try {
			result = sqlSession.selectOne("ProfessorMapper.selectProfessorItem", professor);
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
	public List<Professor> getProfessorList(Professor professor) 
			throws Exception {
		List<Professor> result = null;
		
		try {
			result = sqlSession.selectList("ProfessorMapper.selectProfessorList", professor);
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
