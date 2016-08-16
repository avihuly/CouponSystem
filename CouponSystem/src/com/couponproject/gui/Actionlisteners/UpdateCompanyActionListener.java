package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.couponproject.beans.Company;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CompanyAlreadyExistsException;
import com.couponproject.exception.EmailAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.frames.helpers.GuiUtil;

public class UpdateCompanyActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	AdminFacade adminFacade;
	JTable companiesTable;
	JTextArea nameTextFiled;
	JTextArea EmailTextFiled;
	JTextArea passwordTextFiled;

	// ***********
	// constructor
	// ***********
	public UpdateCompanyActionListener(AdminFacade adminFacade, JTable companiesTable, JTextArea nameTextFiled,
			JTextArea passwordTextFiled, JTextArea EmailTextFiled) {
		this.adminFacade = adminFacade;
		this.companiesTable = companiesTable;
		this.nameTextFiled = nameTextFiled;
		this.EmailTextFiled = EmailTextFiled;
		this.passwordTextFiled = passwordTextFiled;
	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int selectedRow = companiesTable.getSelectedRow();
			long companyId = (Long) companiesTable.getModel().getValueAt(selectedRow, Constants.CompanyTableIDIndex);
			// creating Company from user input
			Company company = new Company(companyId, nameTextFiled.getText(), passwordTextFiled.getText(),
					EmailTextFiled.getText());

			int Confirmation = JOptionPane.showConfirmDialog(null,
					("Update company:\n" + company.getCompName() + ", id:" + companyId), "Are you sure?",
					JOptionPane.YES_NO_OPTION);

			if (Confirmation == JOptionPane.YES_OPTION) {
				adminFacade.updateCompany(company);
				GuiUtil.CompaniesToTable(companiesTable, adminFacade.getAllCompanies());
				JOptionPane.showMessageDialog(null, "Company \"" + company.getCompName() + "\" was successful updated");
				GuiUtil.disposeFrameByEvent(e);
			}

		} catch (AdminFacadeException adminE) {
			adminE.printStackTrace();
		} catch (IllegalPasswordException | CompanyAlreadyExistsException | EmailAlreadyExistsException otherE) {
			JOptionPane.showMessageDialog(null, otherE.getMessage());
		}

		companiesTable.getParent().revalidate();
		companiesTable.getParent().repaint();
	}

}
