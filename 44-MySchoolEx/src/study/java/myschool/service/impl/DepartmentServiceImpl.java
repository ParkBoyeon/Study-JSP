package study.java.myschool.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import study.java.myschool.model.Department;
import study.java.myschool.model.Professor;
import study.java.myschool.model.Student;
import study.java.myschool.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	/** MyBatis의 Mapper를 호출하기 위한 SqlSession 객체 */
	// --> import org.apache.ibatis.session.SqlSession;
	private SqlSession session;

	/** 생성자. SqlSession을 전달받는다. */
	public DepartmentServiceImpl(SqlSession session) {
		this.session = session;
	}

	/** 학과 저장하기 -> 저장 후 저장된 결과를 조회하여 리턴한다. */
	// --> import study.java.myschool.model.Department;
	@Override
	public Department addDepartment(Department params) 
			throws NullPointerException, Exception {
		int row_count = session.insert("DepartmentMapper.add_department", params);

		// 저장된 데이터의 행 수가 없을 경우 강제로 예외를 발생시킨다.
		if (row_count == 0) {
			throw new NullPointerException();
		}
		return session.selectOne("DepartmentMapper.get_department_item", params);
	}

	/** 학과 저장하기 -> 수정된 결과를 조회하여 리턴한다. */
	@Override
	public Department editDepartment(Department params) 
			throws NullPointerException, Exception {
		int row_count = session.update("DepartmentMapper.edit_department", params);
		
		// 수정된 데이터의 행 수가 없을 경우 강제로 예외를 발생시킨다.
		if(row_count == 0) {
			throw new NullPointerException();
		}
		return session.selectOne("DepartmentMapper.get_department_item", params);
	}
	
	/** 학과 삭제하기 */
	@Override
	public void deleteDepartment(Department params) 
			throws NullPointerException, Exception {
		// 1) 학생 데이터 삭제
		// --> import study.java.myschool.model.Student;
		Student stud = new Student();
		stud.setDeptno(params.getDeptno());
		session.delete("StudentMapper.remove_student_by_deptno", stud);
				
		// 2) 교수 데이터 삭제
		// --> import study.java.myschool.model.Professor;
		Professor prof = new Professor();
		prof.setDeptno(params.getDeptno());
		session.delete("ProfessorMapper.remove_professor_by_deptno", prof);
				
		// 3) 학과 데이터 삭제
		int row_count = session.delete("DepartmentMapper.remove_department", params);
				
		// 최종적으로 삭제된 행이 없을 경우 강제로 예외를 발생시킨다.
		if (row_count == 0) {
			throw new NullPointerException();
		}

	}

	/** 학과 데이터 조회하기 (단일 행) */
	@Override
	public Department getDepartmentItem(Department params) 
			throws NullPointerException, Exception {
		Department item = session.selectOne("DepartmentMapper.get_department_item", params);
		
		if(item == null) {
			throw new NullPointerException();
		}
		return item;
	}

	/** 학과 데이터 조회하기 (다중 행)*/
	@Override
	public List<Department> getDepartmentList() 
			throws NullPointerException, Exception {
		List<Department> list = session.selectList("DepartmentMapper.get_department_list", null);
		
		if (list ==null) {
			throw new NullPointerException();
		}
		return list;
	}

}
