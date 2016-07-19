package com.couponproject.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.Actionlisteners.CompanysCouponActionListener;
import com.couponproject.gui.Actionlisteners.PurchasedCouponsActionListener;

public class CompanyMainFrame extends JFrame{
	private JTable tableCouponData;
	
	public CompanyMainFrame(CompanyFacade companyFacade){
		// frame properties
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		CenterPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblCouponPic = new JLabel("");
		CenterPanel.add(lblCouponPic);

		tableCouponData = new JTable();
		CenterPanel.add(new JScrollPane(tableCouponData));
		
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
		// ActionListeners & Buttons 
		// *************************
		// Coupon tables ActionListeners 
		ListSelectionModel selectedModel = tableCouponData.getSelectionModel();
		selectedModel.addListSelectionListener(e -> {
			int selectedRow = selectedModel.getMinSelectionIndex();
			System.out.println(selectedRow);
		});
		
		// All Company's Coupons
		JButton btnCompCoupons = new JButton("All Company's Coupons");
		btnCompCoupons.addActionListener(
				new CompanysCouponActionListener(tableCouponData, westPanel, companyFacade));
		
		// ***************************	
		// Adding buttons to westPanel
		// ***************************
		westPanel.add(btnCompCoupons);
		
	}
}
