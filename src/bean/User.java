package bean;

import java.io.Serializable;

public class User implements Serializable{
	private boolean isAuthenticated;

	public boolean getIsAuthenticated(){
		return isAuthenticated;
	}

	public void setIsAuthenticated(boolean isAuthenticated){
		this.isAuthenticated=isAuthenticated;
	}
}
