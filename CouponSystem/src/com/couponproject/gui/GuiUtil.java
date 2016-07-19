package com.couponproject.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponTableColumnNames;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CustomerFacadeException;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.Actionlisteners.AboutActionListener;
import com.couponproject.gui.Actionlisteners.AllCouponsActionListener;
import com.couponproject.gui.Actionlisteners.PurchasedCouponsActionListener;
import com.mysql.fabric.xmlrpc.base.Array;

public class GuiUtil {
	// ********
	// Set Logo
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
			System.out.println(ie.getMessage());
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
		DefaultTableModel model = new DefaultTableModel();
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
			ArrayList<String> tempCoupon = new ArrayList<>();
			tempCoupon.add(coupon.getTitle());
			tempCoupon.add(coupon.getMessage());
			tempCoupon.add(coupon.getType().name());
			tempCoupon.add(coupon.getStartDate().toString());
			tempCoupon.add(coupon.getEndDate().toString());
			tempCoupon.add(Double.toString(coupon.getPrice()));
			// Hidden details  
			tempCoupon.add(Long.toString(coupon.getId()));
			tempCoupon.add(Integer.toString(coupon.getAmount()));
			tempCoupon.add(coupon.getImage());
			// Adding row to table
			model.addRow(tempCoupon.toArray());
		}
		// Adding model to table		
		tableCouponData.setModel(model);
		
		// Heightening columns ID Amount & Image
		tableCouponData.removeColumn(tableCouponData.getColumnModel().getColumn(6));
		tableCouponData.removeColumn(tableCouponData.getColumnModel().getColumn(6));
		tableCouponData.removeColumn(tableCouponData.getColumnModel().getColumn(6));
		
		// Center alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < (tableCouponData.getColumnCount()); i++) {
			tableCouponData.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		// First row selected as default
		tableCouponData.setRowSelectionInterval(0, 0);
	}

	// ************************
	// setCustomerHomeBntLayout
	// ************************
	public static void setCustomerHomeBntLayout(JTable tableCouponData, JPanel Panel, CustomerFacade customerFacade){		
		// ***********************
		// Buttons ActionListeners
		// ***********************
		Panel.removeAll();  	// clear panel
		
		// All Purchased Coupons
		JButton btnMyCoupons = new JButton("All My Coupons");
		btnMyCoupons.addActionListener(
				new PurchasedCouponsActionListener(tableCouponData, Panel, customerFacade));
		
		JButton bntBrowseCoupons  = new JButton("Browse Coupons");
		bntBrowseCoupons.addActionListener(new AllCouponsActionListener(tableCouponData, Panel, customerFacade));
		
		
		// ***************************	
		// Adding buttons to westPanel
		// ***************************
		Panel.add(btnMyCoupons);
		Panel.add(bntBrowseCoupons);
		
		// --------------------
		// revalidate & repaint
		// --------------------
		Panel.revalidate();
		Panel.repaint();	
	}
}
