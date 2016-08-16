package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import com.couponproject.beans.Customer;
import com.couponproject.gui.frames.helpers.TemplateFrame;


public class InfoCustomerFrame extends TemplateFrame {
	// ***********
	// constructor
	// ***********
	public InfoCustomerFrame(Customer customer) {
		// frame properties
		super("Admin - Customer Info",300,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(3, 1, 5, 5));

		JLabel lblBlanck = new JLabel(" Customer Info:");
		CenterPanel.add(lblBlanck);

		JLabel lblID = new JLabel(" ID: " + customer.getId());
		CenterPanel.add(lblID);

		JLabel lblName = new JLabel(" Name: " + customer.getCustName());
		CenterPanel.add(lblName);

		JButton bntBack = new JButton("Back");
		// bntBack ActionListener
		bntBack.addActionListener(e->{
			this.dispose();
		});
		add(bntBack, BorderLayout.SOUTH);

	}
}
