package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.couponproject.constants.ClientType;
import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.frames.AdminMainFrame;
import com.couponproject.gui.frames.CompanyMainFrame;
import com.couponproject.gui.frames.CustomerMainFrame;
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
	public LoginActionlistener(JFrame loginframe, ButtonGroup userTypeRadioGroup, JTextField txtUserName,
			JPasswordField txtPassword) {
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
		AdminFacade adminFacade = CouponSystem.getInstance().loginAsAdmin(userName, password);
		// check for a successful login
		if (adminFacade != null) {
			// Turing LoginFrame Visibility off
			loginframe.setVisible(false);
			// login message
			JOptionPane.showMessageDialog(null, "Login!!!");
			// loading Admin main Frame
			new AdminMainFrame(adminFacade);
		} else {
			JOptionPane.showMessageDialog(null, "!!!!!NOT LOGED IN!!!");
		}
	}

	//////////////////////////
	// Loading ComapnyFrame //
	//////////////////////////
	private void loadComapnyFrame() {
		CompanyFacade companyFacade = CouponSystem.getInstance().loginAsCompany(userName, password);
		// check for a successful login
		if (companyFacade != null) {
			// Turing LoginFrame Visibility off
			loginframe.setVisible(false);
			// login message
			JOptionPane.showMessageDialog(null, "Login!!!");
			// loading Company main frame
			CompanyMainFrame companyMainFrame = new CompanyMainFrame(companyFacade);
			companyMainFrame.setVisible(true);
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
			new CustomerMainFrame(customerFacade);
		} else {
			JOptionPane.showMessageDialog(null, "!!!!!NOT LOGED IN!!!");
		}
	}
}
