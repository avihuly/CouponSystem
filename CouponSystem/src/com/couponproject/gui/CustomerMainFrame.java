package com.couponproject.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.Actionlisteners.CouponTableListSelectionListener;

public class CustomerMainFrame extends JFrame {
	private JTable tableCouponData = new JTable();

	public CustomerMainFrame(CustomerFacade customerFacade) {
		// frame properties
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
		sp.setBorder(BorderFactory.createEmptyBorder());
		tableCouponData.setShowGrid(false);
		tableCouponData.setShowHorizontalLines(true);
		
		CenterPanel.add(sp, BorderLayout.CENTER);
		
		// make it all fit
		CenterPanel.setPreferredSize(new Dimension(750, 500));
		pack();
		
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
		GuiUtil.setCustomerHomeBntLayout(tableCouponData, westPanel, customerFacade);

		// Coupon Table ActionListeners
		// ----------------------------
		ListSelectionModel selectedModel = tableCouponData.getSelectionModel();
		selectedModel.addListSelectionListener(new CouponTableListSelectionListener(tableCouponData, lblCouponPic));
	}
}
