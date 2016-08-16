package com.couponproject.gui.Actionlisteners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.couponproject.constants.Constants;
import com.couponproject.exception.CouponAlreadyPurchasedException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.OutOfDateException;
import com.couponproject.exception.OutOfStockException;
import com.couponproject.facade.CustomerFacade;

public class PurchaseCouponMouseListener extends MouseAdapter {

	// **********
	// Attributes
	// **********
	CustomerFacade customerFacade;

	// ***********
	// constructor
	// ***********
	public PurchaseCouponMouseListener(CustomerFacade customerFacade) {
		this.customerFacade = customerFacade;
	}

	// ************
	// mouseClicked
	// ************
	@Override
	public void mouseClicked(MouseEvent e) {
		// Make sure double click
		if (e.getClickCount() == 2) {
			// Get coupon table from event
			JTable tableCouponData = (JTable) e.getSource();
			// Get coupon Selected Row
			int selectedRow = tableCouponData.getSelectedRow();
			// Get coupon Selected ID
			long CouponID = (long) tableCouponData.getModel().getValueAt(selectedRow, Constants.couponTableIDIndex);
			// Confirmation dialog
			int Confirmation = JOptionPane.showConfirmDialog(null, "Are you sure?", "Buy coupon",
					JOptionPane.YES_NO_OPTION);
			if (Confirmation == JOptionPane.YES_OPTION) {
				// Perches coupon
				try {
					customerFacade.purchaseCoupon(CouponID);
					JOptionPane.showMessageDialog(null, "Coupon has been successfully purchased  :)");
				} catch (CouponAlreadyPurchasedException | OutOfDateException | OutOfStockException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (CouponSystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
