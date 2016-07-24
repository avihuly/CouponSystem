package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.couponproject.beans.Customer;
import com.couponproject.dbdao.CustomerDBDAO;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.facade.AdminFacade;

public class CreateCustomerActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	AdminFacade adminFacade;
	JTextArea txtName;
	JTextArea txtPassword;

	// ***********
	// constructor
	// ***********
	public CreateCustomerActionListener(AdminFacade adminFacade, JTextArea txtName, JTextArea txtPassword) {
		this.adminFacade = adminFacade;
		this.txtName = txtName;
		this.txtPassword = txtPassword;
	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Customer customer = new Customer(txtName.getText(), txtPassword.getText());
		try {
			adminFacade.createCustomer(customer);
		} catch (AdminFacadeException e) {
			// TODO Auto-generated catch block
		} catch (IllegalPasswordException | CustomerAlreadyExistsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
