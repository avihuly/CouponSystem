package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.couponproject.beans.Coupon;
import com.couponproject.beans.CouponType;
import com.couponproject.dao.CouponDAO;
import com.couponproject.exception.CouponSystemException;

public class CouponDBDAO implements CouponDAO{

	// a method that gets an insnance of a coupon and adds it to the coupon table in the DB
	// TODO: when creating a coupon it should be joind also to a company.
	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemException {
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
						
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
					
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}


	// a method that gets an instance of a coupon and removes it form the db
	//TODO: remove the coupon from the joined tables!!
	@Override
	public void removeCoupon(Coupon coupon) throws CouponSystemException {
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
					
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

		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	//a method that gets an instance of a coupon and changes the related line in the DB
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			
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
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	// a method that gets ID of a coupon, looks for the coupon in the coupon table in the db, creates a coupon instance 
	// from the data in the table and returns it
	@Override
	public Coupon getCoupon(long id) throws CouponSystemException {
		
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				
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
					
			
			// Return coupon
			return coupon;
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	// a method that returns a List of all the coupons from the coupon table in the DB
	@Override
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {
		
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
						
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from coupon");
									
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
							
			// Processing resultSet into a List of Coupon
			//---------------------------------------------------
			//Declaring a List of 'Coupon's
			Collection <Coupon> coupons = new ArrayList<>(); 
							
			// Iterating the resultSet - 
			// each iteration is converted into a Coupon instance and added to the List
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
					coupons.add(coupon);
				}
									
			// Return List<Coupon>
			return coupons;
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	// a method that gets a couponType and returns a List of all the coupons with that type
	@Override
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemException {
		
		// Creating a Connection object to the DB
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
						
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from coupon where TYPE = ?");
			
			// Values
			selectStmt.setString(1, couponType.name());
			
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();

			// Processing resultSet into a List of Coupon
			//---------------------------------------------------
			//Declaring a List of 'Coupon's
			Collection <Coupon> coupons = new ArrayList<>(); 
									
			// Iterating the resultSet - 
			// each iteration is converted into a Coupon instance and added to the List
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
					coupons.add(coupon);
				}
				
			// Return List<Coupon>
			return coupons;
	
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}
}
