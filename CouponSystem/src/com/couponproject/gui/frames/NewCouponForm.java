package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.SubmittingFormNewCouponActionListener;

public class NewCouponForm extends JFrame{
	// **********
	// Attributes
	// **********
	CompanyFacade companyFacade;
	private JTextField couponTitle;
	private JFormattedTextField  startDate;
	private JFormattedTextField  endDate;
	private JTextField amount;
	private JTextField type;
	private JTextField messege;
	private JTextField price;
	private JTextField image;
	
	// ***********
	// constructor
	// ***********
	public NewCouponForm(CompanyFacade companyFacade){
		this.companyFacade=companyFacade;
		
		// frame properties
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);
		
		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 600, 60);
		
		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(8, 1, 0, 0));

		JLabel labelCouponTitle = new JLabel("Coupon Title:");
		westPanel.add(labelCouponTitle);

		JLabel labelStartDate = new JLabel("Start Date:");
		westPanel.add(labelStartDate);

		JLabel labelEndDate = new JLabel("End Date:");
		westPanel.add(labelEndDate);
		
		JLabel labelAmount = new JLabel("Amount:");
		westPanel.add(labelAmount);
		
		JLabel labelType = new JLabel("Coupon Type:");
		westPanel.add(labelType);
		
		JLabel labelMassage = new JLabel("Massage:");
		westPanel.add(labelMassage);
		
		JLabel labelPrice = new JLabel("Price:");
		westPanel.add(labelPrice);
		
		JLabel labelImage = new JLabel("Image Location:");
		westPanel.add(labelImage);
				
		// ***********
		// East Panel
		// ***********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(8, 1, 0, 0));

		// ***********
		// Center Panel
		// ***********
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(8, 1, 0, 0));

		couponTitle = new JTextField();
		CenterPanel.add(couponTitle);
		couponTitle.setColumns(10);
				
		startDate = new JFormattedTextField();
		CenterPanel.add(startDate);
		startDate.setColumns(10);
		
		endDate = new JFormattedTextField();
		CenterPanel.add(endDate);
		endDate.setColumns(10);
		
		amount = new JTextField();
		CenterPanel.add(amount);
		amount.setColumns(10);
		
		type = new JTextField();
		CenterPanel.add(type);
		type.setColumns(10);
		
		messege = new JTextField();
		CenterPanel.add(messege);
		messege.setColumns(10);
		
		price = new JTextField();
		CenterPanel.add(price);
		price.setColumns(10);
		
		image = new JTextField();
		CenterPanel.add(image);
		image.setColumns(10);
		//TODO: how to change to open file?
		
		// ***********
		// South Panel
		// ***********

		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));
		
		//submit button
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new SubmittingFormNewCouponActionListener(this, companyFacade, couponTitle, startDate, 
						endDate, amount, type, messege, price, image));
		southPanel.add(submitBtn, BorderLayout.WEST);
		
		//clear form button
		JButton clearFormBtn = new JButton("Clear From");
		clearFormBtn.addActionListener(e ->{
			couponTitle.setText(null); 
			startDate.setText(null);
			endDate.setText(null);
			amount.setText(null);
			type.setText(null);
			messege.setText(null);
			price.setText(null);
			image.setText(null);
		});
		southPanel.add(clearFormBtn, BorderLayout.EAST);
	}
}
