package com.model;

public class Account {

	private String id;
	private String password;
    	
	public Account() {

		id = password = null;
	}
	
	public Account(String id, String pwd) {
		
		setId(id);
		setPassword(pwd);
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	
	public boolean accountCompare(Account account){
		
		boolean result = true;
		
		if(!this.getId().equals(account.getId())) result = false;
		else if(!this.getPassword().equals(account.getPassword())) result = false;
		
		return result;
	}
	
	@Override
	public String toString() {
		return "[Id: " + getId() + ", Password: " + getPassword() + "]";
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Account))return false;
	    Account otherAccount = (Account)other;
	    if(accountCompare(otherAccount)) return true;
	    else return false;
	}
	
	@Override
	public int hashCode(){
		  return 0;
	}
}