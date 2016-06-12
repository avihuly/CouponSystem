package com.couponproject.dbdao;

import java.sql.*;
import java.util.Collection;

import com.couponproject.beans.Coupon;
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
				+ "customer (CAST_NAME, PASSWORD) "
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
				"delet from customer "
				+ "where ID = ? and CAST_NAME = ? and PASSWORD = ?");

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
		
		// Return customer
		return customer;
	}

	@Override
	public Collection<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String custNmae, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
