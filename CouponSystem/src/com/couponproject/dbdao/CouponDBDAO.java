package com.couponproject.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.Coupon;
import com.couponproject.beans.CouponType;
import com.couponproject.beans.Customer;
import com.couponproject.dao.CouponDAO;

public class CouponDBDAO implements CouponDAO{

	// a method that gets an insnance of a coupon and adds it to the coupon table in the DB
	// TODO: when creating a coupon it should be joind also to a company.
	@Override
	public void createCoupon(Coupon coupon) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
				
		// Insert prepared statement 
		PreparedStatement createStmt = myCon.prepareStatement(
				"insert into "
				+ "coupon (TITLE, START_DATE, ENT_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) "
				+ "values (?,?,?,?,?,?,?,?);"); //id will be assign in the DB
				
				// Values 
				createStmt.setString(1, coupon.getTitle());
				createStmt.setDate(2, coupon.getStartDate());
				createStmt.setDate(2, coupon.getEndDate());
				createStmt.setInt(4, coupon.getAmount());
				createStmt.setString(5, coupon.getType().name());
				createStmt.setString(6, coupon.getMessage());
				createStmt.setDouble(7, coupon.getPrice());
				createStmt.setString(8, coupon.getImage());
				
				
				// Execute
				createStmt.executeUpdate();
				
				// Close connection
				myCon.close();
			}


	// a method that gets an instance of a coupon and removes it form the db
	//TODO: remove the coupon from the joined tables!!
	@Override
	public void removeCoupon(Coupon coupon) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		// Delete prepared statement
		PreparedStatement deleteStmt = myCon.prepareStatement(
				"delete from coupon "
				+ "where ID = ? and TITLE = ? and START_DATE = ? and END_DATE = ?");

		// Values
		deleteStmt.setLong(1, coupon.getId());
		deleteStmt.setString(2, coupon.getTitle());
		deleteStmt.setDate(3, coupon.getStartDate());
		deleteStmt.setDate(4, coupon.getEndDate());
		
		// Execute
		deleteStmt.executeUpdate();
		
		// Close connection
		myCon.close();
		
	}

	//a method that gets an instance of a coupon and changes the related line in the DB
	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		// Update prepared statement
		PreparedStatement updateStmt = myCon.prepareStatement(
				"update coupon "
				+ "set TITILE = ? and START_DATE = ? and END_DATE = ? and AMOUNT = ? and TYPE = ? and MESSAGE = ? "
				+ "and PRICE = ? and IMAGE = ?"
				+ "where ID = ?");

		// Values
		updateStmt.setString(1, coupon.getTitle());
		updateStmt.setDate(2, coupon.getStartDate());
		updateStmt.setDate(3, coupon.getEndDate());
		updateStmt.setInt(4, coupon.getAmount());
		updateStmt.setString(5, coupon.getType().name());
		updateStmt.setString(6, coupon.getMessage());
		updateStmt.setDouble(7, coupon.getPrice());
		updateStmt.setString(8, coupon.getImage());
		updateStmt.setLong(9, coupon.getId());
		
		
		// Execute
		updateStmt.executeUpdate();
		
		// Close connection
		myCon.close();
	}

	// a method that gets ID of a coupon, looks for the coupon in the coupon table in the db, creates a coupon onstance 
	// from the data in the table and returns it
	@Override
	public Coupon getCoupon(long id) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
				
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from coupon "
						+ "where ID = ?");
				
		// Values
		selectStmt.setLong(1, id);
				
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();
				
		// Processing resultSet into a Coupon(bean) instance
		Coupon coupon = new Coupon(
				myRs.getLong("ID"),
				myRs.getString("TITLE"),
				myRs.getDate("START_DATE"),
				myRs.getDate("END_DATE"),
				myRs.getInt("AMOUNT"),
				CouponType.valueOf(myRs.getString("TYPE")),
				myRs.getString("MESSEGE"),
				myRs.getDouble("PRICE"),
				myRs.getString("IMAGE"));
				
				
		// Close connection
		myCon.close();
				
		// Return coupon
		return coupon;

	}

	// a method that returns a Collection of all the coupons from the coupon table in the DB
	@Override
	public Collection<Coupon> getAllCoupons() throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
				
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from coupon");
								
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();
						
		// Processing resultSet into a Collection of Coupon
		//---------------------------------------------------
		//Declaring a set of 'Coupon's
		Collection <Coupon> coupSet = new HashSet<>(); 
						
		// Iterating the resultSet - 
		// each iteration is converted into a Coupon instance and added to the set
			while(myRs.next()){
				Coupon coupon = new Coupon(
						myRs.getLong("ID"),
						myRs.getString("TITLE"),
						myRs.getDate("START_DATE"),
						myRs.getDate("END_DATE"),
						myRs.getInt("AMOUNT"),
						CouponType.valueOf(myRs.getString("TYPE")),
						myRs.getString("MESSEGE"),
						myRs.getDouble("PRICE"),
						myRs.getString("IMAGE"));
				coupSet.add(coupon);
			}
		// Close connection
		myCon.close();
					
		// Return Set<Coupon>
		return coupSet;

	}

	// a method that gets a couponType and returns a Collection of all the coupons with that type
	@Override
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
						
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"select * from coupon where TYPE = ?");
		
		// Values
		selectStmt.setString(1, couponType.name());
		
		// Execute and get a resultSet
		ResultSet myRs = selectStmt.executeQuery();

		// Processing resultSet into a Collection of Coupon
		//---------------------------------------------------
		//Declaring a set of 'Coupon's
		Collection <Coupon> coupSet = new HashSet<>(); 
								
		// Iterating the resultSet - 
		// each iteration is converted into a Coupon instance and added to the set
			while(myRs.next()){
				Coupon coupon = new Coupon(
						myRs.getLong("ID"),
						myRs.getString("TITLE"),
						myRs.getDate("START_DATE"),
						myRs.getDate("END_DATE"),
						myRs.getInt("AMOUNT"),
						CouponType.valueOf(myRs.getString("TYPE")),
						myRs.getString("MESSEGE"),
						myRs.getDouble("PRICE"),
						myRs.getString("IMAGE"));
				coupSet.add(coupon);
			}
		// Close connection
		myCon.close();
							
		// Return Set<Coupon>
		return coupSet;

	}

}
