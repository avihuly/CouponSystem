package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.GuiUtil;

public class AllCouponsActionListener implements ActionListener {
	// **********
	// Attributes
	// **********
	JTable tableCouponData;
	JPanel Panel;
	CustomerFacade customerFacade;

	// ***********
	// constructor
	// ***********
	public AllCouponsActionListener(JTable tableCouponData, JPanel Panel, CustomerFacade customerFacade) {
		this.tableCouponData = tableCouponData;
		this.Panel = Panel;
		this.customerFacade = customerFacade;
	}

	// ***************
	// actionPerformed
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		// ----------------------------------
		// Step 1 - load all Coupons to table
		// ----------------------------------
		try {
			GuiUtil.CouponsToTable(tableCouponData, CouponDBDAO.getInstace().getAllCoupons());
		} catch (CouponSystemException e1) {
			e1.printStackTrace();
		}
		
		// ---------------------------------------------------
		// Step 2 - add purchase coupon MouseListener to table
		// ---------------------------------------------------
		tableCouponData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					JOptionPane.showMessageDialog(null, row);
				}
			}
		});

		// -----------------------------------------------------
		// Step 3 - generate buttons for coupon by relevant types
		// ------------------------------------------------------
		Panel.removeAll(); // clear panel
		for (CouponType couponType : CouponType.values()) {
			// Each type will generate a button
			JButton tempBnt = new JButton(couponType.name());
			// ActionListener - GuiUtil.CouponsToTable()
			tempBnt.addActionListener(typeE -> {
				try {
					GuiUtil.CouponsToTable(tableCouponData,
							CouponDBDAO.getInstace().getCouponsByType(CouponType.valueOf(tempBnt.getText())));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			Panel.add(tempBnt);
		}

		// ---------------------------
		// Step 4 - All coupons button
		// ---------------------------
		JButton allkBnt = new JButton("All Coupons");
		allkBnt.addActionListener(allE -> {
			try {
				GuiUtil.CouponsToTable(tableCouponData, CouponDBDAO.getInstace().getAllCoupons());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Panel.add(allkBnt);
		
		// --------------------
		// Step 5 - Back button   
		// --------------------
		JButton backBnt = new JButton("Back");
		backBnt.addActionListener(backE -> {
			// Set Customer Home Button Layout
			GuiUtil.setCustomerHomeBntLayout(tableCouponData, Panel, customerFacade);
			
			// Remove all MouseListeners
			MouseListener[] mouseListeners = tableCouponData.getMouseListeners();
			for (MouseListener mouseListener: mouseListeners){

				////////////////
				////////////////
				
				//System.out.println(mouseListener.getClass() instanceof MouseAdapter);
				// tableCouponData.removeMouseListener(mouseListener);
				
				
				/////////////////
				/////////////////
			}
		});
		Panel.add(backBnt);
		
		// -----------------------------
		// Step 6 - revalidate & repaint
		// -----------------------------			
		Panel.revalidate();
		Panel.repaint();
		
	}

}
