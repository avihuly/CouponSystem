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
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;

/**
 * This Class represents the Admin client of the Coupon System. 
 * <p>This class includes all the operations Admin can perform in the system</P>
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class AdminFacade{

	// ***********
	// constructor
	// ***********
	/**
	 * Construct the AdminFacade
	 */
	public AdminFacade() {}

	// ***************
	// *****Methods***
	// ***************
	// Login
	/**
	 * Returns AdminFacade instance upon a successful login and null if login fails
	 * @param name Admin's User Name
	 * @param password Admin's Password
	 * @return AdminFacade instance
	 */
	public static AdminFacade login(String name, String password){
		if (name.equals("0") && password.equals("0")) {
			return new AdminFacade();
		} 
		return null;
	}
	
	// ***************
	// Company methods
	// ***************
	/**
	 * Creates new Company in the DB based on a given Company instance
	 * @param company Company instance
	 * @throws AdminFacadeException
	 * @throws IllegalPasswordException
	 * @throws CompanyAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public void createCompany(Company company) throws AdminFacadeException, IllegalPasswordException, CompanyAlreadyExistsException, EmailAlreadyExistsException {
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

	/**
	 * Removes an existing Company from the DB based on a given Company instance.
	 * <p>All of this Company's Coupons are deleted from the DB from coupon table, company_coupon table</p>
	 * and customer_coupon table 
	 * @param company Company instance
	 * @throws AdminFacadeException
	 * @throws CouponDoesNotExistException
	 * @throws CompanyDoesNotExistException
	 * @throws CompanyCouponDoesNotExistsException
	 */
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

	/**
	 * Updates an existing Company in the DB based on a given Company instance
	 * @param company Company instance
	 * @throws AdminFacadeException
	 * @throws IllegalPasswordException
	 * @throws CompanyAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public void updateCompany(Company company) throws AdminFacadeException, IllegalPasswordException, CompanyAlreadyExistsException, EmailAlreadyExistsException {
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

	/**
	 * Returns Company instance based on a given Company's ID
	 * @param id Company's ID
	 * @return Company instance
	 * @throws AdminFacadeException
	 */
	public Company getCompany(long id) throws AdminFacadeException {
		// TODO check if Company exist (isCompany)
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

	/**
	 * Returns a collection of all the Companies in the DB
	 * @return Collection of companies
	 * @throws AdminFacadeException
	 */
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
	/**
	 * Creates new Customer in the DB based on a given Customer instance
	 * @param customer Customer instance
	 * @throws AdminFacadeException
	 * @throws IllegalPasswordException
	 * @throws CustomerAlreadyExistsException
	 */
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

	/**
	 * Removes an existing Customer from the DB based on a given Customer instance.
	 * <p>All of this Customer's purchased Coupons are deleted from the DB from customer_coupon table</p>
	 * 
	 * @param customer Customer instance
	 * @throws AdminFacadeException
	 * @throws CouponDoesNotExistException
	 * @throws CustomerDoesNotExistException
	 */
	public void removeCustomer(Customer customer) throws AdminFacadeException, CouponDoesNotExistException, CustomerDoesNotExistException {
		// TODO check if Customer exist
		try {
			// Deleting all customer's coupons by invoking the getCoupons method in CustomerDBDAO
			for (Coupon coupon : CustomerDBDAO.getInstace().getCoupons(customer.getId())) {
				//TODO: this is a wrong? should remove from customer_coupon table and not from coupon table 
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

	/**
	 * Updates an existing Customer in the DB based on a given Company instance.
	 * @param customer Customer instance
	 * @throws AdminFacadeException
	 * @throws IllegalPasswordException
	 * @throws CustomerAlreadyExistsException
	 */
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

	/**
	 * Returns Customer instance based on a given Customer's ID
	 * @param id Customer's ID
	 * @return Customer instance
	 * @throws AdminFacadeException
	 */
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

	/**
	 * Returns a collection of all the Customers in the DB
	 * @return Collection of Customers
	 * @throws AdminFacadeException
	 */
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
