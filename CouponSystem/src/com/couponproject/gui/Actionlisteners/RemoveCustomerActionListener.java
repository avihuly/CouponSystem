package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.couponproject.beans.Customer;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.frames.helpers.GuiUtil;

public class RemoveCustomerActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	AdminFacade adminFacade;
	JTable clientsTable;

	// ***********
	// constructor
	// ***********
	public RemoveCustomerActionListener(AdminFacade adminFacade, JTable clientsTable) {
		this.adminFacade = adminFacade;
		this.clientsTable = clientsTable;
	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			int selectedRow = clientsTable.getSelectionModel().getMinSelectionIndex();
			long customerId = (Long) clientsTable.getModel().getValueAt(selectedRow,
					Constants.CustomerTableIDIndex);
			Customer customer = adminFacade.getCustomer(customerId);

			int Confirmation = JOptionPane.showConfirmDialog(null,("Delete customer:\n" + customer.getCustName() +", id:"+ customerId),
					 "Are you sure?", JOptionPane.YES_NO_OPTION);

			if (Confirmation == JOptionPane.YES_OPTION) {
				adminFacade.removeCustomer(customer);
				GuiUtil.clientsToTable(clientsTable, adminFacade.getAllCustomers());
			}

		} catch (AdminFacadeException | CouponDoesNotExistException e1) {
		} catch (CustomerDoesNotExistException e1) {
			JOptionPane.showMessageDialog(null, "Customer does not exist");
		}
		clientsTable.getParent().revalidate();
		clientsTable.getParent().repaint();
	}
}
