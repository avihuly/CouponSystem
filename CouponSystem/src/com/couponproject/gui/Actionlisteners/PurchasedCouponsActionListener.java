package com.couponproject.gui.Actionlisteners;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.frames.CustomerMainFrame;
import com.couponproject.gui.frames.helpers.GuiUtil;

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
			
			// ----------------------------------------------------------------
			// Step 2 - generate buttons for coupon by relevant types and price  
			// ----------------------------------------------------------------
			Panel.removeAll();  	// clear panel
			
			Panel.setLayout(new BorderLayout());
			
			// Price Slider panel //
			JPanel pricePanel = new JPanel();
			JSlider priceSlider = new CouponByPriceSliders(tableCouponData, customerFacade);
			pricePanel.add(priceSlider);
			pricePanel.setBorder(BorderFactory.createTitledBorder("All my coupons by price"));
			Panel.add(pricePanel, BorderLayout.NORTH);
			
			// Button panel //
			JPanel bntPanel = new JPanel(new GridLayout(8, 1, 0, 0));
			for (CouponType couponType: customerFacade.getUniqueCouponTypes()){
				// Each type will generate a button
				JButton tempBnt = new JButton(couponType.name());
				// ActionListener - GuiUtil.CouponsToTable()
				tempBnt.addActionListener(typeE -> {
					try {
						GuiUtil.CouponsToTable(tableCouponData,
								customerFacade.getAllPurchasedCouponsByType(CouponType.valueOf(tempBnt.getText())));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				});
				bntPanel.add(tempBnt);
			}
			
			// ---------------------------
			// Step 3 - All coupons button   
			// ---------------------------
			JButton allkBnt = new JButton("All Coupons");
			allkBnt.addActionListener(allE -> {
				try {
					GuiUtil.CouponsToTable(tableCouponData, customerFacade.getAllPurchasedCoupons());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			bntPanel.add(allkBnt);
			
			// --------------------
			// Step 4 - Back button   
			// --------------------
			JButton backBnt = new JButton("Back");
			backBnt.addActionListener(backE -> {
				CustomerMainFrame.setCustomerHomeBntLayout(tableCouponData, Panel, customerFacade);
			});
			bntPanel.add(backBnt);
			
			// -----------------------------
			// Step 5 - revalidate & repaint
			// -----------------------------			
			Panel.add(bntPanel,BorderLayout.CENTER);
			Panel.revalidate();
			Panel.repaint();

		} catch (CouponSystemException custE) {
			custE.printStackTrace();
		}
	}
}
