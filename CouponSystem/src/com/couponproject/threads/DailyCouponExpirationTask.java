package com.couponproject.threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import com.couponproject.beans.Coupon;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.system.CouponSystem;

/**
 * This is the DailyCouponExportationTask thread. It runs every 24 hours and deletes all expired coupons from the DB.
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class DailyCouponExpirationTask extends Thread {
	
	// Quit boolean 
	/**
	 * Holds the volatile boolean quit flag. While this parameter is false the DailyCouponExportationTask is running. 
	 */
	private volatile boolean quit = false;

	/**
	 * The DailyCouponExportationTask thread run method.
	 * <p>This method delete all the expired coupons from the coupon table, company_coupon table and customer_coupon table 
	 * in the DB./p>
	 */
	@Override
	public void run() {
		while (!quit) {
			try {
				// get all coupons from DB
				for (Coupon coupon : CouponSystem.getInstance().getCouponDBDAO().getAllCoupons()) {
					// check if coupon end date as past
					if (LocalDate.now().isAfter(coupon.getEndDate())) {
						//delete coupon from company_coupon table
						CouponSystem.getInstance().getCouponDBDAO().removeCouponCompanyByCouponID(coupon.getId());
						// delete coupon from customer_coupon table
						CouponSystem.getInstance().getCouponDBDAO().removeCouponCustomerByCouponID(coupon.getId());
						// delete the coupon from coupon table
						CouponSystem.getInstance().getCouponDBDAO().removeCoupon(coupon);
						// write to log
						FileWriter fstream = new FileWriter("logs/DailyCouponExpirationTaskLOG.txt",true);
						BufferedWriter out = new BufferedWriter(fstream);
						out.write("DELETE: " + coupon.toString()+"\n");
						out.close();
					}
				}
				
				// Sleep for 24 Hours
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (CouponSystemException | InterruptedException | CouponDoesNotExistException | IOException e) {
				e.printStackTrace();
				try (FileWriter fstream = new FileWriter("logs/DailyCouponExpirationTaskLOG.txt",true);){
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(e.toString());
					out.close();
				} catch (IOException e1) {}
			}
		}
	}
	
	/**
	 * Turns the volatile boolean quit flag to true - ends the DailyCouponExportationTask thread's running 
	 */
	public void stopTask(){
		quit = true;
	}
}