package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.couponproject.beans.Customer;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.RemoveCustomerActionListener;

public class CustomerInfoFrame extends JFrame {
	private JTextArea txtName = new JTextArea();
	private JTextArea txtPassword = new JTextArea();

	public CustomerInfoFrame(AdminFacade adminFacade, JTable clientsTable) {
		// frame properties
		super("Admin - Custimer Info");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 400, 40);

		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		// **********
		// East Panel
		// **********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);

		// **********************
		// West Panel - bnt panel
		// **********************
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(5, 1, 5, 5));

		JLabel lblCustomerID = new JLabel("ID: " + id);
		westPanel.add(lblCustomerID);

		JLabel lblCustomerName = new JLabel("Name: ");
		westPanel.add(lblCustomerName);

		JLabel lblCustomerPassword = new JLabel("Password: ");
		westPanel.add(lblCustomerPassword);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(5, 1, 5, 5));

		try {
			Customer customer = adminFacade.getCustomer(id);

			JLabel lblBlanck = new JLabel();
			CenterPanel.add(lblBlanck);

			txtName = new JTextArea(customer.getCustName());
			CenterPanel.add(txtName);

			txtPassword = new JTextArea(customer.getPassword());
			CenterPanel.add(txtPassword);

		} catch (AdminFacadeException e) {
			// TODO
		}

		// make it all fit
		pack();
	}
	
	public JTextArea getNameTextFiled() {
		return txtName;
	}
	
	public JTextArea getPasswordTextFiled() {
		return txtPassword;
	}
	
	
}
