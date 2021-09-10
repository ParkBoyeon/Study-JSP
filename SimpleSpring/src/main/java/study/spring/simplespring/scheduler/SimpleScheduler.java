package study.spring.simplespring.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import study.spring.simplespring.service.ProfessorService;

@Slf4j
@Controller
public class SimpleScheduler {
	
	@Autowired
	ProfessorService professorService;
	
	public void everySeconds() {
		log.debug("1초 단위로 실행되는 작업 >> " + professorService.addData());
	}
	
	public void everyMinites() {
		log.debug("분 단위로 실행되는 작업 >> " + professorService.editData());
	}
	
	public void every10Seconds() {
		log.debug("10초마다 한번씩 실행되는 작업 >> " + professorService.getDataItem());
	}
	
	public void everyMidnight() {
		log.debug("매일 자정에 실행되는 작업 >> " + professorService.getDataList());
	}

}
