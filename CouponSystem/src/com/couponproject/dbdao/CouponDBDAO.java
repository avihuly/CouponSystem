package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import com.couponproject.beans.Coupon;
import com.couponproject.constants.*;
import com.couponproject.exception.*;
import com.couponproject.dao.CouponDAO;
import com.couponproject.util.Util;

/**
 * 
 * This Class implements the CouponDAO interface, using mySQL server & c3po connection pool. 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class CouponDBDAO implements CouponDAO{
	// *********
	// Attribute
	// *********
	private static CouponDBDAO instace = null;

	// ***************
	// *****Methods***
	// ***************
	/**
	 * Returns the single instance of CouponDBDAO
	 * @return single instance of CouponDBDAO
	 */
	public static CouponDBDAO getInstace() {
		if (instace == null) {
			return new CouponDBDAO();
		}
		return instace;
	}
	
	// Takes Coupon as argument and adds it to the coupon table in the DB
	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemException, CouponTitleAlreadyExistException {
		if (Util.isCoupon(coupon)) {
			throw new CouponTitleAlreadyExistException(Constants.CouponTitleAlreadyMassage);
		} else {
			// Getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Insert prepared statement
				PreparedStatement createStmt = myCon.prepareStatement(
					"insert into coupon ("
					+ CouponTableColumnNames.TITLE + "," 
					+ CouponTableColumnNames.START_DATE + "," 
					+ CouponTableColumnNames.END_DATE + "," 
					+ CouponTableColumnNames.AMOUNT + "," 
					+ CouponTableColumnNames.TYPE + "," 
					+ CouponTableColumnNames.MESSAGE + "," 
					+ CouponTableColumnNames.PRICE + "," 
					+ CouponTableColumnNames.IMAGE 
					+ ") values (?,?,?,?,?,?,?,?);"); // id will be assign in the DB
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
				
				// Get and Set coupon Id that was generated in the DB
				PreparedStatement getIdSts = myCon.prepareStatement(
						"SELECT "+ CouponTableColumnNames.ID 
						+ " FROM coupon WHERE "
						+ CouponTableColumnNames.TITLE + " = ?");
				// Value & Execute
				getIdSts.setString(1, coupon.getTitle());
				ResultSet myRs = getIdSts.executeQuery();
				// set coupon ID from DB
				myRs.next();
				// set coupon id
				coupon.setId(myRs.getLong("ID"));
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	// Takes Coupon as argument and removes it from the coupon table in the DB
	@Override
	public void removeCoupon(Coupon coupon) throws CouponSystemException, CouponDoesNotExistException {
		if (!Util.isCoupon(coupon)){
			throw new CouponDoesNotExistException("Coupon does not exist");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				// Delete prepared statement
				PreparedStatement deleteStmt = myCon.prepareStatement(
						"delete from coupon where "
						+ CouponTableColumnNames.ID + "= ? and "
						+ CouponTableColumnNames.TITLE + "= ?");
				// Values
				deleteStmt.setLong(1, coupon.getId());
				deleteStmt.setString(2, coupon.getTitle());
				// Execute
				deleteStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * Removes from the database the link between a given Coupon, by his Coupon ID, to a Customer who purchased this Coupon
	 * @param couponID The ID of the Coupon to be deleted
	 * @throws CouponSystemException
	 */
	public void removeCouponCustomerByCouponID(long couponID) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Update prepared statement
			PreparedStatement deleteStmt = myCon.prepareStatement(
					"DELETE FROM customer_coupon where " 
					+ JoinTablesColumnNames.COUPON_ID + "= ?");
			// Values
			deleteStmt.setLong(1, couponID);
			// Execute
			deleteStmt.executeUpdate();
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
	
	/**
	 * Removes from the database the link between a given Coupon, by his Coupon ID, to the Company who owns this Coupon
	 * @param couponID The ID of the Coupon to be deleted
	 * @throws CouponSystemException
	 */
	public void removeCouponCompanyByCouponID(long couponID) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Update prepared statement
			PreparedStatement deleteStmt = myCon.prepareStatement(
					"DELETE FROM company_coupon where "
					+ JoinTablesColumnNames.COUPON_ID + "= ?");
			// Values
			deleteStmt.setLong(1, couponID);
			// Execute
			deleteStmt.executeUpdate();
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
		
	// A method that gets an instance of a coupon and changes the related line in the DB
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException, CouponTitleAlreadyExistException {
		if (Util.isCouponNameExist(coupon)) {
			throw new CouponTitleAlreadyExistException(Constants.CouponTitleAlreadyMassage);
		} else {
			// getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Update prepared statement
				PreparedStatement updateStmt = myCon.prepareStatement(
						"update coupon set "
						+ CouponTableColumnNames.END_DATE + "= ? , "
						+ CouponTableColumnNames.PRICE + "= ? where "
						+ CouponTableColumnNames.ID + "= ?");
				// Values
				// converting localDate to sql.date
				updateStmt.setDate(1, Date.valueOf(coupon.getEndDate()));
				updateStmt.setDouble(2, coupon.getPrice());
				updateStmt.setLong(3, coupon.getId());
				// Execute
				updateStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	// A method that gets ID of a coupon, looks for the coupon in the coupon table in the db, creates a coupon instance 
	// from the data in the table and returns it
	@Override
	public Coupon getCoupon(long id) throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from coupon where "
					+ CouponTableColumnNames.ID + "= ?");
			// Values
			selectStmt.setLong(1, id);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			myRs.next();
			// Return coupon
			return Util.resultSetToCoupn(myRs);
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
	
	
	// A method that returns a List of all the coupons from the DB coupon table
	@Override
	public Collection<Coupon> getAllCoupons() throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement("select * from coupon");
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();	
			// Processing resultSet into a Collection of Coupon
			Collection <Coupon> coupons = new ArrayList<>(); 
			// Iterating the resultSet - 
			// each iteration is converted into a Coupon instance and added to the List
				while(myRs.next()){
					coupons.add(Util.resultSetToCoupn(myRs));
				}
			// Return Collection<Coupon>
			return coupons;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	// A method that gets a couponType and returns a List of all the coupons with that type
	@Override
	public Collection<Coupon> getCouponsByType(CouponType couponType) throws CouponSystemException {
		// Getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){						
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from coupon where "
					+ CouponTableColumnNames.TYPE + "= ?");
			// Values
			selectStmt.setString(1, couponType.name());
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Collection of Coupon
			Collection <Coupon> coupons = new ArrayList<>(); 			
			// Iterating the resultSet - 
			// each iteration is converted into a Coupon instance and added to the List
				while(myRs.next()){
					coupons.add(Util.resultSetToCoupn(myRs));
				}
			// Return List<Coupon>
			return coupons;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
}
