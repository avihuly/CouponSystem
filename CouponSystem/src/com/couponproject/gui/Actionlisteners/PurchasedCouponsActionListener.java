package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.couponproject.constants.CouponType;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.GuiUtil;

public class PurchasedCouponsActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	JTable tableCouponData;
	JPanel Panel;
	CustomerFacade customerFacade;

	// ***********
	// constructor
	// ***********
	public PurchasedCouponsActionListener(JTable tableCouponData, JPanel Panel, CustomerFacade customerFacade) {
		this.tableCouponData = tableCouponData;
		this.Panel = Panel;
		this.customerFacade = customerFacade;
	}
	
	// ***************
	// actionPerformed 
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// ----------------------------------
			// Step 1 - load all Coupons to table    
			// ----------------------------------
			GuiUtil.CouponsToTable(tableCouponData, customerFacade.getAllPurchasedCoupons());
			
			// -----------------------------------------------------
			// Step 2 - generate buttons for coupon by relevant types   
			// ------------------------------------------------------
			Panel.removeAll();  	// clear panel
			for (CouponType couponType: customerFacade.getUniqueCouponTypes()){
				// Each type will generate a button
				JButton tempBnt = new JButton(couponType.name());
				// ActionListener - GuiUtil.CouponsToTable()
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
			
			// ---------------------------
			// Step 3 - All coupons button   
			// ---------------------------
			JButton allkBnt = new JButton("All Coupons");
			allkBnt.addActionListener(allE -> {
				try {
					GuiUtil.CouponsToTable(tableCouponData, customerFacade.getAllPurchasedCoupons());
				} catch (Exception e1) {}
			});
			Panel.add(allkBnt);
			
			// --------------------
			// Step 4 - Back button   
			// --------------------
			JButton backBnt = new JButton("Back");
			backBnt.addActionListener(backE -> {
				// clear panel
				Panel.removeAll();
				JButton btnMyCoupons = new JButton("All My Coupons");
				btnMyCoupons.addActionListener(
						new PurchasedCouponsActionListener(tableCouponData, Panel, customerFacade));
				Panel.add(btnMyCoupons);
				Panel.revalidate();
				Panel.repaint();
			});
			Panel.add(backBnt);
			
			
			// -----------------------------
			// Step 5 - revalidate & repaint
			// -----------------------------			
			Panel.revalidate();
			Panel.repaint();

		} catch (CouponSystemException custE) {
			// TODO Auto-generated catch block
			custE.printStackTrace();
		}
	}
}
