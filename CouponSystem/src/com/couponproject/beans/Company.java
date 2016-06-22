package com.couponproject.beans;

public class Company {
	//
	// Attributes
	//

	// Instants variables
	private long id;
	private String compName;
	private String password;
	private String email;

	// Constructors
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

	//
	// Methods - getters & setters
	//
	
	//Get ID
	public long getId() {
		return id;
	}

	//Get Company name
	public String getCompName() {
		return compName;
	}

	//Get Password
	public String getPassword() {
		return password;
	}

	//Get E-mail
	public String getEmail() {
		return email;
	}


	//Set ID
	public void setId(long id) {
		this.id = id;
	}

	//Set Company name
	public void setCompName(String compName) {
		this.compName = compName;
	}

	//Set Password
	public void setPassword(String password) {
		this.password = password;
	}

	//	Set E-mail
	public void setEmail(String email) {
		this.email = email;
	}

	//toString
	//TODO: remove password?
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}
	
	
}
