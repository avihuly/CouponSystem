package com.couponproject.gui.frames;

import java.awt.*;
import javax.swing.*;

import com.couponproject.beans.Company;
import com.couponproject.beans.Customer;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class AdminMainFrame extends TemplateFrame {
	public AdminMainFrame(AdminFacade adminFacade) {
		// frame properties
		super("Coupons (logged as admin)",500,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		// **********
		// East Panel
		// **********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);

		// **********
		// West Panel
		// **********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);

		// ************************
		// Center Panel - Bnt panel
		// ************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);

		// ------------------
		// Customer bnt panel
		// ------------------
		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(BorderFactory.createTitledBorder("Customer"));
		customerPanel.setLayout(new GridLayout(5, 1, 5, 5));

		JLabel lblBlank = new JLabel();
		customerPanel.add(lblBlank);

		JButton bntCreateCustomer = new JButton("Create customer");
		customerPanel.add(bntCreateCustomer);

		JButton bntAllCustomers = new JButton("All customers");
		customerPanel.add(bntAllCustomers);

		JButton bntCustomerByID = new JButton("Search customer by ID");
		customerPanel.add(bntCustomerByID);

		// add customerPanel to CenterPanel
		CenterPanel.add(customerPanel);

		// -----------------
		// Company bnt panel
		// ------------------
		JPanel companyPanel = new JPanel();
		companyPanel.setBorder(BorderFactory.createTitledBorder("Company"));
		companyPanel.setLayout(new GridLayout(5, 1, 5, 5));

		JLabel lblBlank2 = new JLabel();
		companyPanel.add(lblBlank2);

		JButton bntCreateCompany = new JButton("Create company");
		companyPanel.add(bntCreateCompany);

		JButton bntAllCompanies = new JButton("All companies");
		companyPanel.add(bntAllCompanies);

		JButton bntCompanyByID = new JButton("Search company by ID");
		companyPanel.add(bntCompanyByID);

		// add companyPanel to CenterPanel
		CenterPanel.add(companyPanel);
		pack();

		// ***************
		// ActionListeners
		// ***************
		// All Customer
		bntAllCustomers.addActionListener(e -> {
			AllCustomersFrame customerFrame = new AllCustomersFrame(adminFacade);
			customerFrame.setVisible(true);
		});

		// Create customer
		bntCreateCustomer.addActionListener(e -> {
			NewCustomerForme customerInfoFrame = new NewCustomerForme(adminFacade);
			customerInfoFrame.setVisible(true);
		});

		// Search customer by ID
		bntCustomerByID.addActionListener(e -> {
			String input = JOptionPane.showInputDialog("Enter customer ID:");
			if (input != null) {
				try {
					long targetID = Long.parseLong(input);
					Customer customer = adminFacade.getCustomer(targetID);
					InfoCustomerFrame customerInfoFrame = new InfoCustomerFrame(customer);
					customerInfoFrame.setVisible(true);
				} catch (AdminFacadeException adminE) {
					JOptionPane.showMessageDialog(null, "ID does not exists in database");
					bntCustomerByID.doClick();
				} catch (NumberFormatException notNumE) {
					JOptionPane.showMessageDialog(null, "ID must be a number");
					bntCustomerByID.doClick();
				}
			}
		});

		// All Companies
		bntAllCompanies.addActionListener(e -> {
			AllCompaniesFrame companiesFrame = new AllCompaniesFrame(adminFacade);
			companiesFrame.setVisible(true);
		});

		// Create Company
		bntCreateCompany.addActionListener(e -> {
			NewCompanyForme companyInfoFrame = new NewCompanyForme(adminFacade);
			companyInfoFrame.setVisible(true);
		});

		// Search Company by ID
		bntCompanyByID.addActionListener(e -> {
			String input = JOptionPane.showInputDialog("Enter company ID:");
			if (input != null) {
				try {
					long targetID = Long.parseLong(input);
					Company company = adminFacade.getCompany(targetID);
					InfoCompanyFrame companyInfoFrame = new InfoCompanyFrame(company);
					companyInfoFrame.setVisible(true);
				} catch (AdminFacadeException adminE) {
					JOptionPane.showMessageDialog(null, "ID does not exists in database");
					bntCompanyByID.doClick();
				} catch (NumberFormatException notNumE) {
					JOptionPane.showMessageDialog(null, "ID must be a number");
					bntCompanyByID.doClick();
				}
			}
		});

	}
}
