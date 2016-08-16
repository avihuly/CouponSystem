package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import com.couponproject.beans.Customer;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.Actionlisteners.UpdateCustomerActionListener;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class UpDateCustomerFrame extends TemplateFrame {
	// **********
	// Attributes
	// **********
	private JTextArea txtName;
	private JTextArea txtPassword;

	// ***********
	// constructor
	// ***********
	public UpDateCustomerFrame(AdminFacade adminFacade, JTable clientsTable) {
		// frame properties
		super("Admin - Custimer Update", 300, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// selected customer
		Customer customer;
		try {
			customer = adminFacade.getCustomer((long) clientsTable.getValueAt(clientsTable.getSelectedRow(), Constants.CustomerTableIDIndex));

			// **************************
			// Center Panel - Table panel
			// **************************
			JPanel CenterPanel = new JPanel();
			getContentPane().add(CenterPanel, BorderLayout.CENTER);
			CenterPanel.setLayout(new GridLayout(5, 1, 5, 5));

			JLabel lblCustomerIdVlaue = new JLabel(Long.toString(customer.getId()));
			CenterPanel.add(lblCustomerIdVlaue);
			
			txtName = new JTextArea(customer.getCustName());
			CenterPanel.add(txtName);

			txtPassword = new JTextArea(customer.getPassword());
			CenterPanel.add(txtPassword);
			
			// **********************
			// West Panel - bnt panel
			// **********************
			JPanel westPanel = new JPanel();
			getContentPane().add(westPanel, BorderLayout.WEST);
			westPanel.setLayout(new GridLayout(5, 1, 5, 5));

			JLabel lblCustomerID = new JLabel(" Customer ID: ");
			westPanel.add(lblCustomerID);

			JLabel lblCustomerName = new JLabel(" Name: ");
			westPanel.add(lblCustomerName);

			JLabel lblCustomerPassword = new JLabel(" Password: ");
			westPanel.add(lblCustomerPassword);

			// ***********
			// South Panel
			// ***********
			JPanel southPanel = new JPanel();
			getContentPane().add(southPanel, BorderLayout.SOUTH);
			
			JButton bntUpdate = new JButton("Update");
			// Update ActionListener
			bntUpdate.addActionListener(
					new UpdateCustomerActionListener(adminFacade, clientsTable, txtName, txtPassword));
			add(bntUpdate, BorderLayout.SOUTH);



		} catch (AdminFacadeException e) {
			// TODO
		}
	}
}
