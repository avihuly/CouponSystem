package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.couponproject.constants.CouponType;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.GuiUtil;

public class CompanysCouponActionListener implements ActionListener{

	// **********
	// Attributes
	// **********
	JTable tableCouponData;
	JPanel Panel;
	CompanyFacade companyFacade;

	// ***********
	// constructor
	// ***********
	public CompanysCouponActionListener(JTable tableCouponData, JPanel Panel, CompanyFacade companyFacade) {
		this.tableCouponData = tableCouponData;
		this.Panel = Panel;
		this.companyFacade = companyFacade;
	}
		
		
	// ***************
	// actionPerformed 
	// ***************
	public void actionPerformed(ActionEvent a) {
		try {
			// ----------------------------------
			// Step 1 - load all Coupons to table    
			// ----------------------------------
			GuiUtil.CouponsToTable(tableCouponData, companyFacade.getAllCoupons());
			
			// -----------------------------------------------------
			// Step 2 - generate buttons for coupon by relevant types   
			// ------------------------------------------------------
			Panel.removeAll();  	// clear panel
			for (CouponType couponType: companyFacade.getUniqueCouponTypes()){
				// Each type will generate a button
				JButton tempBnt = new JButton(couponType.name());
				// ActionListener - GuiUtil.CouponsToTable()
				tempBnt.addActionListener(typeE -> {
					try {
						GuiUtil.CouponsToTable(tableCouponData,
								companyFacade.getCouponByType(CouponType.valueOf(tempBnt.getText())));
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
					GuiUtil.CouponsToTable(tableCouponData, companyFacade.getAllCoupons());
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
				JButton btnCompCoupons = new JButton("All Company's Coupons");
				btnCompCoupons.addActionListener(
						new CompanysCouponActionListener(tableCouponData, Panel, companyFacade));
				Panel.add(btnCompCoupons);
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
