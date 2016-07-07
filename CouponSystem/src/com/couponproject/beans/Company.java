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
	
	public Company(){};

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
