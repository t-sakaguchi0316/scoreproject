package bean;

public class Teacher implements java.io.Serializable {

	private String id;
	private String password;
	private String name;
	private school school;

	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName(){
		return name;
	}
	
	
	
	
	public void setId(int id) {
		this.id=id;
	}
	public void setLogin(String login) {
		this.login=login;
	}
	public void setPassword(String password) {
		this.password=password;
	}
}
