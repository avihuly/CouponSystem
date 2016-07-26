package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.RemoveCustomerActionListener;
import com.couponproject.gui.Actionlisteners.UpdateCustomerActionListener;

public class AllCustomersFrame extends JFrame {
	// **********
	// Attributes
	// **********
	JTable clientsTable = new JTable();

	// ***********
	// constructor
	// ***********
	public AllCustomersFrame(AdminFacade adminFacade) {
		// frame properties
		super("Admin - All Custimers");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		// set layout
		getContentPane().setLayout(new BorderLayout());
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 750, 75);

		// **********************
		// West Panel - bnt panel
		// **********************
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(10, 1, 0, 0));

		JButton bntRemoveCustomer = new JButton("Remove customer");
		westPanel.add(bntRemoveCustomer);

		JButton bntUpdateCustomer = new JButton("Update customer");
		westPanel.add(bntUpdateCustomer);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(clientsTable);
		CenterPanel.add(sp, BorderLayout.CENTER);
		try {
			GuiUtil.clientsToTable(clientsTable, adminFacade.getAllCustomers());
		} catch (AdminFacadeException e) {// TODO:
		}

		// make it all fit
		pack();

		// ***************
		// ActionListeners
		// ***************
		// RemoveCustomer
		bntRemoveCustomer.addActionListener(new RemoveCustomerActionListener(adminFacade, clientsTable));

		// UpdateCustomer
		bntUpdateCustomer.addActionListener(e -> {
			UpDateCustomerFrame customerInfoFrame = new UpDateCustomerFrame(adminFacade, clientsTable);
			customerInfoFrame.setVisible(true);
		});

	}
}
