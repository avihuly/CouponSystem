package com.couponproject.threads;

import java.time.LocalDate;

import com.couponproject.beans.Coupon;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;

/*
 *  This is the DailyCouponExportationTask thread
 *  it's runs every 24 hours and deletes all expired 
 *  coupons from the DB/
 */

public class DailyCouponExportationTask implements Runnable {
	
	// Quit boolean 
	private volatile boolean quit = false;

	@Override
	public void run() {
		while (!quit) {
			try {
				// get all coupons from DB
				for (Coupon coupon : CouponDBDAO.getInstace().getAllCoupons()) {

					// check if coupon end date as past
					if (LocalDate.now().isAfter(coupon.getEndDate())) {
						CouponDBDAO.getInstace().removeCoupon(coupon);
					}
				}
				// Sleep for 24 Hours
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (CouponSystemException | InterruptedException | CouponDoesNotExistException e) {
				// TODO what souled happen here??!!?!?!
				e.printStackTrace();
			}
		}
	}
	
	public void stopTask(){
		quit = true;
	}
	
	
	
}