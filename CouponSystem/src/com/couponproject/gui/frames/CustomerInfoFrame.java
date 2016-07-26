package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import com.couponproject.beans.Customer;
import com.couponproject.constants.Constants;
import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.UpdateCustomerActionListener;

public class CustomerInfoFrame extends JFrame {
	// ***********
	// constructor
	// ***********
	public CustomerInfoFrame(Customer customer) {
		// frame properties
		super("Admin - Custimer Info");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 300, 40);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(3, 1, 5, 5));

		JLabel lblBlanck = new JLabel("Customer Info:");
		CenterPanel.add(lblBlanck);

		JLabel lblID = new JLabel("ID: " + customer.getId());
		CenterPanel.add(lblID);

		JLabel lblName = new JLabel("Name: " + customer.getCustName());
		CenterPanel.add(lblName);

		JButton bntBack = new JButton("Back");
		// bntBack ActionListener
		bntBack.addActionListener(e->{
			this.dispose();
		});
		add(bntBack, BorderLayout.SOUTH);

	}
}
