package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.*;
import com.couponproject.beans.*;
import com.couponproject.constants.*;
import com.couponproject.exception.*;
import com.couponproject.dao.CustomerDAO;
import com.couponproject.util.Util;

/**
 * 
 * This Class implements the CustomerDAO interface, using mySQL server & c3po connection pool.
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class CustomerDBDAO implements CustomerDAO {
	// *********
	// Attribute
	// *********
	private static CustomerDBDAO instace = null;
	
	// ***************
	// *****Methods***
	// ***************
	public static CustomerDBDAO getInstace(){
		if (instace == null){
			return new CustomerDBDAO();
		}
		return instace;
	}
	
	// Takes Customer as argument and adds it to the customer table in the DB
	@Override
	public void createCustomer(Customer customer)
			throws CouponSystemException, IllegalPasswordException, CustomerAlreadyExistsException {
		if (!Util.passwordvalidation(customer.getPassword())) {
			throw new IllegalPasswordException(Constants.PasswordErrorMassage);
		} else if (Util.isCustomerNameExist(customer)) {
			throw new CustomerAlreadyExistsException(Constants.UserNameErrorMassage);
		} else {
			// getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Insert prepared statement
				PreparedStatement createStmt = myCon.prepareStatement(
						"insert into customer (" 
						+ CustomerTableColumnNames.CUST_NAME + ", "
						+ CustomerTableColumnNames.PASSWORD + ") " 
						+ "values (?,?);"); // id will be assign in the DB
				// Values
				createStmt.setString(1, customer.getCustName());
				createStmt.setString(2, customer.getPassword());
				// Execute
				createStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	// A method that gets an instance of an existing customer and removes it from the DB customer table 
	@Override
	public void removeCustomer(Customer customer) throws CouponSystemException, CustomerDoesNotExistException {
		if (!Util.isCustomer(customer)) {
			throw new CustomerDoesNotExistException("Customer does not exist");
		} else {
			// getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Delete prepared statement
				PreparedStatement deleteStmt = myCon.prepareStatement(
						"delete from customer where "
						+ CustomerTableColumnNames.ID + " = ? and "
						+ CustomerTableColumnNames.CUST_NAME + "= ? and "
						+ CustomerTableColumnNames.PASSWORD + "= ?");
				// Values
				deleteStmt.setLong(1, customer.getId());
				deleteStmt.setString(2, customer.getCustName());
				deleteStmt.setString(3, customer.getPassword());
				// Execute
				deleteStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	// A method that gets a customer that exists in the customer table updates its details 
	@Override
	public void updateCustomer(Customer customer)
			throws CouponSystemException, IllegalPasswordException, CustomerAlreadyExistsException {
		if (!Util.passwordvalidation(customer.getPassword())) {
			throw new IllegalPasswordException(Constants.PasswordErrorMassage);
		} else if (Util.isCustomerNameExist(customer)) {
			throw new CustomerAlreadyExistsException(Constants.UserNameErrorMassage);
		} else {
			// getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Update prepared statement
				PreparedStatement updateStmt = myCon.prepareStatement(
						"update customer set "
						+ CustomerTableColumnNames.CUST_NAME + "= ?, "
						+ CustomerTableColumnNames.PASSWORD + "= ? where "
						+ CustomerTableColumnNames.ID + "= ?");
				// Values
				updateStmt.setString(1, customer.getCustName());
				updateStmt.setString(2, customer.getPassword());
				updateStmt.setLong(3, customer.getId());
				// Execute
				updateStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	// A method that gets a customer's ID, looks for the line in customer table in the db with that ID
	// creates a customer instance with the details taken from the db and returns it 
	@Override
	public Customer getCustomer(long id) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from customer where "
					+ CustomerTableColumnNames.ID + "= ?");
			// Values
			selectStmt.setLong(1, id);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Customer(bean) instance
			myRs.next();
			// Return customer
			return Util.resultSetToCustomer(myRs);
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
	
	// A method that gets a customer's name and password, looks for the line in customer table in the db with that name and password
	// creates a customer instance with the details taken from the db and returns it 
	@Override
	public Customer getCustomer(String name, String password) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from customer where "
					+ CustomerTableColumnNames.CUST_NAME + "= ? and " 
					+ CustomerTableColumnNames.PASSWORD + "= ?");
			// Values
			selectStmt.setString(1, name);
			selectStmt.setString(2, password);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Customer(bean) instance
			myRs.next();
			// Return customer
			return Util.resultSetToCustomer(myRs); 
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	// A method that returns a collection of all the customers in the customer table on the db
	@Override
	public Collection<Customer> getAllCustomer() throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement("select * from customer");
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Collection of Customer
			Collection<Customer> customers = new ArrayList<>();
			// Iterating the resultSet -
			// each iteration is converted into a Customer instance and added to
			// the List
			while (myRs.next()) {
				customers.add(Util.resultSetToCustomer(myRs));
			}
			// Return customer
			return customers;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	// A method that returns a collection of all the coupons that belongs to a customer	
	@Override
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"SELECT * FROM coupon " 
					+ "JOIN customer_coupon "
					+ "ON coupon." + CouponTableColumnNames.ID 
					+ "= customer_coupon." + JoinTablesColumnNames.COUPON_ID  
					+ "WHERE customer_coupon."+ JoinTablesColumnNames.CUST_ID + "= ?");
			// Value
			selectStmt.setLong(1, id);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Collection of Coupon
			Collection<Coupon> coupons = new ArrayList<>();
			// Iterating the resultSet -
			// each iteration is converted into a Coupon instance and added to the List
			while (myRs.next()) {
				// Generating Coupon and adding it to the List
				coupons.add(Util.resultSetToCoupn(myRs));
			}
			// Return List of coupon
			return coupons;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	// This method should take customer name and password as argument 
	// and return a boolean indicating a successful login or not
	@Override
	public boolean login(String custNmae, String password) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from customer where " 
					+ CustomerTableColumnNames.CUST_NAME + " = ? and "
					+ CustomerTableColumnNames.PASSWORD +" = ?");
			// Values
			selectStmt.setString(1, custNmae);
			selectStmt.setString(2, password);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Return true if the is a match
			return myRs.next();
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	/**
	 * Updates a joined Customer-Coupon table based on a gives Customer's and Coupon's IDs
	 * @param custId Customer's ID
	 * @param coupId Coupon's ID
	 * @throws CouponSystemException
	 */
	public void addCouponToCustomer(long custId, long coupId) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Insert prepared statement 
			PreparedStatement insertStmt = myCon.prepareStatement(
					"insert into customer_coupon ("
					+ JoinTablesColumnNames.CUST_ID + "," 
					+ JoinTablesColumnNames.COUPON_ID + ") values (?,?);");
			// Values
			insertStmt.setLong(1, custId);
			insertStmt.setLong(2, coupId);
			// Execute
			insertStmt.executeUpdate();
			// Select prepared statement to get coupon's amount before purchase
			PreparedStatement selectStmt = myCon.prepareStatement("select * from coupon " + "where ID = ?");
			// Value
			selectStmt.setLong(1, coupId);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			
			// 
			// Subtract 1 coupon from coupon amount 
			int coupAmount = myRs.getInt(CouponTableColumnNames.AMOUNT.name());
			// remove 1 from the amount of the coupon
			PreparedStatement updateStmt = myCon.prepareStatement(
					"update coupon set "
					+ CouponTableColumnNames.AMOUNT + "= ? where " 
					+ CouponTableColumnNames.ID + "= ?");
			// Values
			updateStmt.setDouble(2, coupAmount--);
			updateStmt.setLong(3, coupId);
			// Execute
			updateStmt.executeUpdate();
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
	
	@Override
	public void removeCustomerCoupon(long custId, long couponId) {
		// TODO Auto-generated method stub		
	}

	// ****************
	// UniqueCouponType
	// *****************
	/**
	 * Returns a collection of of all the CouponTaypes of the Coupons purchased by a specific Customer
	 * @param customer The Customer for which the collection of CouponType is returned
	 * @return Collection of CouponType 
	 * @throws CouponSystemException
	 */
	public Collection<CouponType> getUniqueCouponTypes(Customer customer) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"SELECT DISTINCT " + CouponTableColumnNames.TYPE 
					+ " FROM coupon JOIN customer_coupon ON coupon." +  CouponTableColumnNames.ID
					+ " = customer_coupon." + JoinTablesColumnNames.COUPON_ID 
					+ " WHERE customer_coupon." + JoinTablesColumnNames.CUST_ID + " = ?");
			// Value
			selectStmt.setLong(1, customer.getId());
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Collection of CouponType
			Collection<CouponType> couponsTypes = new ArrayList<>();
			// Iterating the resultSet
			while (myRs.next()) {
				// Generating Coupons type and adding it to the List
				CouponType couponType = CouponType.valueOf(myRs.getString(CouponTableColumnNames.TYPE.name()));
				couponsTypes.add(couponType);
			}
			// Return List of coupon
			return couponsTypes;

		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
}
