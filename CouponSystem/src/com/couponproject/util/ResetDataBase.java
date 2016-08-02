package com.couponproject.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;


import com.couponproject.constants.CouponType;
import com.couponproject.beans.*;
import com.couponproject.dbdao.*;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;


public class ResetDataBase {

	public static void execute() {
		drpoAndCreateTables();
		couponTableAlignment();
		companyTableAlignment();
		customerTableAlignment();
		companyCouponTableAlignment();
		customerCouponTableAlignment();
	}

	private static void drpoAndCreateTables() {
		try (Connection myCon = ConnectionPool.getInstance().getConnection()){
			
			Statement stmt = myCon.createStatement();
			
			stmt.executeUpdate("DROP TABLE company;");
			stmt.executeUpdate("DROP TABLE company_coupon;");
			stmt.executeUpdate("DROP TABLE customer;");
			stmt.executeUpdate("DROP TABLE customer_coupon;");
			stmt.executeUpdate("DROP TABLE coupon;");

			stmt.execute("CREATE TABLE `company` ("
					+ "`ID` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`COMP_NAME` varchar(45) NOT NULL,"
					+ "`PASSWORD` varchar(45) NOT NULL,"
					+ "`EMAIL` varchar(45) NOT NULL,"
					+ "PRIMARY KEY (`ID`)"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=4000 DEFAULT CHARSET=utf8;");

			stmt.execute("CREATE TABLE `company_coupon` ("
  					+ "`COMP_ID` int(11) NOT NULL,"
  					+ "`COUPON_ID` int(11) NOT NULL,"
  					+ "PRIMARY KEY (`COUPON_ID`,`COMP_ID`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

			stmt.execute("CREATE TABLE `customer` ("
  					+ "`ID` int(11) NOT NULL AUTO_INCREMENT,"
  					+ "`CUST_NAME` varchar(45) NOT NULL,"
  					+ "`PASSWORD` varchar(45) NOT NULL,"
  					+ "PRIMARY KEY (`ID`)"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;");

			stmt.execute("CREATE TABLE `customer_coupon` ("
  					+ "`CUST_ID` int(11) NOT NULL,"
  					+ "`COUPON_ID` int(11) NOT NULL,"
  					+ "PRIMARY KEY (`CUST_ID`,`COUPON_ID`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

			stmt.execute("CREATE TABLE `coupon` ("
  					+ "`ID` int(11) NOT NULL AUTO_INCREMENT,"
  					+ "`TITLE` varchar(200) DEFAULT NULL,"
  					+ "`START_DATE` date DEFAULT NULL,"
  					+ "`END_DATE` date DEFAULT NULL,"
  					+ "`AMOUNT` int(11) DEFAULT NULL,"
  					+ "`TYPE` varchar(45) DEFAULT NULL,"
  					+ "`MESSAGE` varchar(1000) DEFAULT NULL,"
  					+ "`PRICE` double DEFAULT NULL,"
  					+ "`IMAGE` varchar(45) DEFAULT NULL,"
  					+ "PRIMARY KEY (`ID`)"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=3000 DEFAULT CHARSET=utf8;");
			
		} catch (SQLException | IOException |PropertyVetoException e) {
			e.printStackTrace();
		} 
	}

	private static void couponTableAlignment() {
		// adding new coupons to coupon table from electricity
		for (int i = 0; i < 10; i++) {
			// Coupon instance
			Coupon coupon = new Coupon(
					"title" + ("ELECTRICITY " + i + 1),
					LocalDate.now(),
					LocalDate.of(2017, 11, 23), 
					((int) Math.random() * 102), CouponType.ELECTRICITY,
					"message" + (i + 1),
					((int)(Math.random() * 10) + 20.99),
					"image/CouponPics/ELECTRICITY.jpg");
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					e.printStackTrace();
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
		// adding new coupons to coupon table from Camping
		for (int i = 10; i < 20; i++) {
			// Coupon instance
			Coupon coupon = new Coupon(
					"title" + ("CAMPING " + i + 1), 
					LocalDate.now(), 
					LocalDate.of(2017, 11, 15),
					((int) Math.random() * 102), 
					CouponType.CAMPING, "message" + (i + 1), 
					((int)(Math.random() * 10) + 20.99),
					"image/CouponPics/CAMPING.png");
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}

		// adding new coupons to coupon table from Food
		for (int i = 20; i < 30; i++) {
			// Coupon instance
			Coupon coupon = new Coupon(
					"title" + ("FOOD " + i + 1),
					LocalDate.now(), 
					LocalDate.of(2017, 11, 15),
					((int) Math.random() * 102), 
					CouponType.FOOD, "message" + (i + 1), 
					((int)(Math.random() * 10) + 20.99),
					"image/CouponPics/FOOD.jpg");
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
		// adding new coupons to coupon table from Health
		for (int i = 30; i < 40; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("HEALTH " + i + 1),
					LocalDate.now(), 
					LocalDate.of(2017, 11, 15),
					((int) Math.random() * 102), 
					CouponType.HEALTH, "message" + (i + 1), 
					((int)(Math.random() * 10) + 20.99),
					"image/CouponPics/HEALTH.png");
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
		// adding new coupons to coupon table from Sports
		for (int i = 40; i < 50; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("SPORTS " + i + 1), 
					LocalDate.now(), 
					LocalDate.of(2017, 11, 15),
					((int) Math.random() * 102), 
					CouponType.SPORTS, "message" + (i + 1), 
					((int)(Math.random() * 20) + 20.99),
					"image/CouponPics/SPORTS.jpg");
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
	}

	private static void companyTableAlignment() {
		// adding new companies to company table
		for (int i = 0; i < 10; i++) {
			// Company instance
			Company company = new Company("company " + (i + 7000), "Password" + (i + 1),
					"Email@" + (i + 7000) + ".com");
			try {
				// This is the hart of the test
				// converting company object into a sql query and running it
				CompanyDBDAO.getInstace().createCompany(company);

			} catch (CouponSystemException | IllegalPasswordException | CompanyAlreadyExistsException | EmailAlreadyExistsException e) {
				e.printStackTrace();
			} 
		}
	}

	private static void customerTableAlignment() {
		// adding new customers to customer table
		for (int i = 0; i < 10; i++) {
			// Customer instance
			Customer customer = new Customer("customer " + (i + 99), "AaBbC" + (i + 99));
			try {
				CustomerDBDAO.getInstace().createCustomer(customer);
			} catch (CouponSystemException | CustomerAlreadyExistsException | IllegalPasswordException e) {
				e.printStackTrace();
			}
		}

	}

	private static void companyCouponTableAlignment() {
		// add new information to the company_coupon table
		try {
			Collection<Company> companies = CompanyDBDAO.getInstace().getAllCompanies();
			int numOfCompanies = companies.size();
			int outerCounter = 0;
			long firstCompanyID = 0;
			for (Company company : companies) {
				firstCompanyID = company.getId();
				break;
			}
			;
			long companyID = firstCompanyID;
			Collection<Coupon> coupons = CouponDBDAO.getInstace().getAllCoupons();
			for (Coupon coupon : coupons) {
				int inerCounter = 0;
				CompanyDBDAO.getInstace().addCompanyCoupon(companyID, coupon.getId());
				inerCounter++;
				if (inerCounter > 6 && outerCounter < numOfCompanies) {
					companyID++;
					outerCounter++;
				}
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}

	private static void customerCouponTableAlignment() {
		// add new information to the company_coupon table
		try {
			Collection<Customer> customers = CustomerDBDAO.getInstace().getAllCustomer();
			int numOfCompanies = customers.size();
			int outerCounter = 0;
			long firstCustomerID = 0;
			for (Customer customer : customers) {
				firstCustomerID = customer.getId();
				break;
			}
			;
			long customerID = firstCustomerID;
			Collection<Coupon> coupons = CouponDBDAO.getInstace().getAllCoupons();
			for (Coupon coupon : coupons) {
				int inerCounter = 0;
				CustomerDBDAO.getInstace().addCouponToCustomer(customerID, coupon.getId());
				inerCounter++;
				if (inerCounter > 6 && outerCounter < numOfCompanies) {
					customerID++;
					outerCounter++;
				}
			}
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
}
