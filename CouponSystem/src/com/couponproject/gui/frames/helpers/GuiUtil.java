package com.couponproject.gui.frames.helpers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.*;
import com.couponproject.beans.*;
import com.couponproject.constants.Constants;
import com.couponproject.gui.Actionlisteners.MessageCellRenderer;

public class GuiUtil {
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
	
	
	public static JDatePickerImpl datePickerInitialization (){
	UtilDateModel dateModel = new UtilDateModel();
	Properties p = new Properties();
	p.put("text.today", "Today");
	p.put("text.month", "Month");
	p.put("text.year", "Year");
	dateModel.setSelected(true);
	JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
	return new JDatePickerImpl(datePanel, new DateLabelFormatter());
}
	}
