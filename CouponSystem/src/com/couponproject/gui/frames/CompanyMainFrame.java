package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.DimensionUIResource;

import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.CompanysCouponActionListener;
import com.couponproject.gui.Actionlisteners.NewCompanysCouponActionListener;

public class CompanyMainFrame extends JFrame{
	private JTable tableCouponData=new JTable();
	
	public CompanyMainFrame(CompanyFacade companyFacade){
		// frame properties
		super("Coupons (logged as company)");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);
		
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
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 750, 75);
		
		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(10, 1, 0, 0));
			
	
		// *************************
		// Buttons & ActionListeners  
		// *************************		
		// All Company's Coupons Button - to show all of the company's coupons in a table
		JButton btnCompCoupons = new JButton("All Company's Coupons");
		btnCompCoupons.addActionListener(
				new CompanysCouponActionListener(tableCouponData, westPanel, companyFacade));
		//Add coupon Button - enables the user to add new coupon to the company
		JButton btnAddCoupon = new JButton("Add New Coupon");
		//TODO: completing the NewCompanysCouponActionListener class
		btnAddCoupon.addActionListener(new NewCompanysCouponActionListener(companyFacade));
		
		
		// ***************************	
		// Adding buttons to westPanel
		// ***************************
		westPanel.add(btnCompCoupons);
		westPanel.add(btnAddCoupon);
	}
}
