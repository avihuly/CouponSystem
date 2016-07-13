package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CustomerFacadeException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.GuiUtil;

public class AllCouponsActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	JTable tableCouponData;
	JPanel Panel;
	CustomerFacade customerFacade;

	// ***********
	// constructor
	// ***********
	public AllCouponsActionListener(JTable tableCouponData, JPanel Panel, CustomerFacade customerFacade) {
		this.tableCouponData = tableCouponData;
		this.Panel = Panel;
		this.customerFacade = customerFacade;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// load Coupons to table
			GuiUtil.CouponsToTable(tableCouponData, customerFacade.getAllPurchasedCoupons());
			
			// clear panel
			Panel.removeAll();
			// Generating buttons by relevant coupons
			CouponType tempCouponType = null;
			// Iterating All PurchasedCoupons - for checking relevant coupon types
			for (Coupon coupon : customerFacade.getAllPurchasedCoupons()) {
				// each type will generate one button 
				if (tempCouponType != coupon.getType()) {
					tempCouponType = coupon.getType();
					JButton tempBnt = new JButton(tempCouponType.name());
					// ActionListener
					tempBnt.addActionListener(typeE -> {
						try {
							GuiUtil.CouponsToTable(tableCouponData,
									customerFacade.getAllPurchasedCouponsByType(CouponType.valueOf(tempBnt.getText())));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					});
					Panel.add(tempBnt);
				}
			}
			
			// Back button
			JButton backBnt = new JButton("Back");
			backBnt.addActionListener(backE -> {
				// clear panel
				Panel.removeAll();
				JButton btnMyCoupons = new JButton("All My Coupons");
				btnMyCoupons.addActionListener(
						new AllCouponsActionListener(tableCouponData, Panel, customerFacade));
				Panel.add(btnMyCoupons);
				Panel.revalidate();
				Panel.repaint();
			});
			Panel.add(backBnt);
			
			
			// reprint panel
			Panel.revalidate();
			Panel.repaint();

		} catch (CustomerFacadeException custE) {
			// TODO Auto-generated catch block
			custE.printStackTrace();
		}
	}
}
