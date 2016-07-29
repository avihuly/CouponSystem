package com.couponproject.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.couponproject.beans.Company;
import com.couponproject.beans.Coupon;
import com.couponproject.beans.Customer;
import com.couponproject.constants.Constants;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.Actionlisteners.AboutActionListener;
import com.couponproject.gui.Actionlisteners.AllCouponsActionListener;
import com.couponproject.gui.Actionlisteners.MessageCellRenderer;
import com.couponproject.gui.Actionlisteners.PurchasedCouponsActionListener;

public class GuiUtil {
	// ********
	// Set logo
	// ********
	public static void setLogoBySize(JFrame frame, int width, int height) {
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

	// *****************
	// Set Icon And Menu
	// *****************
	public static void setFrameIconAndMenu(JFrame frame) {
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

		// -------
		// MenuBar
		// -------
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// MenuItem - About
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
		}
		mnHelp.add(mntmAbout);
	}

	// ****************
	// Coupon Table Set
	// ****************
	public static void CouponsToTable(JTable tableCouponData, Collection<Coupon> couponCollection) {
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

		for (Coupon coupon : couponCollection) {
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

		// Setting column width by percentage
		for (int i = 0; i < tableCouponData.getColumnCount(); i++) {
			// Calculating and assigning each column width
			tableCouponData.getColumnModel().getColumn(i).setPreferredWidth((int) (columnWidthPercentage[i] * tW));
		}

		tableCouponData.setRowHeight(50);
		// Message Column Renderer
		tableCouponData.getColumnModel().getColumn(Constants.couponTableMESSAGEIndex)
				.setCellRenderer(new MessageCellRenderer());
	}

	// ********************
	// clients To Table Set
	// ********************
	public static void clientsToTable(JTable customerTable, Collection<Customer> allCustomers) {
		// Disable editing
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("PASSWORD");

		for (Customer customer : allCustomers) {
			ArrayList<Object> tempCustomer = new ArrayList<>();
			tempCustomer.add(customer.getId());
			tempCustomer.add(customer.getCustName());
			tempCustomer.add(customer.getPassword());

			model.addRow(tempCustomer.toArray());
		}
		// Adding model to table
		customerTable.setModel(model);

		// Center alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < (customerTable.getColumnCount()); i++) {
			customerTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// First row selected as default
		if (customerTable.getRowCount() > 0) {
			customerTable.setRowSelectionInterval(0, 0);
		}

		// RowSorter
		customerTable.setAutoCreateRowSorter(true);
		customerTable.setRowHeight(30);
	}

	// ********************
	// clients To Table Set
	// ********************
	public static void CompaniesToTable(JTable companiesTable, Collection<Company> allCompanies) {
		// Disable editing
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("EMAIL");
		model.addColumn("PASSWORD");

		for (Company company : allCompanies) {
			ArrayList<Object> tempCompany = new ArrayList<>();
			tempCompany.add(company.getId());
			tempCompany.add(company.getCompName());
			tempCompany.add(company.getEmail());
			tempCompany.add(company.getPassword());

			model.addRow(tempCompany.toArray());
		}
		// Adding model to table
		companiesTable.setModel(model);

		// Center alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < (companiesTable.getColumnCount()); i++) {
			companiesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// First row selected as default
		if (companiesTable.getRowCount() > 0) {
			companiesTable.setRowSelectionInterval(0, 0);
		}

		// RowSorter
		companiesTable.setAutoCreateRowSorter(true);
		companiesTable.setRowHeight(30);

	}

	// **********************
	// Dispose frame by event
	// **********************
	public static void disposeFrameByEvent(ActionEvent e) {
		Container sourceFrame = ((JButton) e.getSource()).getParent();
		while (!(sourceFrame instanceof JFrame)) {
			sourceFrame = sourceFrame.getParent();
		}
		((JFrame) sourceFrame).dispose();
	}
}
