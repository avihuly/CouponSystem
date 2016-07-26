package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.couponproject.beans.Company;
import com.couponproject.gui.GuiUtil;

public class InfoCompanyFrame extends JFrame {
	// ***********
	// constructor
	// ***********
	public InfoCompanyFrame(Company company) {
		// frame properties
		super("Admin - Company Info");
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
		CenterPanel.setLayout(new GridLayout(4, 1, 5, 5));

		JLabel lblBlanck = new JLabel(" Company Info:");
		CenterPanel.add(lblBlanck);

		JLabel lblID = new JLabel(" ID: " + company.getId());
		CenterPanel.add(lblID);

		JLabel lblName = new JLabel(" Name: " + company.getCompName());
		CenterPanel.add(lblName);
		
		JLabel lblEmail = new JLabel(" Email: " + company.getEmail());
		CenterPanel.add(lblEmail);

		JButton bntBack = new JButton("Back");
		// bntBack ActionListener
		bntBack.addActionListener(e -> {
			this.dispose();
		});
		add(bntBack, BorderLayout.SOUTH);

	}
}
