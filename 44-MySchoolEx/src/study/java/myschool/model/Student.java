package study.java.myschool.model;

public class Student {
	private int studno;
	private String name;
	private String userid;
	private int grade;
	private String idnum;
	private String birthdate;
	private String tel;
	private int height;
	private int weight;
	private int deptno;
	private int profno;
	
	public int getStudno() {
		return studno;
	}
	public void setStudno(int studno) {
		this.studno = studno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getProfno() {
		return profno;
	}
	public void setProfno(int profno) {
		this.profno = profno;
	}
	@Override
	public String toString() {
		return "Student [studno=" + studno + ", name=" + name + ", userid=" + userid + ", grade=" + grade + ", idnum="
				+ idnum + ", birthdate=" + birthdate + ", tel=" + tel + ", height=" + height + ", weight=" + weight
				+ ", deptno=" + deptno + ", profno=" + profno + "]";
	}
	
	
	
}
