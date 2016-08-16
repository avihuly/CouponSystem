package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import com.couponproject.beans.Customer;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.frames.helpers.GuiUtil;

public class UpdateCustomerActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	AdminFacade adminFacade;
	JTable clientsTable;
	JTextArea nameTextFiled;
	JTextArea passwordTextFiled;

	// ***********
	// constructor
	// ***********
	public UpdateCustomerActionListener(AdminFacade adminFacade, JTable clientsTable, JTextArea nameTextFiled,
			JTextArea passwordTextFiled) {
		this.adminFacade = adminFacade;
		this.clientsTable = clientsTable;
		this.nameTextFiled = nameTextFiled;
		this.passwordTextFiled = passwordTextFiled;

	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int selectedRow = clientsTable.getSelectedRow();
			long customerId = (Long) clientsTable.getModel().getValueAt(selectedRow,
					Constants.CustomerTableIDIndex);
			// creating Customer from user input
			Customer customer = new Customer(customerId,nameTextFiled.getText(), passwordTextFiled.getText());

			int Confirmation = JOptionPane.showConfirmDialog(null,
					("Update customer:\n" + customer.getCustName() + ", id:" + customerId), "Are you sure?",
					JOptionPane.YES_NO_OPTION);

			if (Confirmation == JOptionPane.YES_OPTION) {
				adminFacade.updateCustomer(customer);
				GuiUtil.clientsToTable(clientsTable, adminFacade.getAllCustomers());
				JOptionPane.showMessageDialog(null, "Customer \"" + customer.getCustName() + "\" was successful updated");
				GuiUtil.disposeFrameByEvent(e);
			}

		} catch (AdminFacadeException e1) {
			e1.printStackTrace();
		} catch (IllegalPasswordException | CustomerAlreadyExistsException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		clientsTable.getParent().revalidate();
		clientsTable.getParent().repaint();
	}
}
