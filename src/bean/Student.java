package bean;

public class Student {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;


	public String getNo() {
		return no;
	}
	public String getName(){
		return name;
	}
	public int getEntYear(){
		return entYear;
	}
	public String getClassNum(){
		return classNum;
	}
	public boolean getIsAttend(){
		return isAttend;
	}
	public School getSchool(){
		return school;
	}



	public void setNo(String no) {
		this.no=no;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setEntYear(int entYear){
		this.entYear=entYear;
	}
	public void setClassNum(String classNum){
		this.classNum=classNum;
	}
	public void setIsAttend(boolean isAttend){
		this.isAttend=isAttend;
	}
	public void setSchool(School school){
		this.school=school;
	}
}
