package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import com.couponproject.beans.Company;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.Actionlisteners.UpdateCompanyActionListener;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class UpDateCompanyFrame extends TemplateFrame {
	// **********
	// Attributes
	// **********
	private JTextArea txtName;
	private JTextArea txtEmail;
	private JTextArea txtPassword;

	// ***********
	// constructor
	// ***********
	public UpDateCompanyFrame(AdminFacade adminFacade, JTable companiesTable) {
		// frame properties
		super("Admin - Company Update", 300, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		// selected company
		Company company;
		try {
			company = adminFacade.getCompany(
					(long) companiesTable.getValueAt(companiesTable.getSelectedRow(), Constants.CompanyTableIDIndex));

			// **************************
			// Center Panel - Table panel
			// **************************
			JPanel CenterPanel = new JPanel();
			getContentPane().add(CenterPanel, BorderLayout.CENTER);
			CenterPanel.setLayout(new GridLayout(5, 1, 5, 5));

			JLabel lblCustomerIdVlaue = new JLabel(Long.toString(company.getId()));
			CenterPanel.add(lblCustomerIdVlaue);

			txtName = new JTextArea(company.getCompName());
			CenterPanel.add(txtName);

			txtEmail = new JTextArea(company.getEmail());
			CenterPanel.add(txtEmail);

			txtPassword = new JTextArea(company.getPassword());
			CenterPanel.add(txtPassword);

			// **********************
			// West Panel - bnt panel
			// **********************
			JPanel westPanel = new JPanel();
			getContentPane().add(westPanel, BorderLayout.WEST);
			westPanel.setLayout(new GridLayout(5, 1, 5, 5));

			JLabel lblCompanyID = new JLabel(" Company ID: ");
			westPanel.add(lblCompanyID);

			JLabel lblCompanyName = new JLabel(" Name: ");
			westPanel.add(lblCompanyName);

			JLabel lblCompanyEmail = new JLabel(" Email: ");
			westPanel.add(lblCompanyEmail);

			JLabel lblCompanyPassword = new JLabel(" Password: ");
			westPanel.add(lblCompanyPassword);

			// ***********
			// South Panel
			// ***********
			JPanel southPanel = new JPanel();
			getContentPane().add(southPanel, BorderLayout.SOUTH);

			JButton bntUpdate = new JButton("Update");
			// Update ActionListener
			bntUpdate.addActionListener(
					new UpdateCompanyActionListener(adminFacade, companiesTable, txtName, txtPassword, txtEmail));
			add(bntUpdate, BorderLayout.SOUTH);

		} catch (AdminFacadeException e) {}
	}
}
