package com.couponproject.gui.frames;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.CouponByPriceSliders;
import com.couponproject.gui.Actionlisteners.CouponTableListSelectionListener;
import com.couponproject.gui.Actionlisteners.UpdateCustomerActionListener;
import com.couponproject.gui.Actionlisteners.allCustomersActionListeners;

public class AdminMainFrame extends JFrame {
	public AdminMainFrame(AdminFacade adminFacade) {
		// frame properties
		super("Coupons (logged as admin)");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 400, 40);

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

		JButton bntCreateCompany = new JButton("Create company");
		companyPanel.add(bntCreateCompany);

		JButton bntRemoveCompany = new JButton("Remove company");
		companyPanel.add(bntRemoveCompany);

		JButton bntUpdateCompany = new JButton("Update company");
		companyPanel.add(bntUpdateCompany);

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
			CustomersFrame customerFrame = new CustomersFrame(adminFacade);
			customerFrame.setVisible(true);
		});
		
		// Create customer
		bntCreateCustomer.addActionListener(e->{
			NewCustomerForme customerInfoFrame = new NewCustomerForme(adminFacade);
			customerInfoFrame.setVisible(true);
		});
		
		// Search customer by ID
		bntCustomerByID.addActionListener(e->{
			String input = JOptionPane.showInputDialog("Enter customer ID:");
			
			try {
				long targetID = Long.parseLong(input); 
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "ID must be a number");
			}
			
		});

	}
}
