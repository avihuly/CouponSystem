package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.couponproject.beans.Company;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CompanyDoesNotExistException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.frames.helpers.GuiUtil;

public class RemoveCompanyActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	AdminFacade adminFacade;
	JTable companyTable;

	// ***********
	// constructor
	// ***********
	public RemoveCompanyActionListener(AdminFacade adminFacade, JTable companyTable) {
		this.adminFacade = adminFacade;
		this.companyTable = companyTable;
	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int selectedRow = companyTable.getSelectionModel().getMinSelectionIndex();
			long companyId = (Long) companyTable.getModel().getValueAt(selectedRow,
					Constants.CompanyTableIDIndex);
			Company company = adminFacade.getCompany(companyId);

			int Confirmation = JOptionPane.showConfirmDialog(null,
					("Delete company:\n" + company.getCompName() + ", id:" + companyId), "Are you sure?",
					JOptionPane.YES_NO_OPTION);

			if (Confirmation == JOptionPane.YES_OPTION) {
				adminFacade.removeCompany(company);
				GuiUtil.CompaniesToTable(companyTable, adminFacade.getAllCompanies());
			}

		} catch (AdminFacadeException | CouponDoesNotExistException | CompanyCouponDoesNotExistsException e1) {
			e1.printStackTrace();
		} catch (CompanyDoesNotExistException e1) {
			JOptionPane.showMessageDialog(null, "Company does not exist");
		}

		companyTable.getParent().revalidate();
		companyTable.getParent().repaint();
	}
}
