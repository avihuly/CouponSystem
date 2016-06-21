package com.couponproject.beans;

public class Company {
	//
	//Attributes
	//
	private long id;
	private String compName;
	private String password;
	private String email;

	//
	//Constructors
	//
	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	public Company(String compName, String password, String email) {
		this.compName = compName;
		this.password = password;
		this.email = email;
	}

	//getters
	public long getId() {
		return id;
	}


	public String getCompName() {
		return compName;
	}


	public String getPassword() {
		return password;
	}


	public String getEmail() {
		return email;
	}


	//setters
	public void setId(long id) {
		this.id = id;
	}


	public void setCompName(String compName) {
		this.compName = compName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
