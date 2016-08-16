package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.Actionlisteners.CreateCustomerActionListener;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class NewCustomerForme extends TemplateFrame {
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
		super("Admin - New Customer",300,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

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
