package com.couponproject.gui.Actionlisteners;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.frames.CustomerMainFrame;
import com.couponproject.system.CouponSystem;

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

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		// ----------------------------------
		// Step 1 - load all Coupons to table
		// ----------------------------------
		try {
			GuiUtil.CouponsToTable(tableCouponData, CouponSystem.getInstance().getCouponDBDAO().getAllCoupons());
		} catch (CouponSystemException e1) {
			e1.printStackTrace();
		}
		
		// ---------------------------------------------------
		// Step 2 - add purchase coupon MouseListener to table
		// ---------------------------------------------------
		MouseAdapter doubleClickToPerches = new PurchaseCouponMouseListener(customerFacade);
		tableCouponData.addMouseListener(doubleClickToPerches);
		
		// -----------------------------------------------------
		// Step 3 - generate buttons for coupon by relevant types
		// ------------------------------------------------------
		Panel.removeAll(); // clear panel
		
		for (CouponType couponType : CouponType.values()) {
			// Each type will generate a button
			JButton tempBnt = new JButton(couponType.name());
			// ActionListener - GuiUtil.CouponsToTable()
			tempBnt.addActionListener(typeE -> {
				try {
					GuiUtil.CouponsToTable(tableCouponData,
							CouponSystem.getInstance().getCouponDBDAO().getCouponsByType(CouponType.valueOf(tempBnt.getText())));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			Panel.add(tempBnt);
		}

		// ---------------------------
		// Step 4 - All coupons button
		// ---------------------------
		JButton allkBnt = new JButton("All Coupons");
		allkBnt.addActionListener(allE -> {
		
			try {
				GuiUtil.CouponsToTable(tableCouponData, CouponSystem.getInstance().getCouponDBDAO().getAllCoupons());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Panel.add(allkBnt);
		
		// --------------------
		// Step 5 - Back button   
		// --------------------
		JButton backBnt = new JButton("Back");
		backBnt.addActionListener(backE -> {
			// Remove purchase coupon MouseListener
			tableCouponData.removeMouseListener(doubleClickToPerches);
			// Set Customer Home Button Layout
			CustomerMainFrame.setCustomerHomeBntLayout(tableCouponData, Panel, customerFacade);		
		});
		Panel.add(backBnt);
		
		// -----------------------------
		// Step 6 - revalidate & repaint
		// -----------------------------			
		Panel.revalidate();
		Panel.repaint();
		
	}

}
