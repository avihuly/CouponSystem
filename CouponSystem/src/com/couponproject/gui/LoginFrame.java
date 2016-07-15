package com.couponproject.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.couponproject.constants.ClientType;
import com.couponproject.gui.Actionlisteners.LoginActionlistener;

public class LoginFrame extends JFrame {

	// **********
	// Attributes
	// **********
	private ButtonGroup userTypeRadioGroup = new ButtonGroup();
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	// ***********
	// constructor
	// ***********
	public LoginFrame(){
		super("Coupons");
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 313, 286);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);
		// Set Layout
		getContentPane().setLayout(new BorderLayout(0, 0));

		
		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));

		JPanel bntPanel = new JPanel();
		southPanel.add(bntPanel);
		bntPanel.setLayout(new BorderLayout(0, 0));

		JButton LoginBnt = new JButton("Login");
		
		// Login icon
		Image loginIcon;
		try {
			File input = new File("image/loginIcon.png");
			loginIcon = ImageIO.read(input);
			LoginBnt.setIcon(new ImageIcon(loginIcon.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}
		
		bntPanel.add(LoginBnt, BorderLayout.SOUTH);

		JPanel radioPanel = new JPanel();
		southPanel.add(radioPanel, BorderLayout.NORTH);
		radioPanel.setBorder(new TitledBorder(null, "Login As", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JRadioButton radioCustomer = new JRadioButton("Customer");
		radioCustomer.setSelected(true);
		// ******* setActionCommand *******
		radioCustomer.setActionCommand(ClientType.Customer.name());
		userTypeRadioGroup.add(radioCustomer);
		radioPanel.add(radioCustomer);

		JRadioButton radioCompany = new JRadioButton("Company");
		// ******* setActionCommand *******
		radioCompany.setActionCommand(ClientType.Company.name());
		userTypeRadioGroup.add(radioCompany);
		radioPanel.add(radioCompany);

		JRadioButton radioAdmin = new JRadioButton("Admin");
		userTypeRadioGroup.add(radioAdmin);
		// ******* setActionCommand *******
		radioAdmin.setActionCommand(ClientType.Admin.name());
		radioPanel.add(radioAdmin);

		
		
		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JLabel labelUserName = new JLabel("User Nane:");
		westPanel.add(labelUserName);

		JLabel labelPassword = new JLabel("Password:");
		westPanel.add(labelPassword);

		
		// ***********
		// East Panel
		// ***********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(6, 1, 0, 0));

		// ***********
		// Center Panel
		// ***********
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(3, 1, 0, 0));

		txtUserName = new JTextField();
		CenterPanel.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();
		CenterPanel.add(txtPassword);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this,300,50);
		
		
		// *****************************
		// LoginBnt & Enter Key - Action
		// *****************************
		LoginBnt.addActionListener(new LoginActionlistener(
				(JFrame)this,
				userTypeRadioGroup, 
				txtUserName, 
				txtPassword));
	}
}
