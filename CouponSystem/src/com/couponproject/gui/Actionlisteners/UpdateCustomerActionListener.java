package com.couponproject.gui.Actionlisteners;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import com.couponproject.beans.Customer;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CustomerAlreadyExistsException;
import com.couponproject.exception.CustomerDoesNotExistException;
import com.couponproject.exception.IllegalPasswordException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;

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
			int selectedcustomerRow = clientsTable.getSelectedRow();
			long customerId = (Long) clientsTable.getModel().getValueAt(selectedcustomerRow,
					Constants.CustomerTableIDIndex);
			// creating Customer from user input
			Customer customer = new Customer(customerId,nameTextFiled.getText(), passwordTextFiled.getText());

			int Confirmation = JOptionPane.showConfirmDialog(null,
					("Update customer:\n" + customer.getCustName() + ", id:" + customerId), "Are you sure?",
					JOptionPane.YES_NO_OPTION);

			if (Confirmation == JOptionPane.YES_OPTION) {
				adminFacade.updateCustomer(customer);
				GuiUtil.clientsToTable(clientsTable, adminFacade.getAllCustomers());

				Container sourceFrame = ((JButton) e.getSource()).getParent();
				while (!(sourceFrame instanceof JFrame)) {
					sourceFrame = sourceFrame.getParent();
				}
				((JFrame) sourceFrame).dispose();
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