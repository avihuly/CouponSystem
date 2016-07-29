package com.couponproject.tableAlignment;

import java.time.LocalDate;
import java.util.Collection;

import com.couponproject.beans.Company;
import com.couponproject.beans.Coupon;
import com.couponproject.beans.Customer;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.dbdao.CustomerDBDAO;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;

public class TableAlignment {

	public static void main(String[] args) {
		couponTableAlignment();
		companyTableAlignment();
		customerTableAlignment();
		companyCouponTableAlignment();
		customerCouponTableAlignment();
	}

	public static void couponTableAlignment() {
		try {
			Collection<Coupon> coupons = CouponDBDAO.getInstace().getAllCoupons();
			for (Coupon coupon : coupons) {
//				long couponId = coupon.getId();
//				//removing the coupon from customer_coupon table
//				CouponDBDAO.getInstace().removeCouponCustomerByCouponID(couponId);
//				//removing the coupon from company_coupon table
//				CouponDBDAO.getInstace().removeCouponCompanyByCouponID(couponId);
				//removing the coupon from coupon tableW
				CouponDBDAO.getInstace().removeCoupon(coupon);
			}
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CouponDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// adding new coupons to coupon table from electricity
		for (int i = 0; i < 10; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("ELECTRICITYsdc " + i + 1), LocalDate.now(),
					LocalDate.of(2017, 11, 23), ((int) Math.random() * 102), CouponType.ELECTRICITY,
					"message" + (i + 1), (Math.random() * 10 + 20.99), "image" + (i + 1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
		// adding new coupons to coupon table from Camping
		for (int i = 10; i < 20; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("CAMPING " + i + 1), LocalDate.now(), LocalDate.of(2017, 11, 15),
					((int) Math.random() * 102), CouponType.CAMPING, "message" + (i + 1), (Math.random() * 10 + 20.99),
					"image" + (i + 1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}

		// adding new coupons to coupon table from Food
		for (int i = 20; i < 30; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("FOOD " + i + 1), LocalDate.now(), LocalDate.of(2017, 11, 23),
					((int) Math.random() * 102), CouponType.FOOD, "message" + (i + 1), (Math.random() * 10 + 20.99),
					"image" + (i + 1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
		// adding new coupons to coupon table from Health
		for (int i = 30; i < 40; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("HEALTH " + i + 1), LocalDate.now(), LocalDate.of(2017, 11, 23),
					((int) Math.random() * 102), CouponType.HEALTH, "message" + (i + 1), (Math.random() * 10 + 20.99),
					"image" + (i + 1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
		// adding new coupons to coupon table from Sports
		for (int i = 40; i < 50; i++) {
			// Coupon instance
			Coupon coupon = new Coupon("title" + ("SPORTS " + i + 1), LocalDate.now(), LocalDate.of(2017, 11, 23),
					((int) Math.random() * 102), CouponType.SPORTS, "message" + (i + 1), (Math.random() * 10 + 20.99),
					"image" + (i + 1));
			try {
				// This is the hart of the test
				// converting coupon object into a sql query and running it
				try {
					CouponDBDAO.getInstace().createCoupon(coupon);
				} catch (CouponTitleAlreadyExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (CouponSystemException e) {
				System.out.println("createCouponTest Error");
			}
		}
	}

	public static void companyTableAlignment() {
		// removing all companies from company table
		try {
			Collection<Company> companies = CompanyDBDAO.getInstace().getAllCompanies();
			for (Company company : companies) {
				CompanyDBDAO.getInstace().removeCompany(company);
			}
		} catch (CouponSystemException | CompanyDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// adding new companies to company table
		for (int i = 0; i < 10; i++) {
			// Company instance
			Company company = new Company("company " + (i + 7000), "Password" + (i + 1),
					"Email@" + (i + 7000) + ".com");
			try {
				// This is the hart of the test
				// converting company object into a sql query and running it
				CompanyDBDAO.getInstace().createCompany(company);

			} catch (CouponSystemException | IllegalPasswordException | CompanyAlreadyExistsException e) {
				System.out.println("createCustomerTest Error");
				e.printStackTrace();
			} catch (EmailAlreadyExistsException e) {
				System.out.println("createCustomerTest Error");
				e.printStackTrace();
			}
		}
	}

	public static void customerTableAlignment() {
		// removing all customers from customer table
		try {
			Collection<Customer> customers = CustomerDBDAO.getInstace().getAllCustomer();
			for (Customer customer : customers) {
				CustomerDBDAO.getInstace().removeCustomer(customer);
			}
		} catch (CouponSystemException | CustomerDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// adding new customers to customer table
		for (int i = 0; i < 10; i++) {
			// Customer instance
			Customer customer = new Customer("customer " + (i + 99), "AaBbC" + (i + 99));
			try {
				CustomerDBDAO.getInstace().createCustomer(customer);
			} catch (CouponSystemException | CustomerAlreadyExistsException | IllegalPasswordException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public static void companyCouponTableAlignment(){
		//empty the company_coupon table
		try {
			CouponDBDAO.getInstace().emptyCouponCompany();
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add new information to the company_coupon table
		try {
			Collection<Company> companies = CompanyDBDAO.getInstace().getAllCompanies();
			int numOfCompanies = companies.size();
			int outerCounter=0;
			long firstCompanyID=0;
			for(Company company:companies){
				firstCompanyID=company.getId();
				break;};
			long companyID=firstCompanyID;
			Collection<Coupon> coupons = CouponDBDAO.getInstace().getAllCoupons();
			for(Coupon coupon:coupons){
				int inerCounter = 0;
				CompanyDBDAO.getInstace().addCompanyCoupon(companyID, coupon.getId());
				inerCounter++;
				if(inerCounter>6 && outerCounter<numOfCompanies){
					companyID++;
					outerCounter++;
				}
			}
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void customerCouponTableAlignment(){
		//empty the company_coupon table
		try {
			CouponDBDAO.getInstace().emptyCustomerCompany();
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add new information to the company_coupon table
		try {
			Collection<Customer> customers = CustomerDBDAO.getInstace().getAllCustomer();
			int numOfCompanies = customers.size();
			int outerCounter=0;
			long firstCustomerID=0;
			for(Customer customer:customers){
				firstCustomerID=customer.getId();
				break;};
			long customerID=firstCustomerID;
			Collection<Coupon> coupons = CouponDBDAO.getInstace().getAllCoupons();
			for(Coupon coupon:coupons){
				int inerCounter = 0;
				CustomerDBDAO.getInstace().addCouponToCustomer(customerID, coupon.getId());
				inerCounter++;
				if(inerCounter>6 && outerCounter<numOfCompanies){
					customerID++;
					outerCounter++;
				}
			}
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
