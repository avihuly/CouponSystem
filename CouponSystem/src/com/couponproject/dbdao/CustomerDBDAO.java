package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.*;
import com.couponproject.dao.CustomerDAO;
import com.couponproject.exception.CouponSystemException;

// implements CustomerDAO with mysql
public class CustomerDBDAO implements CustomerDAO {
	
	@Override
	public void createCustomer(Customer custumer) throws CouponSystemException {

		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			
			// Insert prepared statement
			PreparedStatement createStmt = myCon.prepareStatement(					
					"insert into "
					+ "customer (CUST_NAME, PASSWORD) "
					+ "values (?,?);"); //id will be assign in the DB

			// Values
			createStmt.setString(1, custumer.getCustName());
			createStmt.setString(2, custumer.getPassword());

			// Execute
			createStmt.executeUpdate();

		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		} 
		
	}

	@Override
	public void removeCustomer(Customer custumer) throws CouponSystemException {
		// Creating a Connection object to the DB
		
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
		
			// Delete prepared statement
			PreparedStatement deleteStmt = myCon.prepareStatement(
					"delete from customer "
					+ "where ID = ? and CUST_NAME = ? and PASSWORD = ?");

			// Values
			deleteStmt.setLong(1, custumer.getId());
			deleteStmt.setString(2, custumer.getCustName());
			deleteStmt.setString(3, custumer.getPassword());
			
			// Execute
			deleteStmt.executeUpdate();
			
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}

	}

	@Override
	public void updateCustomer(Customer custumer) throws CouponSystemException {
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// Update prepared statement
			PreparedStatement updateStmt = myCon.prepareStatement(
					"update customer "
					+ "set CUST_NAME = ? and PASSWORD = ? "
					+ "where ID = ?");

			// Values
			updateStmt.setString(1, custumer.getCustName());
			updateStmt.setString(2, custumer.getPassword());
			updateStmt.setLong(3, custumer.getId());
			
			// Execute
			updateStmt.executeUpdate();
			
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}

	}

	@Override
	public Customer getCustomer(long id) throws CouponSystemException {
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from customer "
					+ "where ID = ?");
			
			// Values
			selectStmt.setLong(1, id);
			
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			
			System.out.println("HERE");
			
			// Processing resultSet into a Customer(bean) instance
			myRs.next();
			Customer customer = new Customer(
					myRs.getLong("ID"),
					myRs.getString("CUST_NAME"),
					myRs.getString("PASSWORD"));

			// Return customer
			return customer;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	@Override
	public Collection<Customer> getAllCustomer() throws CouponSystemException {
		// Creating a Connection object to the DB 
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from customer");
							
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
					
			// Processing resultSet into a Collection of Customer
			//---------------------------------------------------
			//Declaring a List of 'Customer's
			Collection <Customer> customers = new ArrayList<>(); 
					
			// Iterating the resultSet - 
			// each iteration is converted into a Customer instance and added to the List
				while(myRs.next()){
					Customer customer = new Customer(
						myRs.getLong("ID"),
						myRs.getString("CUST_NAME"),
						myRs.getString("PASSWORD"));
					customers.add(customer);
				}

			// Return customer
			return customers;	
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	@Override
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException {
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
		
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				// TODO: read about Relational Division ???
				"SELECT * FROM coupon "
				+ "JOIN customer_coupon "
				+ "ON coupon.ID = customer_coupon.COUPON_ID "
				+ "WHERE customer_coupon.CUST_ID = " + id);		   		

		// Execute and get a resultSet		
		ResultSet myRs = selectStmt.executeQuery();

		// Processing resultSet into a Collection of Coupon
		// ---------------------------------------------------
		// Declaring a List of 'Coupon's
		Collection<Coupon> coupons = new ArrayList<>();

		// Iterating the resultSet -
		// each iteration is converted into a Coupon instance and added to the
		// List
		while (myRs.next()) {
			// Generating Coupon
			Coupon coupon = new Coupon(
					myRs.getLong("ID"),
					myRs.getString("TITLE"), 
					myRs.getDate("START_DATE"),
					myRs.getDate("END_DATE"), 
					myRs.getInt("AMOUNT"), 
					CouponType.valueOf(myRs.getString("TYEP")),
					myRs.getString("MESSAGE"), 
					myRs.getDouble("PRICE"), 
					myRs.getString("IMAGE"));
			// Adding to List
			coupons.add(coupon);
		}

		// Return List of coupon
		return coupons;
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	@Override
	public boolean login(String custNmae, String password) throws CouponSystemException {
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {

		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from customer "
				+ "where CUST_NAME = ? and PASSWORD = ?");

		// Values
		selectStmt.setString(1, custNmae);
		selectStmt.setString(2, password);
		
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();
		
		// Return true if the is a match
		return myRs.next(); 
		
	} catch (PropertyVetoException | SQLException | IOException e) {
		throw new CouponSystemException("CouponSystemException", e);
	}
}
}
