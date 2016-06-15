package com.couponproject.dbdao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.couponproject.beans.Company;
import com.couponproject.beans.Coupon;
import com.couponproject.beans.CouponType;
import com.couponproject.dao.CompanyDAO;

//This class implements the CompanyDAO interface with mySQL
public class CompanyDBDAO implements CompanyDAO{
	//TODO: closing connections
	@Override
	//a method that gets Company instance that should be of a new company ant adds it to the company table in the db
	//TODO: check if the company is new, at this stage or at earlier stage?
	public void createCompany(Company company) throws SQLException {
		// opening a connection to the db
		Connection myCon = MysqlConnection.getConnection();
		
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
	}

	@Override
	//a method that gets an instance of an existing (!!!) company and removes it from the company table in the db
	public void removeCompany(Company company) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
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
		
	}

	@Override
	//a method that gets a company that exists (!!!) in the company table in the updates details in the db
	//TODO: what info can be changed?? compny name - no...
	//TODO: the instance should include all the details from the existing line in db beside what that was changed
	public void updateCompany(Company company) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
				
		// Update prepared statement
		PreparedStatement updateStmt = myCon.prepareStatement(
						"update company " + "set EMAIL = ? " + "set PASSWORD + ?"
						+ "where ID = ? and CAST_NAME = ? ");

		// Values
		updateStmt.setString(1, company.getEmail());	
		updateStmt.setString(2, company.getPassword());
		updateStmt.setLong(3, company.getId());
		updateStmt.setString(4, company.getCompName());
								
		// Execute
		updateStmt.executeUpdate();

	}

	// a method that gets a company's ID, looks for the line in company table in the db with that ID
	// creates a company instance with the details taken from the db and returns it 
	@Override
	public Company getCompany(long id) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		// make prepared statement
		PreparedStatement compStmt = myCon.prepareStatement(
				"select from company WHERE ID = ?");
				
		// Value
		compStmt.setLong(1, id);
		
		ResultSet rs = compStmt.executeQuery();
		String compName = rs.getString("COMP_NAME");
		String password = rs.getString("PASSWORD");
		String email = rs.getString("EMAIL");
		
		Company comp = new Company(id, compName, password, email);
		
		
		return comp;
	}
	// a method that gets a collection of all the companies in the company table on the db
	@Override
	public Collection<Company> getAllCompanies() throws SQLException {

		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		//declaring a connection of companies
		
		List<Company> companies = new ArrayList<>();
		
		//prepared statement
		PreparedStatement stmt = myCon.prepareStatement("select * from company ");
		//a result set 
		ResultSet rs = stmt.executeQuery();
		
		// a loop that goes over the result set line by line and makes a company instance of every line
		while(rs.next()){
			long id = rs.getLong("ID");
			String compName = rs.getString("COMP_NAME");
			String password = rs.getString("PASSWORD");
			String email = rs.getString("EMAIL");
			companies.add(new Company(id, compName, password, email));
		}
				
		return companies;
	}

	@Override
	
	//a method that get a collection of all the coupons that belongs to a company
	//in the instruction the method does not get company, but I changed it
	public Collection<Coupon> getCoupons(/*Company company*/) throws SQLException {
		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		// creating a list of coupons
		List<Coupon> coupons = new ArrayList<>();
		
		//statement - going to Company_Coupon table and getting the list of the coupons that relates to a company.
		PreparedStatement stmt = myCon.prepareStatement("select * from company_coupon where COMP_ID = ? ");
		
		//value
		stmt.setLong(1, company.getId());
		
		//ResultSet
		ResultSet rs = stmt.executeQuery();
		
		//a outer loop that collects the coupon IDs relating to the company
		while(rs.next()){
			long couponID = rs.getLong("COUPON_ID");
			
			//a prepared statement to go to coupon table and take all of the coupon details
			PreparedStatement couponStmt = myCon.prepareStatement("select * from coupon where ID = ? ");
			couponStmt.setLong(1, couponID);
			//getting result set from table
			ResultSet couponRs = couponStmt.executeQuery();
				//a inner loop to get coupon details from a line and enter it to a coupon instance
				while(couponRs.next()){
					long id = couponRs.getLong("ID");
					String title = couponRs.getString("TITLE");
					Date startDate = couponRs.getDate("START_DATE");
					Date endDate = couponRs.getDate("END_DATE");
					int amount = couponRs.getInt("AMOUNT");
					String type = couponRs.getString("TYPE");
					String message = couponRs.getString("MESSAGE");
					double price = couponRs.getDouble("PRICE");
					String image = couponRs.getString("IMAGE");
					//changing string type to enum
					CouponType theType = CouponType.valueOf(type);
					
					//creating a coupon instance in the coupons list
					//TODO: after changing the id assignment in the coupon constructor the below should be changed
					coupons.add(new Coupon(/*id,*/ title, startDate, endDate, amount, theType, message, price, image));
					
				}
		}
		
		
		return coupons;
	}

	//a method that gets company name and password and returns whether they correct
	//TODO: exception for compName that does not exists here or when the name is entered by the user? 
	@Override
	public boolean login(String compName, String password) throws SQLException {

		// Creating a Connection object to the DB
		Connection myCon = MysqlConnection.getConnection();
		
		PreparedStatement loginStmt = myCon.prepareStatement("select * from company where COMP_NAME = ?");
		
		//Value
		loginStmt.setString(1, compName);
		
		//Result set
		ResultSet rs = loginStmt.executeQuery();
		
		String compareToPassword = rs.getString("PASSWORD");

		if(password == compareToPassword){
			return true;
		}
		else return false;
	}
	

}
