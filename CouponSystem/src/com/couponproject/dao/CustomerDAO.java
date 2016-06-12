package com.couponproject.dao;

import java.sql.SQLException;
import java.util.Collection;
import com.couponproject.beans.*;


// this interface specify's all the functions 
// of a Customer in relation to the data base
public interface CustomerDAO {
	// ************
	// Methods list
	// ************
	
	// This method should take a Customer instance as argument 
	// and add its details to DB
	public void createCustomer(Customer custumer) throws SQLException;
	
	// This method should take a Customer instance as argument 
	// and remove its details from DB
	public void removeCustomer(Customer custumer) throws SQLException;
	
	// This method should take a Customer instance as argument 
	// and update its details to DB
	public void updateCustomer(Customer custumer);
	
	// This method should take id (long) as argument 
	// and return a corresponding Customer instance
	public Customer getCustomer(long id);
	
	// This method should return a Collection of all Customer instances
	public Collection<Customer> getAllCustomer();
	
	// This method should return a Collection of all Coupon instances of a specific Customer instance
	public Collection<Coupon> getCoupons();
	
	// This method should take customer name and password as argument 
	// and return a boolean indicating a successful login or not
	public boolean login(String custNmae, String password);
	
}
