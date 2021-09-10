package study.java.helloworld.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.java.helloworld.model.HelloWorld;
import study.java.helloworld.service.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService{
	
	SqlSession sqlSession;
	Logger logger;
	
	public HelloWorldServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}

	@Override
	public void addHelloWorld(HelloWorld hw) throws Exception {
		try {
			int result = sqlSession.insert("HelloWorldMapper.add_helloworld", hw);
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
				logger.debug("저장된 데이터 >> " + hw.toString());
				sqlSession.commit();
				sqlSession.close();
			}
	}

	@Override
	public void editHelloWorld(HelloWorld helloworld) throws Exception {
		try {
			int result = sqlSession.update("HelloWorldMapper.edit_helloworld", helloworld);
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
			logger.debug("수정된 데이터 >> " + helloworld.toString());
			sqlSession.commit();
			sqlSession.close();
		}
		
	}

	@Override
	public void deleteHelloWorld(HelloWorld helloworld) throws Exception {
		try {
			int result = sqlSession.delete("HelloWorldMapper.remove_helloworld", helloworld);
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

	@Override
	public HelloWorld getHelloWorldItem(HelloWorld helloworld) throws Exception {
		HelloWorld result = null;
		
		try {
			result = sqlSession.selectOne("HelloWorldMapper.get_helloworld_item", helloworld);
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
	
	@Override
	public HelloWorld getHelloWorldItemLogin(HelloWorld helloworld) throws Exception {
		HelloWorld result = null;
		
		try {
			result = sqlSession.selectOne("HelloWorldMapper.get_helloworld_item_login", helloworld);
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
	

	@Override
	public List<HelloWorld> getHelloWorldList(HelloWorld helloworld) throws Exception {
		List<HelloWorld> result = null;
		
		try {
			result = sqlSession.selectList("HelloWorldMapper.get_helloworld_list", helloworld);
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
