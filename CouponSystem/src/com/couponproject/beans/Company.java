package com.couponproject.beans;

public class Company {
	private long id;
	private String compName;
	private String password;
	private String email;

	
	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}


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
}
