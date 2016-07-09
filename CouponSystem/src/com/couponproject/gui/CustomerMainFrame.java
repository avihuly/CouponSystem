package com.couponproject.gui;

import java.awt.*;
import javax.swing.*;

import com.couponproject.exception.CustomerFacadeException;
import com.couponproject.facade.CustomerFacade;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMainFrame extends JFrame {
	private CustomerFacade customerFacade;
	private JTable tableCouponData;

	public CustomerMainFrame(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;

		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));

		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(10, 1, 0, 0));

		JButton btnMyCoupons = new JButton("My Coupons");
		btnMyCoupons.addActionListener(e -> {
			try {
				System.out.println(customerFacade.getAllPurchasedCoupons());
			} catch (CustomerFacadeException custE) {
				// TODO Auto-generated catch block
				custE.printStackTrace();
			}
		});
		
		
		westPanel.add(btnMyCoupons);

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
		CenterPanel.add(tableCouponData);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 750, 75);
	}

}
