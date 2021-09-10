package study.spring.simplespring.vo;

/** 멤버변수, 메서드, 생성자를 갖는 형태 */
public class Calc2 {
	private int x;
	private int y;

	public Calc2 (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int sum() {
		return this.x + this.y;
	}
	
}
