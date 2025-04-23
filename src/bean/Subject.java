package bean;

import java.io.Serializable;

public class Subject implements Serializable{
	private String cd;
	private String name;
	private School school;

	public String getCd(){
		return cd;
	}
	public String getName(){
		return name;
	}
	public School getSchool(){
		return school;
	}

	public void setCd(String cd){
		this.cd =cd;
	}
	public void setName(String name){
		this.name=name;
	}

	public void setSchool(School school){
		this.school=school;
	}
}
