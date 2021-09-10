package study.spring.simplespring.vo;

/** 다른 클래스의 객체를 생성자를 통해 주입받아야 하는 클래스 */
public class Calc3 {
	private Value v;
	
	public Calc3(Value v) {
		this.v = v;
	}
	
	
	public int sum() {
		
		return v.getX() + v.getY();
	}

}
