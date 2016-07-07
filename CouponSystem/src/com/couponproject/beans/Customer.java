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

	//
	// other Methods
	//
	
	//toString
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
	}

	// HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custName == null) ? 0 : custName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (custName == null) {
			if (other.custName != null)
				return false;
		} else if (!custName.equals(other.custName))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
		
}
