package bean;

public class TestListStudent {
	private String subjectName;
	private String 	subjectCd;
	private int num;
	private int point;

	public String getSubjectName(){
		return subjectName;
	}
	public String getSubjectCd(){
		return subjectCd;
	}
	public int getNum(){
		return num;
	}
	public int getPoint(){
		return point;
	}


	public void setSubjectName(String subjectName){
		this.subjectName=subjectName;
	}
	public void setSubjectCd(String subjectCd){
		this.subjectCd=subjectCd;
	}
	public void setNum(int num){
		this.num=num;
	}
	public void setPoint(int point){
		this.point=point;
	}

}
