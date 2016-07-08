package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.ClientType;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.system.CouponSystem;

public class LoginActionlistener implements ActionListener {

	// **********
	// Attributes
	// **********
	ButtonGroup userTypeRadioGroup;
	JTextField txtUserName;
	JPasswordField txtPassword;

	ClientType clientType;
	String userName;
	String password;

	// ***********
	// constructor
	// ***********
	public LoginActionlistener(ButtonGroup userTypeRadioGroup, JTextField txtUserName, JPasswordField txtPassword) {
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

	//
	// Loading AdminFarme
	//
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

	//
	// Loading CustomerFrame
	//
	private void loadCustomerFrame() {
		CustomerFacade customerFacade = CouponSystem.getInstance().loginAsCustomer(userName, password);
		if (customerFacade != null) {
			JOptionPane.showMessageDialog(null, "Login!!!");
		} else {
			JOptionPane.showMessageDialog(null, "!!!!!NOT LOGED IN!!!");
		}
	}
}
