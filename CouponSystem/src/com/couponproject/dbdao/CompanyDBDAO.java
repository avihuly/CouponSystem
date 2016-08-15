package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import com.couponproject.beans.*;
import com.couponproject.constants.*;
import com.couponproject.exception.*;
import com.couponproject.util.Util;
import com.couponproject.dao.CompanyDAO;

//This class implements the CompanyDAO interface with mySQL
/**
 * 
 * This Class implements the CoompanyDAO interface, using mySQL server c3po connection pool. 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class CompanyDBDAO implements CompanyDAO{
	// *********
	// Attribute
	// *********
	private static CompanyDBDAO instace = null;

	// ***************
	// *****Methods***
	// ***************
	public static CompanyDBDAO getInstace() {
		if (instace == null) {
			return new CompanyDBDAO();
		}
		return instace;
	}
	
	// A method that gets Company instance that should be of a new company and adds it to the company table in the db
	@Override
	public void createCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException {
		if (!Util.passwordvalidation(company.getPassword())) {
			throw new IllegalPasswordException(Constants.PasswordErrorMassage);
		} else if (Util.isCompany(company)) {
			throw new CompanyAlreadyExistsException(Constants.UserNameErrorMassage);
		} else if (Util.isEmailExist(company)) {
			throw new EmailAlreadyExistsException(Constants.EmailAlreadyExistsMassage);
		} else {
			// getting a connection to DB from pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				// Insert prepared statement
				PreparedStatement createStmt = myCon.prepareStatement(
						"insert into company (" 
							+ CompanyTableColumnNames.COMP_NAME + ","
							+ CompanyTableColumnNames.PASSWORD + "," 
							+ CompanyTableColumnNames.EMAIL
							+ ") values (?,?,?);"); // id will be assign in the DB
				// Values 
				createStmt.setString(1, company.getCompName());
				createStmt.setString(2, company.getPassword());
				createStmt.setString(3, company.getEmail());
				// Execute
				createStmt.executeUpdate(); 
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	
	// A method that gets an instance of an existing company and removes it from the company table in the db
	@Override
	public void removeCompany(Company company) throws CouponSystemException, CompanyDoesNotExistException {
		if (!Util.isCompany(company)){
			throw new CompanyDoesNotExistException("Company does not exist in DB");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Delete prepared statement
				PreparedStatement removeStmt = myCon.prepareStatement(
						"delete from company where" 
						+ CompanyTableColumnNames.ID + "= ? and " 
						+ CompanyTableColumnNames.COMP_NAME + "= ? and "
						+ CompanyTableColumnNames.PASSWORD + "= ?");
				// Values
				removeStmt.setLong(1, company.getId());
				removeStmt.setString(2, company.getCompName());
				removeStmt.setString(3, company.getPassword());
				// Execute
				removeStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}

	// A method that gets a company that exists in DB and updates its details 
	@Override
	public void updateCompany(Company company) throws CouponSystemException, IllegalPasswordException,
			CompanyAlreadyExistsException, EmailAlreadyExistsException {
		if (!Util.passwordvalidation(company.getPassword())) {
			throw new IllegalPasswordException(Constants.PasswordErrorMassage);
		} else if (Util.isCompany(company)) {
			throw new CompanyAlreadyExistsException(Constants.UserNameErrorMassage);
		} else if (Util.isEmailExist(company)) {
			throw new EmailAlreadyExistsException(Constants.EmailAlreadyExistsMassage);
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Update prepared statement
				PreparedStatement updateStmt = myCon.prepareStatement(
						"update company set " 
						+ CompanyTableColumnNames.EMAIL + "= ?, "
						+ CompanyTableColumnNames.PASSWORD + "= ? "
						+ "where " + CompanyTableColumnNames.COMP_NAME + "= ?");
				// Values
				updateStmt.setString(1, company.getEmail());	
				updateStmt.setString(2, company.getPassword());
				updateStmt.setString(3, company.getCompName());				
				// Execute
				updateStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				e.printStackTrace();
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}
	}
	
	// A method that gets a company's ID and coupon's ID and update the company_coupon table in the DB
	@Override
	public void addCompanyCoupon(long compId, long couponId) throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Update prepared statement
			PreparedStatement updateStmt = myCon.prepareStatement( 	
					"insert into company_coupon ("
					+ JoinTablesColumnNames.COMP_ID + ","
					+ JoinTablesColumnNames.COUPON_ID + ") values (?,?);");
			// Values
			updateStmt.setLong(1, compId);	
			updateStmt.setLong(2, couponId);
			// Execute
			updateStmt.executeUpdate();
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	// A method that gets a company's ID and coupon's ID and removes it from the company_coupon table in the DB
	@Override
	public void removeCompanyCoupon(long compId, long couponId)
			throws CouponSystemException, CompanyCouponDoesNotExistsException {
		if (!Util.isCompanyCoupon(couponId, compId)) {
			throw new CompanyCouponDoesNotExistsException("No such coupons for this company");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				// Update prepared statement
				PreparedStatement deleteStmt = myCon.prepareStatement( 	
						"delete from company_coupon where "
						+ JoinTablesColumnNames.COMP_ID + "= ? and " 
						+ JoinTablesColumnNames.COUPON_ID + "= ? ");
				// Values
				deleteStmt.setLong(1, compId);	
				deleteStmt.setLong(2, couponId);
										
				// Execute
				deleteStmt.executeUpdate();
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
			}
		}				
	}
	
	// A method that gets a company's name and password, looks for the line in company table in the db with that name and password
	// creates a company instance with the details taken from the db and returns it 
	@Override
	public Company getCompany(String compName, String password) throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// make prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from company where "
					+ CompanyTableColumnNames.COMP_NAME + " = ? and "
					+ CompanyTableColumnNames.PASSWORD + "= ?");
			// Values
			selectStmt.setString(1, compName);
			selectStmt.setString(2, password);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			myRs.next();
			
			//return company
			return Util.resultSetToCompany(myRs);
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}	
	}
	
	// A method that gets a company's ID, looks for the line in company table in the db with that ID
	// creates a company instance with the details taken from the db and returns it 
	@Override
	public Company getCompany(long id) throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// make prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from company where "
					+ CompanyTableColumnNames.ID +"= ?");	
			// Value
			selectStmt.setLong(1, id);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			myRs.next();	
			//return company
			return Util.resultSetToCompany(myRs);
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
	
	// A method that returns a collection of all the companies in the DB company table 
	@Override
	public Collection<Company> getAllCompanies() throws CouponSystemException {
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			//declaring a collection of companies
			List<Company> companies = new ArrayList<>();
			//prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement("select * from company");
			// Execute and get a resultSet 
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a List of Company
			// each iteration is converted into a Company instance and added to the list
			while(myRs.next()){
				companies.add(Util.resultSetToCompany(myRs));
			}
			//return List<Company>
			return companies;	
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}

	//a method that returns a collection of all the coupons that belongs to a company
	@Override
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException {
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// getting a connection to DB from  pool
			//statement - going to Company_Coupon table and getting the list of the coupons that relates to a company.
			PreparedStatement selectStmt = myCon.prepareStatement(
					"SELECT * FROM coupon "
					+ "JOIN company_coupon "
					+ "ON coupon.ID = company_coupon." + JoinTablesColumnNames.COUPON_ID
					+ " WHERE company_coupon." + JoinTablesColumnNames.COMP_ID + "= ?");
			// Value 
			selectStmt.setLong(1, id);
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();
			// Processing resultSet into a Collection of Coupon
			Collection<Coupon> coupons = new ArrayList<>();
			// Iterating the resultSet -
			// each iteration is converted into a Coupon instance and added to the List
			while (myRs.next()) {
				coupons.add(Util.resultSetToCoupn(myRs));
			}
			// Return coupon
			return coupons;
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
				
	// This method should take company's name and password as argument 
	// and return a boolean indicating a successful login or not 
	@Override
	public boolean login(String compName, String password) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// Select prepared statement
			PreparedStatement loginStmt = myCon.prepareStatement(
					"select * from company where "
					+ CompanyTableColumnNames.COMP_NAME + "= ? and "
					+ CompanyTableColumnNames.PASSWORD + "= ?");
			//Values
			loginStmt.setString(1, compName);
			loginStmt.setString(2, password);	
			// Execute and get a resultSet
			ResultSet myRs = loginStmt.executeQuery();
			// Return true if there is a match
			return myRs.next();
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException(Constants.CouponSystemExceptionMassage + e.getMessage(), e);
		}
	}
	
	// ****************
	// UniqueCouponType
	// ****************
	/**
	 * Returns a collection of of all the CouponTaypes of the Coupons owned by a specific Company
	 * @param company The Company for which the collection of CouponType is returned
	 * @return Collection of CouponType
	 * @throws CouponSystemException
	 */
	public Collection<CouponType> getUniqueCouponTypes(Company company) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			// Select prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"SELECT DISTINCT " + CouponTableColumnNames.TYPE 
						+ " FROM coupon JOIN company_coupon "
						+ "ON coupon.ID = company_coupon." + JoinTablesColumnNames.COUPON_ID
						+ " WHERE company_coupon." + JoinTablesColumnNames.COMP_ID + "= ?");
			//Values
			selectStmt.setLong(1, company.getId());		
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