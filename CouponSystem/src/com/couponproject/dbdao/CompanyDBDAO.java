package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.*;

import com.couponproject.beans.*;
import com.couponproject.constants.CouponTableColumnNames;
import com.couponproject.constants.CouponType;
import com.couponproject.dao.CompanyDAO;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.util.Util;

//This class implements the CompanyDAO interface with mySQL
public class CompanyDBDAO implements CompanyDAO{
	// *********
	// Attribute
	// *********
	private static CompanyDBDAO instace = null;

	// ***********
	// constructor
	// ***********
	private CompanyDBDAO(){
		}

	// ***************
	// *****Methods***
	// ***************

	// Get instance
	public static CompanyDBDAO getInstace() {
		if (instace == null) {
			return new CompanyDBDAO();
		}
		return instace;
	}
	
	
	
	//a method that gets Company instance that should be of a new company and adds it to the company table in the db
	public void createCompany(Company company) throws CouponSystemException, IllegalPasswordException, CompanyAlreadyExistsException {
		
		if(!Util.passwordvalidation(company.getPassword())){
			throw new IllegalPasswordException(
					"Password must contain:\n"
					+ "6-10 characters\n"
					+ "At lest one upper case letter\n"
					+ "At lest one lower case letter\n"
					+ "At lest one digit");		
		}
		else if (Util.isCompany(company)){
			throw new CompanyAlreadyExistsException(
					"User name already exists in DB");	
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
							
				// Insert prepared statement
				PreparedStatement createStmt = myCon.prepareStatement(
						"insert into "
						+ "company (COMP_NAME, PASSWORD, EMAIL) "
						+ "values (?,?,?);"); //id will be assign in the DB
				
				// Values 
				createStmt.setString(1, company.getCompName());
				createStmt.setString(2, company.getPassword());
				createStmt.setString(3, company.getEmail());
				
				// Execute
				createStmt.executeUpdate();


			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}
	}

	
	//a method that gets an instance of an existing (!!!) company and removes it from the company table in the db
	public void removeCompany(Company company) throws CouponSystemException, CompanyDoesNotExistException {
		if (!Util.isCompany(company)){
			throw new CompanyDoesNotExistException(
					"Company does not exist exception");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				
				// Delete prepared statement
				PreparedStatement removeStmt = myCon.prepareStatement(
						"delete from company "
						+ "where ID = ? and COMP_NAME = ? and PASSWORD = ?");

				// Values
				removeStmt.setLong(1, company.getId());
				removeStmt.setString(2, company.getCompName());
				removeStmt.setString(3, company.getPassword());
				
				// Execute
				removeStmt.executeUpdate();
				
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}
	}

	@Override
	//a method that gets a company that exists (!!!) in the company table in the updates details in the db
	//TODO: the instance should include all the details from the existing line in db beside what that was changed
	public void updateCompany(Company company) throws CouponSystemException, IllegalPasswordException, CompanyAlreadyExistsException {
		
		if(!Util.passwordvalidation(company.getPassword())){
			throw new IllegalPasswordException(
					"Password must contain:\n"
					+ "6-10 characters\n"
					+ "At lest one upper case letter\n"
					+ "At lest one lower case letter\n"
					+ "At lest one digit");	
		}
		else if (Util.isCompany(company)){
			throw new CompanyAlreadyExistsException(
					"User name already exists in DB");	
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				
				// Update prepared statement
				PreparedStatement updateStmt = myCon.prepareStatement(
								"update company " 
								+ "set EMAIL = ? and set PASSWORD = ? where COMP_NAME = ?");

				// Values
				updateStmt.setString(1, company.getEmail());	
				updateStmt.setString(2, company.getPassword());
				updateStmt.setString(3, company.getCompName());
				
										
				// Execute
				updateStmt.executeUpdate();

				//close connection
				myCon.close();
				
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}
	}
	
	@Override
	//a method that gets a company's ID and coupon's ID and update the company_coupon table in the DB
	public void addCompanyCoupon(long compId, long couponId) throws CouponSystemException {
		
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
			
			// Update prepared statement
			PreparedStatement updateStmt = myCon.prepareStatement( 	"insert into "
							+ "company_coupon (COMP_ID, COUPON_ID) "
							+ "values (?,?);");

			// Values
			updateStmt.setLong(1, compId);	
			updateStmt.setLong(2, couponId);
			
									
			// Execute
			updateStmt.executeUpdate();
			
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
				
	}

	@Override
	//a method that gets a company's ID and coupon's ID and removes it from the company_coupon table in the DB
	public void removeCompanyCoupon(long compId, long couponId) throws CouponSystemException, CompanyCouponDoesNotExistsException {
		if(!Util.isCompanyCoupon(couponId, compId)){
			throw new CompanyCouponDoesNotExistsException(
					"No such coupons for this company");
		} else {
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()) {
				
				// Update prepared statement
				PreparedStatement deleteStmt = myCon.prepareStatement( 	"delete from company_coupon "
						+ "where COMP_ID = ? and COUPON_ID = ? ");

				// Values
				deleteStmt.setLong(1, compId);	
				deleteStmt.setLong(2, couponId);
				
										
				// Execute
				deleteStmt.executeUpdate();

				
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
		}				
	}
	
	
	// a method that gets a company's name and password, looks for the line in company table in the db with that name and password
	// creates a company instance with the details taken from the db and returns it 
	@Override
	public Company getCompany(String compName, String password) throws CouponSystemException {
		
		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			
			// make prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from company "
					+ "where COMP_NAME = ? and PASSWORD = ?");
					
