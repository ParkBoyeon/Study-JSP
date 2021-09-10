package study.spring.simplespring.vo;

/** 간단한 Pojo 클래스 */
public class Value {
	private int x;
	private int y;
	
	public int getX()		{ return x; }
	public void setX(int x) { this.x = x; }
	
	public int getY()		{ return y; }
	public void setY(int y) { this.y = y; }
	
	@Override
	public String toString() {
		return "Value [x=" + x + ", y=" + y + "]";
	}
	
}
