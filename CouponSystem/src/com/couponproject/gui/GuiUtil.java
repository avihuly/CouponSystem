package com.couponproject.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.Constants;
import com.couponproject.constants.CouponTableColumnNames;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.exception.CustomerFacadeException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.Actionlisteners.AboutActionListener;
import com.couponproject.gui.Actionlisteners.AllCouponsActionListener;
import com.couponproject.gui.Actionlisteners.PurchasedCouponsActionListener;
import com.mysql.fabric.xmlrpc.base.Array;

public class GuiUtil {	
	// ********
	// Set logo
	// ********
	protected static void setLogoBySize(JFrame frame, int width, int height) {
		JPanel northPanel = new JPanel();
		Image logoImg;

		try {
			// getting image refrains
			File input = new File("image/couponLogo.jpg");
			logoImg = ImageIO.read(input);

			// resizing image to fit panel
			Image logoImgResized = logoImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			// converting image to icon
			ImageIcon logoIcon = new ImageIcon(logoImgResized);

			// loading icon to Jlabel
			JLabel logoLabel = new JLabel("", logoIcon, JLabel.CENTER);

			// loading Jlabel to panel
			northPanel.add(logoLabel);

			// adding to frame's BorderLayout.NORTH
			frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		} catch (IOException ie) {
		}

	}
	
	// *************
	// Icon And Menu
	// *************
	protected static void setFrameIconAndMenu(JFrame frame) {
		// --------
		// Icon set
		// --------
		try {
			// getting image refrains
			File input = new File("image/frameIcon.png");
			Image frameImg = ImageIO.read(input);
			// loading icon to frame
			frame.setIconImage(frameImg);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}

		// -----------
		// MenuBar set
		// -----------
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		// About Icon - "image/about.png"
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new AboutActionListener());

		try {
			// getting image refrains
			File input = new File("image/about.png");
			Image aboutImg = ImageIO.read(input);

			// resizing image to fit panel
			Image aboutImgResized = aboutImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

			// converting image to icon
			ImageIcon aboutIcon = new ImageIcon(aboutImgResized);

			// loading icon to menu item
			mntmAbout.setIcon(aboutIcon);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}

		mnHelp.add(mntmAbout);

	}
	
	// ****************
	// Coupon Table Set
	// ****************
	public static void CouponsToTable(JTable tableCouponData, Collection<Coupon> PurchasedCoupons) {
		// Disable editing 
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		model.addColumn("Title");
		model.addColumn("Description");
		model.addColumn("Type");
		model.addColumn("Start Date");
		model.addColumn("End Date");
		model.addColumn("Price");
		// Hidden columns 
		model.addColumn("ID");
		model.addColumn("Amount");
		model.addColumn("Image");

		for (Coupon coupon : PurchasedCoupons) {
			ArrayList<Object> tempCoupon = new ArrayList<>();
			tempCoupon.add(coupon.getTitle());
			tempCoupon.add(coupon.getMessage());
			tempCoupon.add(coupon.getType());
			tempCoupon.add(coupon.getStartDate());
			tempCoupon.add(coupon.getEndDate());
			tempCoupon.add(coupon.getPrice());
			// Hidden details  
			tempCoupon.add(coupon.getId());
			tempCoupon.add(coupon.getAmount());
			tempCoupon.add(coupon.getImage());
			// Adding row to table
			model.addRow(tempCoupon.toArray());
		}
		// Adding model to table		
		tableCouponData.setModel(model);
		
		
		// ----------------
		// 
		// ----------------
		
		// Heeding columns ID Amount & Image
		tableCouponData.removeColumn(tableCouponData.getColumn("ID"));
		tableCouponData.removeColumn(tableCouponData.getColumn("Amount"));
		tableCouponData.removeColumn(tableCouponData.getColumn("Image"));
			
		// Center alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		for (int i = 0; i < (tableCouponData.getColumnCount()); i++) {
			tableCouponData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		// First row selected as default
		if (tableCouponData.getRowCount() > 0) {
			tableCouponData.setRowSelectionInterval(0, 0);
		}
		
		// RowSorter
		tableCouponData.setAutoCreateRowSorter(true);
		
		// Table widths
		int tW = tableCouponData.getWidth();
		
		// Column width percentage
		float[] columnWidthPercentage = { 0.15f, 0.45f, 0.14f, 0.1f, 0.1f, 0.06f };

		for (int i = 0; i < tableCouponData.getColumnCount(); i++) {
			// Calculating and assigning each column width 
			tableCouponData.getColumnModel().getColumn(i).setPreferredWidth(
					(int)(columnWidthPercentage[i]*tW));
		}
		
		tableCouponData.setRowHeight(50);
		// Message Column Renderer
		tableCouponData.getColumnModel().getColumn(Constants.couponTableMESSAGEIndex).setCellRenderer(new MessageCellRenderer());
	}

	// ************************
	// setCustomerHomeBntLayout
	// ************************
	public static void setCustomerHomeBntLayout(JTable tableCouponData, JPanel Panel, CustomerFacade customerFacade){		
		// Buttons ActionListeners
		Panel.removeAll();  	// clear panel
		
		// All Purchased Coupons
		JButton btnMyCoupons = new JButton("All My Coupons");
		btnMyCoupons.addActionListener(
				new PurchasedCouponsActionListener(tableCouponData, Panel, customerFacade));
		
		JButton bntBrowseCoupons  = new JButton("Browse Coupons");
		bntBrowseCoupons.addActionListener(new AllCouponsActionListener(tableCouponData, Panel, customerFacade));
			
		// Adding buttons to westPanel
		Panel.add(btnMyCoupons);
		Panel.add(bntBrowseCoupons);
	
		// revalidate & repaint
		Panel.revalidate();
		Panel.repaint();	
	}
}
