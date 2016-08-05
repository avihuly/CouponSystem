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
	 * Holds the ID of this Company saved in the DB
	 * @see #id
	 */
	private long id;
	/**
	 * Holds this Company's name
	 * @see #compName
	 */
	private String compName;
	/**
	 * Holds this Company's password for entering the Coupon System 
	 * @see #password
	 */
	private String password;
	/**
	 * Hold this Company's e-mail
	 * @see #email
	 */
	private String email;

	// Constructors
	/**
	 * Construct a Company with a given iD, Name, Password and e-mail
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
	 * @param compName
	 * @param password
	 * @param email
	 */
	public Company(String compName, String password, String email) {
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	/**
	 * Constructs a Company with no given parameters
	 */
	public Company(){};

	//
	// Methods - getters & setters
	//
	
	//Get ID
	/**
	 * Returns this Company's ID
	 * @return This Company's ID
	 */
	public long getId() {
		return id;
	}

	//Get Company name
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


	//Set ID
	/**
	 * Sets a new ID for this Company
	 * @param id A new ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	//Set Company name
	/**
	 * Sets new Company Name
	 * @param compName A new Company Name
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}

	//Set Password
	/**
	 * Sets a new Password for this Company
	 * @param password New Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	//	Set E-mail
	/**
	 * Sets new e-mail address for this Compant
	 * @param email new e-mail address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	//toString
	//TODO: remove password?
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
