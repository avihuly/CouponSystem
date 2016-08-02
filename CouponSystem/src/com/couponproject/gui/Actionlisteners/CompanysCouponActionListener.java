package com.couponproject.gui.Actionlisteners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.Constants;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CompanyCouponDoesNotExistsException;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CouponDoesNotExistException;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.frames.UpDateCouponFrame;

//**********************************
//This class's purpose is to give an action to the All Company's Coupons button
//Upon pressing All Company's Coupons button a table of all compny's coupons will appear in the frame

public class CompanysCouponActionListener implements ActionListener{

	// **********
	// Attributes
	// **********
	JTable tableCouponData;
	JPanel Panel;
	CompanyFacade companyFacade;

	// ***********
	// constructor
	// ***********
	public CompanysCouponActionListener(JTable tableCouponData, JPanel Panel, CompanyFacade companyFacade) {
		this.tableCouponData = tableCouponData;
		this.Panel = Panel;
		this.companyFacade = companyFacade;
	}
		
		
	// ***************
	// actionPerformed 
	// ***************
	public void actionPerformed(ActionEvent a) {
		try {
			// ----------------------------------
			// Step 1 - load all Coupons to table    
			// ----------------------------------
			GuiUtil.CouponsToTable(tableCouponData, companyFacade.getAllCoupons());
			
			// -----------------------------------------------------
			// Step 2 - generate buttons for coupon by relevant types   
			// ------------------------------------------------------
			Panel.removeAll();  	// clear panel
		
			
			for (CouponType couponType: companyFacade.getUniqueCouponTypes()){
				// Each type will generate a button
				JButton tempBnt = new JButton(couponType.name());
				// ActionListener - GuiUtil.CouponsToTable()
				tempBnt.addActionListener(typeE -> {
					try {
						GuiUtil.CouponsToTable(tableCouponData,
								companyFacade.getCouponByType(CouponType.valueOf(tempBnt.getText())));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				Panel.add(tempBnt);
			}
			
			// ---------------------------
			// Step 3 - All coupons button
			// ---------------------------
			JButton allkBnt = new JButton("All Coupons");
			allkBnt.addActionListener(allE -> {
				try {
					GuiUtil.CouponsToTable(tableCouponData, companyFacade.getAllCoupons());
				} catch (Exception e1) {
				}
			});
			Panel.add(allkBnt);
			
			// ----------------------------------
			// Step 4 - Update and Remove buttons
			// ----------------------------------
			JLabel lblBlanck = new JLabel();
			Panel.add(lblBlanck);

			JButton updateBtn = new JButton("Update Coupon");
			updateBtn.addActionListener(updateE -> {

				int selectedRow = tableCouponData.getSelectedRow();
				if (selectedRow > -1) {

					Component c = SwingUtilities.getRoot(updateBtn);
					JFrame frame = (JFrame) c;
					frame.setEnabled(false);

					UpDateCouponFrame updateFrame = new UpDateCouponFrame(companyFacade, tableCouponData);
					updateFrame.setVisible(true);
				}
			});
			Panel.add(updateBtn);
			
			JButton removeBtn = new JButton("Remove Coupon");
			removeBtn.addActionListener(removeE ->{
				TableModel tableModel  = tableCouponData.getModel();
				Coupon coupon;
				try {
					int selectedRow = tableCouponData.getSelectedRow();
					if (selectedRow >-1) {
					
						coupon = companyFacade.getCoupon((long) tableModel.getValueAt(selectedRow, Constants.couponTableIDIndex));
						companyFacade.removeCoupon(coupon);
											 
						((DefaultTableModel)tableCouponData.getModel()).removeRow(selectedRow);
						
						tableCouponData.revalidate();
						tableCouponData.repaint();

					}	
				} catch (CompanyFacadeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CouponDoesNotExistException |CompanyCouponDoesNotExistsException e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			});
			Panel.add(removeBtn);
			
			// --------------------
			// Step 5 - Back button   
			// --------------------
			JButton backBnt = new JButton("Back");
			backBnt.addActionListener(backE -> {
				// clear panel
				Panel.removeAll();
				JButton btnCompCoupons = new JButton("All Company's Coupons");
				btnCompCoupons.addActionListener(
						new CompanysCouponActionListener(tableCouponData, Panel, companyFacade));
				Panel.add(btnCompCoupons);
				Panel.revalidate();
				Panel.repaint();
			});
			Panel.add(backBnt);
			
			
			// -----------------------------
			// Step 6 - revalidate & repaint
			// -----------------------------			
			Panel.revalidate();
			Panel.repaint();

		} catch (CouponSystemException custE) {
			// TODO Auto-generated catch block
			custE.printStackTrace();
		}
		
	}
	
	

}
