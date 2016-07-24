package com.couponproject.facade;

import java.util.Collection;

import com.couponproject.beans.*;
import com.couponproject.dbdao.*;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.exception.IllegalPasswordException;

public class AdminFacade{

	// ***********
	// constructor
	// ***********
	public AdminFacade() {}

	// ***************
	// *****Methods***
	// ***************
	// Login
	public static AdminFacade login(String name, String password){
		if (name.equals("0") && password.equals("0")) {
			return new AdminFacade();
		} 
		return null;
	}
	
	// ***************
	// Company methods
	// ***************
	public void createCompany(Company company) throws AdminFacadeException, IllegalPasswordException, CompanyAlreadyExistsException {
		try {
			// Invoking the createCompany method in CompanyDBDAO
			CompanyDBDAO.getInstace().createCompany(company);

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCompany() - Error");
		}
	}

	public void removeCompany(Company company) throws AdminFacadeException, CouponDoesNotExistException, CompanyDoesNotExistException, CompanyCouponDoesNotExistsException {
		try {
			// Deleting all company's coupons by invoking the getCoupons method in **CustomerDBDAO**
			for (Coupon coupon : CompanyDBDAO.getInstace().getCoupons(company.getId())) {
				//deleting from Coupon table
				CouponDBDAO.getInstace().removeCoupon(coupon);
				//deleting from Company_Coupon table
				CompanyDBDAO.getInstace().removeCompanyCoupon(company.getId(), coupon.getId());
				//deleting from Cust_Coupon table
				CouponDBDAO.getInstace().removeCouponCustomerByCouponID(coupon.getId());
			}
					
			// Invoking the removeCompany method in CompanyDBDAO
			CompanyDBDAO.getInstace().removeCompany(company);
			 

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "removeCompany() - Error");
		}
	}

	public void updateCompany(Company company) throws AdminFacadeException, IllegalPasswordException, CompanyAlreadyExistsException {
		try {
			// Invoking the updateCompany method in CompanyDBDAO
			CompanyDBDAO.getInstace().updateCompany(company);

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "updateCompany() - Error");
		}
	}

	public Company getCompany(long id) throws AdminFacadeException {
		// TODO check if Company exist
		try {
			// Invoking the getCompany method in CompanyDBDAO
			return CompanyDBDAO.getInstace().getCompany(id);

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getCompany() - Error");
		}
	}

	public Collection<Company> getAllCompanies() throws AdminFacadeException {
		try {
			// Invoking the getAllCompanies method in CompanyDBDAO
			return CompanyDBDAO.getInstace().getAllCompanies();

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getAllCompanies() - Error");
		}

	}
	
	// ***************
	// Customer method
	// ***************
	public void createCustomer(Customer customer) throws AdminFacadeException, IllegalPasswordException, CustomerAlreadyExistsException {
		try {
			// Invoking the createCompany method in CustomerDBDAO
			CustomerDBDAO.getInstace().createCustomer(customer);

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCustomer() - Error");
		}
	}

	public void removeCustomer(Customer customer) throws AdminFacadeException, CouponDoesNotExistException, CustomerDoesNotExistException {
		// TODO check if Customer exist
		try {
			// Deleting all customer's coupons by invoking the getCoupons method in CustomerDBDAO
			for (Coupon coupon : CustomerDBDAO.getInstace().getCoupons(customer.getId())) {
				CouponDBDAO.getInstace().removeCoupon(coupon);
			}
			
			// Invoking the removeCustomer method in CustomerDBDAO
			CustomerDBDAO.getInstace().removeCustomer(customer);
			
			
			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "removeCustomer() - Error");
		}
	}

	public void updateCustomer(Customer customer) throws AdminFacadeException, IllegalPasswordException, CustomerAlreadyExistsException {
		// TODO check if Customer exist
		try {
			// Invoking the updateCustomer method in CustomerDBDAO
			CustomerDBDAO.getInstace().updateCustomer(customer);

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "updateCustomer() - Error");
		}
	}

	public Customer getCustomer(long id) throws AdminFacadeException {
		// TODO check if Customer exist
		try {
			// Invoking the getCostomer method in CustomerDBDAO
			return CustomerDBDAO.getInstace().getCustomer(id);

			// Catching couponSystemException
		} catch (CouponSystemException e) {
			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getCustomer() - Error");
		}
	}

	public Collection<Customer> getAllCustomers() throws AdminFacadeException {
		// TODO check if Customer exist
		try {
			// Invoking the getAllCustomers method in CustomerDBDAO
			return CustomerDBDAO.getInstace().getAllCustomer();

			// Catching couponSystemException
		} catch (CouponSystemException e) {

			// In case of a problem throw new AdminFacadeException
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getAllCustomers() - Error");
		}

	}
}
