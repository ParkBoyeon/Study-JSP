package study.helloweb.model;

import lombok.Data;

/**
 * 회원 정보를 저장하기 위한 클래스
 */
@Data
public class Member {
	// 아이디
	private String userId;
	// 비밀번호
	private String userPw;
	// 이름
	private String userName;
	// 이메일
	private String email;
}
