package com.couponproject.beans;

public class Customer {
	private long id;
	private String custName;
	private String password;
	
	// Constructor
	public Customer(long id, String custName, String password) {
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	
	public Customer(String custName, String password) {
		this.custName = custName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getCustName() {
		return custName;
	}

	public String getPassword() {
		return password;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}
	
	
	
}
