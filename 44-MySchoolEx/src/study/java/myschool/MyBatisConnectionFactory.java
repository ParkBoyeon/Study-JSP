package study.java.myschool;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	/** 데이터베이스 접속 객체 */
	// import org.spache.ibatis.session.SqlSessionFactory
	private static SqlSessionFactory sqlSessionFactory;
	
	/** XML에 명시된 접속 정보를 읽어들인다. */
	// 클래스 초기화 블럭 : 클래스 변수의 복잡한 초기화에 사용된다.
	// 클래스가 처음 로딩될 때 한번만 수행된다.
	static {
		// 접속 정보를 명시하고 있는 XML의 경로 읽기
		// --> import java.io.Reader;
		// --> import org.apache.ibatis.io.Resources;
		try {
			Reader reader = Resources.getResourceAsReader("study/java/myschool/config.xml");
			
			// sqlSessionFactory 가 존재하지 않는다면 생성한다.
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 데이터베이스 접속 객체를 통해 DATABASE에 접속한 세션을 리턴한다. */
	// --> import org.apache.ibatis.session.SqlSession;
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}

}
