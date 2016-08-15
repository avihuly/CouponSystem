package com.couponproject.constants;

/**
 * This class is used to specify a selection of table index's and String's 
 * that are commonly used trout the code 
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 * 
 */
public class Constants {
	// Coupon table index's
	public static final int couponTableMESSAGEIndex = 1;
	public static final int couponTableEndDateIndex = 4;
	public static final int couponTablePriceIndex = 5;
	public static final int couponTableIDIndex = 6;

	// Customer table index's
	public static final int CustomerTableIDIndex = 0;

	// Company table index's
	public static final int CompanyTableIDIndex = 0;

	// Massages
	public static final String CouponSystemExceptionMassage = "CouponSystemException: ";
	public static final String CouponTitleAlreadyMassage = "Coupon title already exist in DB";
	public static final String UnExpectedErrorMassage = "An unexpected error occurred. please try again later.";
	public static final String UserNameErrorMassage = "User name already exists in DB.";
	public static final String EmailAlreadyExistsMassage = "Email already exists in DB.";
	public static final String PasswordErrorMassage = 
			"Password must contain:\n" 
			+ "6-10 characters\n"
			+ "At lest one upper case letter\n" 
			+ "At lest one lower case letter\n" 
			+ "At lest one digit";
}
