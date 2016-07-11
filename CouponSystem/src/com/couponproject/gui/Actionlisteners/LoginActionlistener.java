package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.couponproject.constants.ClientType;
import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.CustomerMainFrame;
import com.couponproject.system.CouponSystem;

public class LoginActionlistener implements ActionListener {

	// **********
	// Attributes
	// **********
	JFrame loginframe;
	ButtonGroup userTypeRadioGroup;
	JTextField txtUserName;
	JPasswordField txtPassword;

	ClientType clientType;
	String userName;
	String password;

	// ***********
	// constructor
	// ***********
	public LoginActionlistener(JFrame loginframe, ButtonGroup userTypeRadioGroup, JTextField txtUserName, JPasswordField txtPassword) {
		this.loginframe = loginframe;
		this.userTypeRadioGroup = userTypeRadioGroup;
		this.txtUserName = txtUserName;
		this.txtPassword = txtPassword;
	}

	// ****************
	// Action Performed
	// ****************
	@Override
	public void actionPerformed(ActionEvent e) {
		clientType = ClientType.valueOf(userTypeRadioGroup.getSelection().getActionCommand());
		userName = txtUserName.getText();
		password = new String(txtPassword.getPassword());

		switch (clientType) {
		case Admin:
			loadAdminFarme();
			break;
		case Company:
			loadComapnyFrame();
			break;
		case Customer:
			loadCustomerFrame();
			break;
		}
	}

	/////////////////////////	
	// Loading AdminFarme ///
	/////////////////////////
	private void loadAdminFarme() {
		AdminFacade adminFacade = CouponSystem.getInstance().loginAsAdmin(userName, userName);
		
		if (adminFacade != null) {
			JOptionPane.showMessageDialog(null, "Login!!!");
		} else {
			JOptionPane.showMessageDialog(null, "!!!!!NOT LOGED IN!!!");
		}
	}

	//
	// Loading ComapnyFrame
	//
	private void loadComapnyFrame() {
		CompanyFacade companyFacade = CouponSystem.getInstance().loginAsCompany(userName, password);
		if (companyFacade != null) {
			JOptionPane.showMessageDialog(null, "Login!!!");
		} else {
			JOptionPane.showMessageDialog(null, "!!!!!NOT LOGED IN!!!");
		}
	}

	////////////////////////////
	// Loading CustomerFrame////
	////////////////////////////
	private void loadCustomerFrame() {
		CustomerFacade customerFacade = CouponSystem.getInstance().loginAsCustomer(userName, password);
		
		// check for a successful login 
		if (customerFacade != null) {
			// Turing LoginFrame Visibility off
			loginframe.setVisible(false);
			
			// login message 
			JOptionPane.showMessageDialog(null, "Login!!!");
			
			// loading Customer main Frame 
			CustomerMainFrame customerMainFrame = new CustomerMainFrame(customerFacade);
			customerMainFrame.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "!!!!!NOT LOGED IN!!!");
		}
	}
}