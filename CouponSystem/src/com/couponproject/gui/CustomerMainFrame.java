package com.couponproject.gui;

import java.awt.*;
import javax.swing.*;


import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.Actionlisteners.AllCouponsActionListener;

public class CustomerMainFrame extends JFrame {
	private JTable tableCouponData;

	public CustomerMainFrame(CustomerFacade customerFacade) {
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
		// CenterPanel.add(tableCouponData);

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

		// ***************
		// ActionListeners
		// ***************
		// All Purchased Coupons
		JButton btnMyCoupons = new JButton("All My Coupons");
		btnMyCoupons.addActionListener(
				new AllCouponsActionListener(tableCouponData, westPanel, customerFacade));
				
		// *******************
		// Adding bnt to panel
		// *******************
		westPanel.add(btnMyCoupons);

	}

}
