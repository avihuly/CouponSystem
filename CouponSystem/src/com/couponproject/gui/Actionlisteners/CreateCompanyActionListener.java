package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.couponproject.beans.Company;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;

public class CreateCompanyActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	AdminFacade adminFacade;
	JTextArea txtName;
	JTextArea txtPassword;
	JTextArea txtEmail;

	// ***********
	// constructor
	// ***********
	public CreateCompanyActionListener(AdminFacade adminFacade, JTextArea txtName, JTextArea txtPassword, JTextArea txtEmail) {
		this.adminFacade = adminFacade;
		this.txtName = txtName;
		this.txtPassword = txtPassword;
		this.txtEmail = txtEmail;
	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		Company company = new Company(txtName.getText(), txtPassword.getText(), txtEmail.getText());
		try {
			adminFacade.createCompany(company);
			JOptionPane.showMessageDialog(null, "Company \"" + company.getCompName() + "\" was successful added");
			GuiUtil.disposeFrameByEvent(e);
		} catch (AdminFacadeException adminE) {
			JOptionPane.showMessageDialog(null, Constants.UnExpectedErrorMassage);
		} catch (IllegalPasswordException | CompanyAlreadyExistsException OtherE) {
			JOptionPane.showMessageDialog(null, OtherE.getMessage());
		} catch (EmailAlreadyExistsException EmailE) {
			JOptionPane.showMessageDialog(null, EmailE.getMessage());
		}
	}

}