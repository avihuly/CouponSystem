package com.couponproject.facade;

import java.util.Collection;
import com.couponproject.beans.*;
import com.couponproject.exception.*;
import com.couponproject.system.CouponSystem;

/**
 * This Class represents the Admin client of the Coupon System. 
 * <p>This class includes methods for all the operations Admin can perform in the system</P>
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class AdminFacade{
	// ***************
	// *****Methods***
	// ***************
	/**
	 * Returns AdminFacade instance upon a successful login or null if login fails
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
	 * Creates new Company in the DB based from a given Company instance
	 * @param company Company instance
	 * @throws AdminFacadeException
	 * @throws IllegalPasswordException
	 * @throws CompanyAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public void createCompany(Company company) throws AdminFacadeException, IllegalPasswordException, CompanyAlreadyExistsException, EmailAlreadyExistsException {
		try {
			// Invoking the createCompany method in CompanyDBDAO
			CouponSystem.getInstance().getCompanyDBDAO().createCompany(company);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCompany() - Error: " + e.getMessage(), e);
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
			for (Coupon coupon : CouponSystem.getInstance().getCompanyDBDAO().getCoupons(company.getId())) {
				// Deleting coupons from Company_Coupon table
				CouponSystem.getInstance().getCompanyDBDAO().removeCompanyCoupon(company.getId(), coupon.getId());
				// Deleting coupons from Cust_Coupon table
				CouponSystem.getInstance().getCouponDBDAO().removeCouponCustomerByCouponID(coupon.getId());
				// Deleting coupons from Coupon table
				CouponSystem.getInstance().getCouponDBDAO().removeCoupon(coupon);
			}
			// Deleting company from Company table
			CouponSystem.getInstance().getCompanyDBDAO().removeCompany(company);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "removeCompany() - Error: " + e.getMessage(), e);
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
			CouponSystem.getInstance().getCompanyDBDAO().updateCompany(company);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "updateCompany() - Error: " + e.getMessage(), e);
		}
	}

	/**
	 * Returns Company instance based on a given Company's ID
	 * @param id Company's ID
	 * @return Company instance
	 * @throws AdminFacadeException
	 */
	public Company getCompany(long id) throws AdminFacadeException {
		try {
			// Invoking the getCompany method in CompanyDBDAO
			return CouponSystem.getInstance().getCompanyDBDAO().getCompany(id);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getCompany() - Error: " + e.getMessage(), e);
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
			return CouponSystem.getInstance().getCompanyDBDAO().getAllCompanies();
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getAllCompanies() - Error: " + e.getMessage(), e);
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
			CouponSystem.getInstance().getCustomerDBDAO().createCustomer(customer);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "createCustomer() - Error: " + e.getMessage(), e);
		}
	}

	/**
	 * Removes an existing Customer from the DB based on a given Customer instance.
	 * <p>All of this Customer's purchased Coupons are deleted from the DB from customer_coupon table</p>
	 * @param customer Customer instance
	 * @throws AdminFacadeException
	 * @throws CouponDoesNotExistException
	 * @throws CustomerDoesNotExistException
	 */
	public void removeCustomer(Customer customer) throws AdminFacadeException, CouponDoesNotExistException, CustomerDoesNotExistException {
		try {
			// Deleting all customer's coupons
			for (Coupon coupon : CouponSystem.getInstance().getCustomerDBDAO().getCoupons(customer.getId())) { 
				CouponSystem.getInstance().getCouponDBDAO().removeCouponCustomerByCouponID(coupon.getId());
			}
			// Deleting customer from DB
			CouponSystem.getInstance().getCustomerDBDAO().removeCustomer(customer);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "removeCustomer() - Error: " + e.getMessage(), e);
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
		try {
			// Invoking the updateCustomer method in CustomerDBDAO
			CouponSystem.getInstance().getCustomerDBDAO().updateCustomer(customer);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "updateCustomer() - Error: " + e.getMessage(), e);
		}
	}

	/**
	 * Returns Customer instance based on a given Customer's ID
	 * @param id Customer's ID
	 * @return Customer instance
	 * @throws AdminFacadeException
	 */
	public Customer getCustomer(long id) throws AdminFacadeException {
		try {
			// Invoking the getCostomer method in CustomerDBDAO
			return CouponSystem.getInstance().getCustomerDBDAO().getCustomer(id);
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getCustomer() - Error: " + e.getMessage(), e);
		}
	}

	/**
	 * Returns a collection of all the Customers in the DB
	 * @return Collection of Customers
	 * @throws AdminFacadeException
	 */
	public Collection<Customer> getAllCustomers() throws AdminFacadeException {
		try {
			// Invoking the getAllCustomers method in CustomerDBDAO
			return CouponSystem.getInstance().getCustomerDBDAO().getAllCustomer();
		} catch (CouponSystemException e) {
			throw new AdminFacadeException("AdminFacadeException - " 
					+ "getAllCustomers() - Error: " + e.getMessage(), e);
		}
	}
}