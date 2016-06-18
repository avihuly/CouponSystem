package com.couponproject.dbdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.couponproject.beans.Company;
import com.couponproject.beans.Coupon;
import com.couponproject.beans.CouponType;
import com.couponproject.dao.CompanyDAO;
import com.couponproject.exception.CouponSystemException;

//This class implements the CompanyDAO interface with mySQL
public class CompanyDBDAO implements CompanyDAO{

	//a method that gets Company instance that should be of a new company ant adds it to the company table in the db
	//TODO: check if the company is new, at this stage or at earlier stage?
	public void createCompany(Company company) throws CouponSystemException {
		
		Connection myCon;
		try {
			// opening a connection to the db
			myCon = MysqlConnection.getConnection();
			
			// Insert prepared statement
			PreparedStatement createStmt = myCon.prepareStatement(
					"insert into "
					+ "company (COMP_NAME, PASSWORD, EMAIL) "
					+ "values (?,?);"); //id will be assign in the DB
			
			// Values 
			createStmt.setString(1, company.getCompName());
			createStmt.setString(2, company.getPassword());
			createStmt.setString(3, company.getEmail());
			
			// Execute
			createStmt.executeUpdate();
			
			//close connection
			myCon.close();

		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
		
	}

	
	//a method that gets an instance of an existing (!!!) company and removes it from the company table in the db
	public void removeCompany(Company company) throws CouponSystemException {
		
		Connection myCon;
		try {
			// Creating a Connection object to the DB
			myCon = MysqlConnection.getConnection();
			// Delete prepared statement
			PreparedStatement deleteStmt = myCon.prepareStatement(
					"delete from company "
					+ "where ID = ? and CAST_NAME = ? and PASSWORD = ?");

			// Values
			deleteStmt.setLong(1, company.getId());
			deleteStmt.setString(2, company.getCompName());
			deleteStmt.setString(3, company.getPassword());
			
			// Execute
			deleteStmt.executeUpdate();
			
			//close connection
			myCon.close();

		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
		
	}

	@Override
	//a method that gets a company that exists (!!!) in the company table in the updates details in the db
	//TODO: what info can be changed?? compny name - no...
	//TODO: the instance should include all the details from the existing line in db beside what that was changed
	public void updateCompany(Company company) throws CouponSystemException {
		
				Connection myCon;
		try {
			// Creating a Connection object to the DB
			myCon = MysqlConnection.getConnection();
			// Update prepared statement
			PreparedStatement updateStmt = myCon.prepareStatement(
							"update company " 
							+ "set EMAIL = ? and set PASSWORD = ? and set COMP_NAME"
							+ "where ID = ? ");

			// Values
			updateStmt.setString(1, company.getEmail());	
			updateStmt.setString(2, company.getPassword());
			updateStmt.setString(3, company.getCompName());
			updateStmt.setLong(4, company.getId());
			
									
			// Execute
			updateStmt.executeUpdate();

			//close connection
			myCon.close();
			
		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
				
	}

	// a method that gets a company's ID, looks for the line in company table in the db with that ID
	// creates a company instance with the details taken from the db and returns it 
	@Override
	public Company getCompany(long id) throws CouponSystemException {
		
		Connection myCon;
		try {
			// Creating a Connection object to the DB
			myCon = MysqlConnection.getConnection();
			// make prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from company "
					+ "where ID = ?");
					
			// Value
			selectStmt.setLong(1, id);
			
			// Execute and get a resultSet
			ResultSet myRs = selectStmt.executeQuery();

			// Processing resultSet into a Company(bean) instance
			String compName = myRs.getString("COMP_NAME");
			String password = myRs.getString("PASSWORD");
			String email = myRs.getString("EMAIL");
			
			Company comp = new Company(id, compName, password, email);

			//close connection
			myCon.close();		
	
			//return company
			return comp;
			
		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
				
	}
	
	
	// a method that gets a collection of all the companies in the company table on the db
	@Override
	public Collection<Company> getAllCompanies() throws CouponSystemException {

		Connection myCon;
		try {
			// Creating a Connection object to the DB
			myCon = MysqlConnection.getConnection();
			//declaring a collection of companies
			List<Company> companies = new ArrayList<>();
			
			//prepared statement
			PreparedStatement selectStmt = myCon.prepareStatement(
					"select * from company ");
			
			// Execute and get a resultSet 
			ResultSet myRs = selectStmt.executeQuery();
			
			// Processing resultSet into a Collection of Company
			// each iteration is converted into a Company instance and added to the list
			while(myRs.next()){
				long id = myRs.getLong("ID");
				String compName = myRs.getString("COMP_NAME");
				String password = myRs.getString("PASSWORD");
				String email = myRs.getString("EMAIL");
				companies.add(new Company(id, compName, password, email));
			}
			
			//close connection
			myCon.close();
			
			//return List<Company>
			return companies;	
		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}

	
	@Override
	//a method that get a collection of all the coupons that belongs to a company
	//in the instruction the method does not get company, but I changed it
	public Collection<Coupon> getCoupons(long id) throws CouponSystemException {
		
		Connection myCon;
		try {
			// Creating a Connection object to the DB
			myCon = MysqlConnection.getConnection();
			
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
			Collection<Coupon> couptSet = new HashSet<>();

			// Iterating the resultSet -
			// each iteration is converted into a Coupon instance and added to the set
			while (myRs.next()) {
				// Generating Coupon
				Coupon coupon = new Coupon(
						myRs.getLong("ID"),
						myRs.getString("TITLE"), 
						myRs.getDate("START_DATE"),
						myRs.getDate("END_DATE"), 
						myRs.getInt("AMOUNT"), 
						CouponType.valueOf(myRs.getString("TYEP")),
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
	
		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystemException", e);
		}
	}
		
		
	//a method that gets company name and password and returns whether they correct
	//TODO: exception for compName that does not exists here or when the name is entered by the user? 
	@Override
	public boolean login(String compName, String password) throws CouponSystemException {

		Connection myCon;
		try {
			// Creating a Connection object to the DB
			myCon = MysqlConnection.getConnection();
			// Select prepared statement
			PreparedStatement loginStmt = myCon.prepareStatement(
					"select * from company "
							+ "where COMP_NAME = ? and PASSWORD = ?");
					
			//Values
			loginStmt.setString(1, compName);
			loginStmt.setString(2, password);
					
			// Execute and get a resultSet
			ResultSet myRs = loginStmt.executeQuery();
			
			boolean login = myRs.next();

			//close connection
			myCon.close();

			// Return true if there is a match
			return login;

		} catch (SQLException e) {
			throw new CouponSystemException("CouponSystenException", e);
		}
	}
	
}
