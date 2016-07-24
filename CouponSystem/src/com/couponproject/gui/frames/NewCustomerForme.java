package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.CreateCustomerActionListener;

public class NewCustomerForme extends JFrame {
	// **********
	// Attributes
	// **********
	private JTextArea txtName = new JTextArea();
	private JTextArea txtPassword = new JTextArea();

	// ***********
	// constructor
	// ***********
	public NewCustomerForme(AdminFacade adminFacade) {
		// frame properties
		super("Admin - New Customer");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 300, 40);

		// **********
		// East Panel
		// **********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);

		// **********************
		// West Panel - bnt panel
		// **********************
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(5, 1, 5, 5));
		
		JLabel lblCustomerName = new JLabel("Customer Name: ");
		westPanel.add(lblCustomerName);

		JLabel lblCustomerPassword = new JLabel("Customer Password: ");
		westPanel.add(lblCustomerPassword);

		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		JButton bntUpdate = new JButton("Create Customer");
		// Create customer ActionListener
		bntUpdate.addActionListener(new CreateCustomerActionListener(adminFacade, txtName, txtPassword));
		add(bntUpdate, BorderLayout.SOUTH);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(5, 1, 5, 5));

		CenterPanel.add(txtName);
		CenterPanel.add(txtPassword);

	}

}
