package com.couponproject.dbdao;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.Coupon;
import com.couponproject.beans.CouponType;
import com.couponproject.beans.Customer;
import com.couponproject.dao.CustomerDAO;

// implements CustomerDAO with mysql
public class CustomerDBDAO implements CustomerDAO {
	
	@Override
	public void createCustomer(Customer custumer) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
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
		
		// Close connection
		myCon.close();
	}

	@Override
	public void removeCustomer(Customer custumer) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
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
		
		// Close connection
		myCon.close();
	}

	@Override
	public void updateCustomer(Customer custumer) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
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
		
		// Close connection
		myCon.close();

	}

	@Override
	public Customer getCustomer(long id) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from customer "
				+ "where ID = ?");
		
		// Values
		selectStmt.setLong(1, id);
		
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();
		
		// Processing resultSet into a Customer(bean) instance
		Customer customer = new Customer(
				myRs.getLong("ID"),
				myRs.getString("CUST_NAME"),
				myRs.getString("PASSWORD"));
		
		// Close connection
		myCon.close();
		
		// Return customer
		return customer;
	}

	@Override
	public Collection<Customer> getAllCustomer() throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from customer");
						
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();
				
		// Processing resultSet into a Collection of Customer
		//---------------------------------------------------
		//Declaring a set of 'Customer's
		Collection <Customer> custSet = new HashSet<>(); 
				
		// Iterating the resultSet - 
		// each iteration is converted into a Customer instance and added to the set
			while(myRs.next()){
				Customer customer = new Customer(
					myRs.getLong("ID"),
					myRs.getString("CUST_NAME"),
					myRs.getString("PASSWORD"));
				custSet.add(customer);
			}
		// Close connection
		myCon.close();
			
		// Return customer
		return custSet;
	}

	@Override
	public Collection<Coupon> getCoupons(long id) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
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
		// Declaring a set of 'Coupon's
		Collection<Coupon> couptSet = new HashSet<>();

		// Iterating the resultSet -
		// each iteration is converted into a Coupon instance and added to the
		// set
		while (myRs.next()) {
			// Generating Coupon
			Coupon coupon = new Coupon(
					myRs.getLong("ID"),
					myRs.getString("TITLE"), 
					myRs.getDate("START_DATE"),
					myRs.getDate("END_DATE"), 
					myRs.getInt("AMOUNT"), 
					CouponType.fromString(myRs.getString("TYEP")),
					myRs.getString("MESSAGE"), 
					myRs.getDouble("PRICE"), 
					myRs.getString("IMAGE"));
			// Adding to set
			couptSet.add(coupon);
		}

		// Close connection
		myCon.close();

		// Return customer
		return couptSet;
	}

	@Override
	public boolean login(String custNmae, String password) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();

		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from customer "
				+ "where CUST_NAME = ? and PASSWORD = ?");

		// Values
		selectStmt.setString(1, custNmae);
		selectStmt.setString(2, password);
		
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();
		
		boolean login = myRs.next();

		// Close connection
		myCon.close();
		 
		// Return true if the is a match
		return login;
	}
}
