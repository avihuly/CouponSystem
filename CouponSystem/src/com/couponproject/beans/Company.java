package com.couponproject.beans;

/**
 * Represents a Company in this Coupon System
 * <p>A Company can own Coupons</p>
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class Company {
	//
	// Attributes
	//

	// Instants variables
	/**
	 * Holds the ID of this Company
	 */
	private long id;
	/**
	 * Holds this Company's name
	 */
	private String compName;
	/**
	 * Holds this Company's password for entering the Coupon System 
	 */
	private String password;
	/**
	 * Hold this Company's e-mail
	 */
	private String email;

	// Constructors
	/**
	 * Construct a Company with a given iD, Name, Password and e-mail.
	 * 
	 * This constructor souled be used to load a company instance FROM the DB. 
	 * 
	 * @param id
	 * @param compName 
	 * @param password
	 * @param email 
	 */
	public Company(long id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	/**
	 * Construct a Company with a given Name, Password and e-mail
	 * 
	 * This constructor souled be used to CREATE a new company in the DB.
	 * 
	 * @param compName
	 * @param password
	 * @param email
	 */
	public Company(String compName, String password, String email) {
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	//
	// Methods - getters & setters
	//
	/**
	 * Returns this Company's ID
	 * @return This Company's ID
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns this Company's name
	 * @return This Company's name
	 */
	public String getCompName() {
		return compName;
	}

	//Get Password
	/**
	 * Returns this Company's password
	 * @return This Company's password
	 */
	public String getPassword() {
		return password;
	}

	//Get E-mail
	/**
	 * Returns this Company's e-mail
	 * @return This Company's e-mail
	 */
	public String getEmail() {
		return email;
	}

	//toString
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email + "]";
	}

	// HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compName == null) ? 0 : compName.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Company other = (Company) obj;
		if (compName == null) {
			if (other.compName != null)
				return false;
		} else if (!compName.equals(other.compName))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
