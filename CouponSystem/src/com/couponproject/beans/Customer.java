package com.couponproject.beans;

/**
 * Represents a Customer in the Coupon System
 * <p>This Customer can purchase Coupons</p>
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 *
 */
public class Customer {
	//
	// Attributes
	//

	// Instants variables
	/**
	 * Holds the ID of this Customer saved in the DB
	 * @see Customer #id
	 */
	private long id;
	/**
	 * Holds the Name of this Customer
	 * @see #custName 
	 */
	private String custName;
	/**
	 * Holds the Password of this Customer
	 * @see #password 
	 */
	private String password;
	
	// Constructors
	/**
	 * Constructs a Customer with a given ID, Name and Password
	 * @param id
	 * @param custName
	 * @param password
	 */
	public Customer(long id, String custName, String password) {
		this.id = id;
		this.custName = custName;
		this.password = password;
	}
	
	/**
	 * Constructs a Customer with a given Name and Password
	 * @param custName
	 * @param password
	 */
	public Customer(String custName, String password) {
		this.custName = custName;
		this.password = password;
	}
	
	//
	// Methods - getters & setters
	//
	
	// Get ID
	/**
	 * Returns this Customer's ID
	 * @return This Customer's ID
	 */
	public long getId() {
		return id;
	}

	//Get Customer name
	/**
	 * Returns this Customer's Name
	 * @return This Customer's Name
	 */
	public String getCustName() {
		return custName;
	}

	//Get Password
	/**
	 * Returns this Customer's Password
	 * @return This Customer's Password
	 */
	public String getPassword() {
		return password;
	}

	//Set ID
	/**
	 * Sets a new ID for this Customer
	 * @param id A new ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	//Set Customer name
	/**
	 * Sets a new Name for this Customer
	 * @param custName A New Name
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	//	Set Password
	/**
	 * Sets a new Password for this Customer
	 * @param password A new Password
	 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custName == null) ? 0 : custName.hashCode());
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}	
}
