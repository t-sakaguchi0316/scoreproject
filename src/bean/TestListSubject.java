package bean;

import java.util.Map;
//ゲッターセッターが足りない
public class TestListSubject {
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer, Integer> points;

	public int getEntYear(){
		return entYear;
	}
	public String getStundentNo(){
		return studentNo;
	}
	public String getStudentName(){
		return studentName;
	}
	public String  getClassNum(){
		return classNum;
	}
	public Map<Integer,Integer> getPoints(){
		return points;
	}


	public void setEntYear(int entYear){
		this.entYear=entYear;
	}
	public void setStudentNo(String studentNo){
		this.studentNo=studentNo;
	}
	public void setStudentName(String studentName){
		this.studentName=studentName;
	}
	public void setClassNum(String classNum){
		this.classNum=classNum;
	}
	public void setPoinst(Map<Integer, Integer> points){
		this.points=points;
	}


}
