package com.couponproject.gui.frames;

import java.awt.*;
import javax.swing.*;

import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.Actionlisteners.CompanysCouponActionListener;
import com.couponproject.gui.Actionlisteners.NewCompanysCouponActionListener;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class CompanyMainFrame extends TemplateFrame{
	private JTable tableCouponData=new JTable();
	
	public CompanyMainFrame(CompanyFacade companyFacade){
		// frame properties
		super("Coupons (logged as company)",750,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		// ***********
		// East Panel
		// ***********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// ***********
		// Center Panel
		// ***********
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new BorderLayout());

		JLabel lblCouponPic = new JLabel("");
		CenterPanel.add(lblCouponPic, BorderLayout.SOUTH);

		JScrollPane sp = new JScrollPane(tableCouponData);
		CenterPanel.add(sp, BorderLayout.CENTER);
		sp.setBorder(BorderFactory.createEmptyBorder());
		
		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(12, 1, 0, 0));
			
	
		// *************************
		// Buttons & ActionListeners  
		// *************************		
		// All Company's Coupons Button - to show all of the company's coupons in a table
		JButton btnCompCoupons = new JButton("All Company's Coupons");
		btnCompCoupons.addActionListener(
				new CompanysCouponActionListener(tableCouponData, westPanel, companyFacade));
		//Add coupon Button - enables the user to add new coupon to the company
		JButton btnAddCoupon = new JButton("Add New Coupon");
		btnAddCoupon.addActionListener(new NewCompanysCouponActionListener(companyFacade));
		
		
		// ***************************	
		// Adding buttons to westPanel
		// ***************************
		westPanel.add(btnCompCoupons);
		westPanel.add(btnAddCoupon);
	}
}
