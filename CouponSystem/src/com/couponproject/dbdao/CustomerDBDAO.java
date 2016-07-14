package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponTableColumnNames;
import com.couponproject.constants.CouponType;
import com.couponproject.constants.CustomerTableColumnNames;
import com.couponproject.dao.CustomerDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.util.Util;

// implements CustomerDAO with mysql
public class CustomerDBDAO implements CustomerDAO {
	// *********
	// Attribute
	// *********
	private static CustomerDBDAO instace = null;
	
	// ***********
	// constructor
	// ***********
	private CustomerDBDAO(){
	}

	// ***************
	// *****Methods***
	// ***************
	
	// Get instance
	public static CustomerDBDAO getInstace(){
		if (instace == null){
			return new CustomerDBDAO();
		}
		return instace;
	}
	
	
	@Override
	// createCustomer
	public void createCustomer(Customer customer) throws CouponSystemException, IllegalPasswordException, CustomerAlreadyExistsException {
		if(!Util.passwordvalidation(customer.getPassword())){
			throw new IllegalPasswordException(
					"Password must contain:\n"
					+ "6-10 characters\n"
					+ "At lest one upper case letter\n"
					+ "At lest one lower case letter\n"
					+ "At lest one digit");	
		}
		else if (Util.isCustomer(customer)){
			throw new CustomerAlreadyExistsException(
					"User name already exists in DB");	
		} else {
			// getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				
				// Insert prepared statement
				PreparedStatement createStmt = myCon.prepareStatement(					
						"insert into "
						+ "customer ("+ CustomerTableColumnNames.CUST_NAME +", "+ CustomerTableColumnNames.PASSWORD +") "
						+ "values (?,?);"); //id will be assign in the DB
				System.out.println(createStmt);
				
				// Values
				createStmt.setString(1, customer.getCustName());
				createStmt.setString(2, customer.getPassword());

				// Execute
				createStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			} 
	}}

	@Override
	public void removeCustomer(Customer customer) throws CouponSystemException, CustomerDoesNotExistException {
		if (!Util.isCustomer(customer)){
			throw new CustomerDoesNotExistException(
					"Customer does not exist exception");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
		
				// Delete prepared statement
				PreparedStatement deleteStmt = myCon.prepareStatement(
						"delete from customer "
						+ "where "+CustomerTableColumnNames.ID+" = ? and CUST_NAME = ? and PASSWORD = ?");

				// Values
				deleteStmt.setLong(1, customer.getId());
				deleteStmt.setString(2, customer.getCustName());
				deleteStmt.setString(3, customer.getPassword());
			
				// Execute
				deleteStmt.executeUpdate();
			
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}
			
	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException, IllegalPasswordException, CustomerAlreadyExistsException {
		if(!Util.passwordvalidation(customer.getPassword())){
			throw new IllegalPasswordException(
					"Password must contain:\n"
					+ "6-10 characters\n"
					+ "At lest one upper case letter\n"
					+ "At lest one lower case letter\n"
					+ "At lest one digit");	
		}
		else if (Util.isCustomer(customer)){
			throw new CustomerAlreadyExistsException(
					"User name already exists in DB");	
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				// Update prepared statement
				PreparedStatement updateStmt = myCon.prepareStatement(
						"update customer "
						+ "set CUST_NAME = ? and PASSWORD = ? "
						+ "where ID = ?");
	
				// Values
				updateStmt.setString(1, customer.getCustName());
				updateStmt.setString(2, customer.getPassword());
				updateStmt.setLong(3, customer.getId());
				
				// Execute
				updateStmt.executeUpdate();
				
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}
	}

	@Override
	public Customer getCustomer(long id) throws CouponSystemException, CustomerAlreadyExistsException {
		if (Util.isCustomer(new Customer(id,null,null))){
			throw new CustomerAlreadyExistsException(
					"User name already exists in DB");	
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Select prepared statement
				PreparedStatement selectStmt = myCon.prepareStatement(
						"select * from customer "
						+ "where ID = ?");
				
				// Values
				selectStmt.setLong(1, id);
				
				// Execute and get a resultSet
				ResultSet myRs = selectStmt.executeQuery();
							
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
	}
	
	public Customer getCustomer(String name, String password) throws CouponSystemException, CustomerAlreadyExistsException {
		if (Util.isCustomer(new Customer(name,password))){
			throw new CustomerAlreadyExistsException(
					"User name already exists in DB");	
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				
				// Select prepared statement
				PreparedStatement selectStmt = myCon.prepareStatement(
						"select * from customer "
						+ "where CUST_NAME = ? and PASSWORD = ?");
	
				// Values
				selectStmt.setString(1, name);
				selectStmt.setString(2, password);
	
				// Execute and get a resultSet
				ResultSet myRs = selectStmt.executeQuery();
	
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
	}

	@Override
	public Collection<Customer> getAllCustomer() throws CouponSystemException {
		// getting a connection to DB from  pool 
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
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
		
		// Select prepared statement
		PreparedStatement selectStmt = myCon.prepareStatement(
				"SELECT * FROM coupon "
				+ "JOIN customer_coupon "
				+ "ON coupon.ID = customer_coupon.COUPON_ID "
				+ "WHERE customer_coupon.CUST_ID = ?");
		
		// Value 
		selectStmt.setLong(1, id);
		
		// Execute and get a resultSet		
		ResultSet myRs = selectStmt.executeQuery();

		// Processing resultSet into a Collection of Coupon
		// ------------------------------------------------
		// Declaring a List of 'Coupon's
		Collection<Coupon> coupons = new ArrayList<>();

		// Iterating the resultSet -
		// each iteration is converted into a Coupon instance and added to the
		// List
		while (myRs.next()) {
			// Generating Coupon and adding it to the List
			coupons.add(resultSetToCoupn(myRs));
		}

		// Return List of coupon
		return coupons;
		
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	@Override
	public boolean login(String custNmae, String password) throws CouponSystemException {
		// getting a connection to DB from  pool
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

	public void addCouponToCustomer(long custId, long coupId) throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {

			// Insert prepared statement
			PreparedStatement insertStmt = myCon.prepareStatement(					
					"insert into "
					+ "customer_coupon (CUST_ID, COUPON_ID) "
					+ "values (?,?);"); //id will be assign in the DB

			// Values
			insertStmt.setLong(1, custId);
			insertStmt.setLong(2, coupId);

			// Execute
			insertStmt.executeUpdate();

		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	// ****************
	// UniqueCouponType
	// *****************
	public Collection<CouponType> getUniqueCouponType(Customer cstomer) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {

			// Select prepared statement
			PreparedStatement selectStmt = myCon
					.prepareStatement("SELECT DISTINCT " + CouponTableColumnNames.TYPE + "FROM coupon");

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
			throw new CouponSystemException("CouponSystemException", e);
		}
	}


			
			
	// ***************
	// private methods
	// ***************
	private Coupon resultSetToCoupn(ResultSet myRs) throws SQLException{
		return new Coupon(
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
	}
}
