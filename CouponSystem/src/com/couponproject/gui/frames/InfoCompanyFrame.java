package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import com.couponproject.beans.Company;
import com.couponproject.gui.frames.helpers.TemplateFrame;


public class InfoCompanyFrame extends TemplateFrame {
	// ***********
	// constructor
	// ***********
	public InfoCompanyFrame(Company company) {
		// frame properties
		super("Admin - Company Info",300,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(4, 1, 5, 5));

		JLabel lblBlanck = new JLabel(" Company Info:");
		CenterPanel.add(lblBlanck);

		JLabel lblID = new JLabel(" ID: " + company.getId());
		CenterPanel.add(lblID);

		JLabel lblName = new JLabel(" Name: " + company.getCompName());
		CenterPanel.add(lblName);
		
		JLabel lblEmail = new JLabel(" Email: " + company.getEmail());
		CenterPanel.add(lblEmail);

		JButton bntBack = new JButton("Back");
		// bntBack ActionListener
		bntBack.addActionListener(e -> {
			this.dispose();
		});
		add(bntBack, BorderLayout.SOUTH);

	}
}
