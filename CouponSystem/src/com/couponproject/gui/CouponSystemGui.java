package com.couponproject.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JMenuItem;

public class CouponSystemGui {

	private JFrame loginFrame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CouponSystemGui window = new CouponSystemGui();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CouponSystemGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setResizable(false);
		loginFrame.setBackground(Color.LIGHT_GRAY);
		loginFrame.setBounds(100, 100, 313, 286);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// loginFrame - Icon set
		try {
			// getting image refrains
			File input = new File("image/frameIcon.png");
			Image frameImg = ImageIO.read(input);
/*
			// resizing image to fit panel
			Image frameImgResized = frameImg.getScaledInstance
					(40, 40, Image.SCALE_SMOOTH);
*/
			// loading icon to frame
			loginFrame.setIconImage(frameImg);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}
		
		
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		loginFrame.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		// About Icon - "image/about.png"
		JMenuItem mntmAbout = new JMenuItem("About");

		try {
			// getting image refrains
			File input = new File("image/about.png");
			Image aboutImg = ImageIO.read(input);

			// resizing image to fit panel
			Image aboutImgResized = aboutImg.getScaledInstance
					(20, 20, Image.SCALE_SMOOTH);

			// converting image to icon
			ImageIcon aboutIcon = new ImageIcon(aboutImgResized);

			// loading icon to menu item
			mntmAbout.setIcon(aboutIcon);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}

		

		mnHelp.add(mntmAbout);
		loginFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel southPanel = new JPanel();
		loginFrame.getContentPane().add(southPanel, BorderLayout.SOUTH);
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

		LoginBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bntPanel.add(LoginBnt, BorderLayout.SOUTH);

		JPanel radioPanel = new JPanel();
		southPanel.add(radioPanel, BorderLayout.NORTH);
		radioPanel.setBorder(new TitledBorder(null, "Login As", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JRadioButton radioButton = new JRadioButton("Customer");
		radioButton.setSelected(true);
		buttonGroup.add(radioButton);
		radioPanel.add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("Company");
		buttonGroup.add(radioButton_1);
		radioPanel.add(radioButton_1);

		JRadioButton radioButton_2 = new JRadioButton("Admin");
		buttonGroup.add(radioButton_2);
		radioPanel.add(radioButton_2);

		JPanel westPanel = new JPanel();
		loginFrame.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JLabel label = new JLabel("User Nane:");
		westPanel.add(label);

		JLabel label_1 = new JLabel("Password:");
		westPanel.add(label_1);

		JPanel eastPanel = new JPanel();
		loginFrame.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(6, 1, 0, 0));

		JPanel CenterPanel = new JPanel();
		loginFrame.getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(3, 1, 0, 0));

		textField = new JTextField();
		CenterPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		CenterPanel.add(textField_1);
		textField_1.setColumns(10);

		// LOGO ******
		JPanel northPanel = new JPanel();
		Image logoImg;

		try {
			// getting image refrains
			File input = new File("image/couponLogo.jpg");
			logoImg = ImageIO.read(input);

			// resizing image to fit panel
			Image logoImgResized = logoImg.getScaledInstance(300, 50, Image.SCALE_SMOOTH);

			// converting image to icon
			ImageIcon logoIcon = new ImageIcon(logoImgResized);

			// loading icon to Jlabel
			JLabel logoLabel = new JLabel("", logoIcon, JLabel.CENTER);

			// loading Jlabel to panel
			northPanel.add(logoLabel);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}

		loginFrame.getContentPane().add(northPanel, BorderLayout.NORTH);

	}

}