			// Values
			selectStmt.setString(1, compName);
			selectStmt.setString(2, password);
			
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();

			// Processing resultSet into a Company(bean) instance
			myRs.next();
			Company comp = new Company(myRs.getLong("ID"),
					myRs.getString("COMP_NAME"), 
					myRs.getString("PASSWORD"), 
					myRs.getString("EMAIL"));	
	
			//return company
			return comp;
			
		} catch (PropertyVetoException | SQLException | IOException e) {
			
			throw new CouponSystemException("CouponSystemException", e);
		}
				
	}
	
	// a method that gets a company's ID, looks for the line in company table in the db with that ID
		// creates a company instance with the details taken from the db and returns it 
		@Override
		public Company getCompany(long id) throws CouponSystemException {
			
			// getting a connection to DB from  pool
			try (Connection myCon = ConnectionPool.getInstance().getConnection()){
				
				// make prepared statement
				PreparedStatement selectStmt = myCon.prepareStatement(
						"select * from company "
						+ "where ID = ?");
						
				// Value
				selectStmt.setLong(1, id);
				
				// Execute and get a resultSet
				ResultSet myRs = selectStmt.executeQuery();

				// Processing resultSet into a Company(bean) instance
				myRs.next();
				String compName = myRs.getString("COMP_NAME");
				String password = myRs.getString("PASSWORD");
				String email = myRs.getString("EMAIL");
				
				Company comp = new Company(id, compName, password, email);	
		
				//return company
				return comp;
				
			} catch (PropertyVetoException | SQLException | IOException e) {
				throw new CouponSystemException("CouponSystemException", e);
			}
					
		}
	
	
	
	// a method that gets a collection of all the companies in the company table on the db
	@Override
	public Collection<Company> getAllCompanies() throws CouponSystemException {

		// getting a connection to DB from  pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
						
			//declaring a collection of companies
			List<Company> companies = new ArrayList<>();
			
			//prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from company ");
			
			// Execute and get a resultSet 
			ResultSet myRs = selectStmt.executeQuery();
			
			// Processing resultSet into a List of Company
			// each iteration is converted into a Company instance and added to the list
			while(myRs.next()){
				Company company = new Company(
						myRs.getLong("ID"),
						myRs.getString("COMP_NAME"),
						myRs.getString("PASSWORD"),
						myRs.getString("EMAIL"));
				companies.add(company);
			}
			
			//return List<Company>
			return companies;	
			
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	
	@Override
	//a method that get a collection of all the coupons that belongs to a company
	//in the instruction the method does not get company, but I changed it
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException {
		
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			// getting a connection to DB from  pool
			
			//statement - going to Company_Coupon table and getting the list of the coupons that relates to a company.
			PreparedStatement selectStmt = myCon.prepareStatement(
					// TODO: read about Relational Division ???
					"SELECT * FROM coupon "
					+ "JOIN company_coupon "
					+ "ON coupon.ID = company_coupon.COUPON_ID "
					+ "WHERE company_coupon.COMP_ID = " + id);
			
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();

			// Processing resultSet into a Collection of Coupon
			// ---------------------------------------------------
			// Declaring a set of 'Coupon's
			Collection<Coupon> coupons = new ArrayList<>();

			// Iterating the resultSet -
			// each iteration is converted into a Coupon instance and added to the List
			while (myRs.next()) {
				// Generating Coupon
				Coupon coupon = new Coupon(
						myRs.getLong("ID"),
						myRs.getString("TITLE"), 
						// converting sql.Date to LocalDate
						myRs.getDate("START_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
						// converting sql.Date to LocalDate
						myRs.getDate("END_DATE").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
						myRs.getInt("AMOUNT"), 
						CouponType.valueOf(myRs.getString("TYEP")),
						myRs.getString("MESSAGE"), 
						myRs.getDouble("PRICE"), 
						myRs.getString("IMAGE"));
				// Adding to set
				coupons.add(coupon);
			}

			// Return coupon
			return coupons;
	
		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}
		
		
	//a method that gets company name and password and returns whether they correct
	//TODO: exception for compName that does not exists here or when the name is entered by the user? 
	@Override
	public boolean login(String compName, String password) throws CouponSystemException {

		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			
			// Select prepared statement
			PreparedStatement loginStmt = myCon.prepareStatement(
					"select * from company "
							+ "where COMP_NAME = ? and PASSWORD = ?");
					
			//Values
			loginStmt.setString(1, compName);
			loginStmt.setString(2, password);
					
			// Execute and get a resultSet
			ResultSet myRs = loginStmt.executeQuery();
			
			// Return true if there is a match
			return myRs.next();

		} catch (PropertyVetoException | SQLException | IOException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}
	
	// ****************
	// UniqueCouponType
	// *****************
	public Collection<CouponType> getUniqueCouponTypes(Company company) throws CouponSystemException {
		// getting a connection to DB from pool
		try (Connection myCon = ConnectionPool.getInstance().getConnection()) {

			// Select prepared statement
			PreparedStatement selectStmt = myCon
					.prepareStatement("SELECT DISTINCT CouponTableColumnNames.TYPE FROM coupon JOIN company_coupon "
							+ "ON coupon.ID = company_coupon.COUPON_ID "
							+ "WHERE company_coupon.COMP_ID = ?");

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
			e.getMessage();
			e.printStackTrace();
			throw new CouponSystemException("CouponSystemException", e);
			///////////////
			////////
			///// problem here 
			///////
		}
	}
	
}
