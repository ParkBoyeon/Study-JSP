package study.spring.simplespring.service.impl;

import org.springframework.stereotype.Service;

import study.spring.simplespring.service.ProfessorService;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Override
	public String addData() {
		return "교수데이터를 추가합니다.";
	}

	@Override
	public String getDataItem() {
		return "교수데이터의 상세정보를 조회합니다.";
	}

	@Override
	public String getDataList() {
		return "교수목록을 조회합니다.";
	}

	@Override
	public String editData() {
		return "교수데이터를 수정합니다.";
	}

	@Override
	public String deleteData() {
		return "교수데이터를 삭제합니다.";
	}
	

}
 