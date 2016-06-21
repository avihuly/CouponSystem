package com.couponproject.beans;

public class Customer {
	//
	// Attributes
	//

	// Instants variables
	private long id;
	private String custName;
	private String password;
	
	// Constructors
	public Customer(long id, String custName, String password) {
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	
	public Customer(String custName, String password) {
		this.custName = custName;
		this.password = password;
	}

	//
	// Methods - getters & setters
	//
	
	// Get ID
	public long getId() {
		return id;
	}

	//Get Customer name
	public String getCustName() {
		return custName;
	}

	//Get Password
	public String getPassword() {
		return password;
	}

	//Set ID
	public void setId(long id) {
		this.id = id;
	}

	//Set Customer name
	public void setCustName(String custName) {
		this.custName = custName;
	}

//	Set Password
	public void setPassword(String password) {
		this.password = password;
	}

	//toString
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}
	
	
	
}
