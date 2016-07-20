package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponTableColumnNames;
import com.couponproject.constants.CouponType;
import com.couponproject.dao.CouponDAO;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.util.Util;


public class CouponDBDAO implements CouponDAO{
	// *********
	// Attribute
	// *********
	private static CouponDBDAO instace = null;

	// ***********
	// constructor
	// ***********
	private CouponDBDAO(){
		}

	// ***************
	// *****Methods***
	// ***************

	// Get instace
	public static CouponDBDAO getInstace() {
		if (instace == null) {
			return new CouponDBDAO();
		}
		return instace;
	}
	
	
	// Takes Coupon as argument and  
	// adds it to the coupon table in the DB
	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemException, CouponTitleAlreadyExistException {
		if (Util.isCoupon(coupon)){
			throw new CouponTitleAlreadyExistException(
					"Coupon title already exist in DB\n"
					+ "choose another title");
		} else {
			// Getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){			
				// Insert prepared statement 
				PreparedStatement createStmt = myCon.prepareStatement(
						"insert into "
						+ "coupon (TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) "
						+ "values (?,?,?,?,?,?,?,?);"); //id will be assign in the DB
						
						// Values 
						createStmt.setString(1, coupon.getTitle());
											  // converting localDate to sql.date
						createStmt.setDate(2, Date.valueOf(coupon.getStartDate()));
											  // converting localDate to sql.date
						createStmt.setDate(3, Date.valueOf(coupon.getEndDate()));
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
	}


	// Takes Coupon as argument and  
	// removes it from the coupon table in the DB
	@Override
	public void removeCoupon(Coupon coupon) throws CouponSystemException, CouponDoesNotExistException {
		if (!Util.isCoupon(coupon)){
			throw new CouponDoesNotExistException(
					"Coupon does not exist exception");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
						
				// Delete prepared statement
				PreparedStatement deleteStmt = myCon.prepareStatement(
						"delete from coupon "
						+ "where ID = ? and TITLE = ?");
	
				// Values
				deleteStmt.setLong(1, coupon.getId());
				deleteStmt.setString(2, coupon.getTitle());
				
				// Execute
				deleteStmt.executeUpdate();
	
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}
	}
	
	public void removeCouponCustomerByCouponID(long couponID) throws CouponSystemException{
		// getting a connection to DB from  pool
				try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
					
					// Update prepared statement
					PreparedStatement deleteStmt = myCon.prepareStatement( 	"delete * from customer_coupon "
							+ "where COUPON_ID = ? ");

					// Values
					deleteStmt.setLong(1, couponID);
					
											
					// Execute
					deleteStmt.executeUpdate();

					
				} catch (PropertyVetoException | SQLException | IOException e) {
					throw new CouponSystemException("CouponSystemException", e);
				}
	}

	//a method that gets an instance of a coupon and changes the related line in the DB
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException, CouponTitleAlreadyExistException {
		if (Util.isCoupon(coupon)){
			throw new CouponTitleAlreadyExistException(
					"Coupon title already exist in DB\n"
					+ "choose another title");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				
				// Update prepared statement
				PreparedStatement updateStmt = myCon.prepareStatement(
						"update coupon "
						+ "set END_DATE = ? and PRICE = ? "
						+ "where TITLE = ?");
	
				// Values
				updateStmt.setString(1, coupon.getTitle());
									  // converting localDate to sql.date
				updateStmt.setDate(2, Date.valueOf(coupon.getStartDate()));
				  					  // converting localDate to sql.date
				updateStmt.setDate(3, Date.valueOf(coupon.getEndDate()));
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
	}

	// a method that gets ID of a coupon, looks for the coupon in the coupon table in the db, creates a coupon instance 
	// from the data in the table and returns it
	@Override
	public Coupon getCoupon(long id) throws CouponSystemException {
		
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from coupon "
							+ "where ID = ?");
					
			// Values
			selectStmt.setLong(1, id);
					
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			
			myRs.next();
			
			// Processing resultSet into a Coupon(bean) instance
			Coupon coupon = new Coupon(
					myRs.getLong("ID"),
					myRs.getString("TITLE"),
					// converting sql.Date to LocalDate
					myRs.getDate("START_DATE").toLocalDate(),
					// converting sql.Date to LocalDate
					myRs.getDate("END_DATE").toLocalDate(),
					myRs.getInt("AMOUNT"),
					CouponType.valueOf(myRs.getString("TYPE")),
					myRs.getString(CouponTableColumnNames.MESSAGE.name()),
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
		
		// getting a connection to DB from  pool
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
							// converting Date to LocalDate
							myRs.getDate("START_DATE").toLocalDate(),
							// converting Date to LocalDate
							myRs.getDate("END_DATE").toLocalDate(),
							myRs.getInt("AMOUNT"),
							CouponType.valueOf(myRs.getString("TYPE")),
							myRs.getString("MESSAGE"),
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
		
		// Getting a connection to DB from  pool
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
							// converting Date to LocalDate
							myRs.getDate("START_DATE").toLocalDate(),
							// converting Date to LocalDate
							myRs.getDate("END_DATE").toLocalDate(),
							myRs.getInt("AMOUNT"),
							CouponType.valueOf(myRs.getString("TYPE")),
							myRs.getString(CouponTableColumnNames.MESSAGE.name()),
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
