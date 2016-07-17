package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTable;

public class AllCouponsActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	JTable tableCouponData;
	JPanel Panel;
	
	// ***********
	// constructor
	// ***********
	public AllCouponsActionListener(JTable tableCouponData, JPanel Panel) {
		this.tableCouponData = tableCouponData;
		this.Panel = Panel;
	}
	
	// ***************
	// actionPerformed 
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
