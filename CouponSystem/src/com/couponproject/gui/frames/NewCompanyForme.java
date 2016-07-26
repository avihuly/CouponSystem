package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.CreateCompanyActionListener;

public class NewCompanyForme extends JFrame {
	// **********
	// Attributes
	// **********
	private JTextArea txtName = new JTextArea();
	private JTextArea txtPassword = new JTextArea();
	private JTextArea txtEmail = new JTextArea();
	

	// ***********
	// constructor
	// ***********
	public NewCompanyForme(AdminFacade adminFacade) {
		// frame properties
		super("Admin - New Company");
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
		
		JLabel lblCompanyName = new JLabel("  Company Name: ");
		westPanel.add(lblCompanyName);
		
		JLabel lblCompanyEmail = new JLabel("  Email: ");
		westPanel.add(lblCompanyEmail);
		
		JLabel lblCompanyPassword = new JLabel("  Password: ");
		westPanel.add(lblCompanyPassword);

		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		JButton bntUpdate = new JButton("Create Company");
		// Create company ActionListener
		bntUpdate.addActionListener(new CreateCompanyActionListener(adminFacade, txtName, txtPassword, txtEmail));
		add(bntUpdate, BorderLayout.SOUTH);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(5, 1, 5, 5));

		CenterPanel.add(txtName);
		CenterPanel.add(txtEmail);
		CenterPanel.add(txtPassword);	
	}
}
