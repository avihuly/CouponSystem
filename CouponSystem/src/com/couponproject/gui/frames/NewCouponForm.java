package com.couponproject.gui.frames;

import java.awt.*;
import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

import com.couponproject.constants.CouponType;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.IntKeyListener;
import com.couponproject.gui.Actionlisteners.PriceKeyListener;

public class NewCouponForm extends JFrame {
	// **********
	// Attributes
	// **********
	CompanyFacade companyFacade;
	private JTextField couponTitle;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private JTextField amount;
	private JComboBox<CouponType> typeBox;
	private JTextArea messege;
	private JTextField price;
	private JLabel imagePath;
	

	// ***********
	// constructor
	// ***********
	public NewCouponForm(CompanyFacade companyFacade) {
		this.companyFacade = companyFacade;

		// frame properties
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 400, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		// West Panel
		// ***********
		JPanel westPanel = new JPanel(new BorderLayout());
		getContentPane().add(westPanel, BorderLayout.WEST);

		JPanel westPanelTop = new JPanel(new GridLayout(4, 1, 0, 10));
		JPanel westPanelBut = new JPanel(new GridLayout(11, 1, 0, 0));

		JLabel labelCouponTitle = new JLabel("Coupon Title:");
		JLabel labelStartDate = new JLabel("Start Date:");
		JLabel labelEndDate = new JLabel("End Date:");
		JLabel labelAmount = new JLabel("Amount:");
		
		westPanelTop.add(labelCouponTitle);
		westPanelTop.add(labelStartDate);
		westPanelTop.add(labelEndDate);
		westPanelTop.add(labelAmount);

		JLabel labelMassage = new JLabel("Massage:");

		JLabel labelType = new JLabel("Coupon Type:");
		JLabel labelPrice = new JLabel("Price:");
		JLabel labelImage = new JLabel("Image Location:");

		westPanelBut.add(labelMassage);
		for (int i = 0; i < 7; i++) {
			JLabel blankLbl = new JLabel();
			westPanelBut.add(blankLbl);
		}
		westPanelBut.add(labelType);
		westPanelBut.add(labelPrice);
		westPanelBut.add(labelImage);

		westPanel.add(westPanelTop, BorderLayout.NORTH);
		westPanel.add(westPanelBut, BorderLayout.CENTER);

		// ***********
		// East Panel
		// ***********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(8, 1, 0, 0));

		// ***********
		// Center Panel
		// ***********
		JPanel CenterPanel = new JPanel(new BorderLayout());
		getContentPane().add(CenterPanel, BorderLayout.CENTER);

		JPanel centerPanelTop = new JPanel(new GridLayout(4, 1, 0, 0));
		JPanel centerPanelMiddle = new JPanel(new GridLayout());
		JPanel centerPanelBut = new JPanel(new GridLayout(3, 1, 0, 0));

		CenterPanel.add(centerPanelTop, BorderLayout.NORTH);
		CenterPanel.add(centerPanelMiddle, BorderLayout.CENTER);
		CenterPanel.add(centerPanelBut, BorderLayout.SOUTH);

		couponTitle = new JTextField();
		startDatePicker = GuiUtil.datePickerInitialization();
		endDatePicker = GuiUtil.datePickerInitialization();
		amount = new JTextField();
		amount.addKeyListener(new IntKeyListener());

		centerPanelTop.add(couponTitle);
		centerPanelTop.add(startDatePicker);
		centerPanelTop.add(endDatePicker);
		centerPanelTop.add(amount);

		messege = new JTextArea();
		messege.setBorder(BorderFactory.createLineBorder(Color.gray));
		centerPanelMiddle.add(messege);

		typeBox = new JComboBox<>();
		for (CouponType type : CouponType.values())
			typeBox.addItem(type);
		price = new JTextField();
		price.addKeyListener(new PriceKeyListener());
		
		JPanel imagePathPanel = new JPanel(new BorderLayout());
		imagePath = new JLabel();
		imagePath.setBorder(BorderFactory.createLineBorder(Color.gray));
		JButton imagePathBnt = new JButton("..."); 
		
		imagePathPanel.add(imagePath, BorderLayout.CENTER);
		imagePathPanel.add(imagePathBnt, BorderLayout.EAST);
		
		imagePathBnt.addActionListener(e -> {
			new ImagePathFromJfilechooser(imagePath);	
		});
		
		centerPanelBut.add(typeBox);
		centerPanelBut.add(price);
		centerPanelBut.add(imagePathPanel);

		// ***********
		// South Panel
		// ***********

		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new BorderLayout(0, 0));

		// submit button
		JButton submitBtn = new JButton("Submit");
		southPanel.add(submitBtn, BorderLayout.WEST);

		submitBtn.addActionListener(e -> {
//			FileInputStream in;
//			try {
//				in = new FileInputStream();
//
//				String targetPath = "image/CouponPics/" + imageName;
//				System.out.println(targetPath);
//				FileOutputStream out;
//
//				out = new FileOutputStream(targetPath);
//
//				int bytesRead = 0;
//				byte[] bucket = new byte[256];
//
//				// Use the bucket to move information from src to dests
//				while ((bytesRead = in.read(bucket)) > -1) {
//					out.write(bucket, 0, bytesRead);
//				}
//				out.close();
//			} catch (IOException e) {
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//			}
		});

		// clear form button
		JButton clearFormBtn = new JButton("Clear From");
		clearFormBtn.addActionListener(e -> {
			couponTitle.setText(null);
			amount.setText(null);
			typeBox.setSelectedIndex(-1);
			messege.setText(null);
			price.setText(null);
			imagePath.setText(null);
		});
		southPanel.add(clearFormBtn, BorderLayout.EAST);
	}
}
