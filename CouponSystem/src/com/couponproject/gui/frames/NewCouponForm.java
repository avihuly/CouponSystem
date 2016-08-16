package com.couponproject.gui.frames;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.*;
import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;
import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.Actionlisteners.*;
import com.couponproject.gui.frames.helpers.GuiUtil;
import com.couponproject.gui.frames.helpers.ImagePathFromJfilechooser;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class NewCouponForm extends TemplateFrame {
	// **********
	// Attributes
	// **********
	CompanyFacade companyFacade;
	private JTextField couponTitle;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private JTextField amountTxt;
	private JComboBox<CouponType> typeBox;
	private JTextArea messegeTxt;
	private JTextField priceTxt;
	private JLabel imagePathlbl;
	private JLabel targetPathlbl = new JLabel();

	// ***********
	// constructor
	// ***********
	public NewCouponForm(CompanyFacade companyFacade) {
		super("New Coupon",400,570);
		this.companyFacade = companyFacade;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

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
		amountTxt = new JTextField();
		amountTxt.addKeyListener(new IntKeyListener());

		centerPanelTop.add(couponTitle);
		centerPanelTop.add(startDatePicker);
		centerPanelTop.add(endDatePicker);
		centerPanelTop.add(amountTxt);

		messegeTxt = new JTextArea();
		messegeTxt.addKeyListener(new MessegeKeyListener(messegeTxt));
		messegeTxt.setBorder(BorderFactory.createLineBorder(Color.gray));
		centerPanelMiddle.add(messegeTxt);

		typeBox = new JComboBox<>();
		for (CouponType type : CouponType.values())
			typeBox.addItem(type);
		priceTxt = new JTextField();
		priceTxt.addKeyListener(new PriceKeyListener());

		JPanel imagePathPanel = new JPanel(new BorderLayout());
		imagePathlbl = new JLabel();
		imagePathlbl.setBorder(BorderFactory.createLineBorder(Color.gray));
		JButton imagePathBnt = new JButton("...");

		imagePathPanel.add(imagePathlbl, BorderLayout.CENTER);
		imagePathPanel.add(imagePathBnt, BorderLayout.EAST);
		
		imagePathBnt.addActionListener(e -> {
			new ImagePathFromJfilechooser(imagePathlbl,targetPathlbl);
		});

		centerPanelBut.add(typeBox);
		centerPanelBut.add(priceTxt);
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
		
		// ----------------
		// Creating coupon
		// ----------------
		submitBtn.addActionListener(e -> {
			// Declaring Coupon variables
			String title;
			String messege;
			String imagePath;
			String targetPath;
			CouponType couponType;
			LocalDate startDate;
			LocalDate endDate;
			java.util.Date utilDate;
			int amount;
			double price;

			// initializing Coupon variables
			try {
				title = couponTitle.getText();
				messege = messegeTxt.getText();
				couponType = (CouponType) typeBox.getSelectedItem();
				utilDate = (java.util.Date) startDatePicker.getModel().getValue();
				startDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				utilDate = (java.util.Date) endDatePicker.getModel().getValue();
				endDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				price = Double.parseDouble(priceTxt.getText());
				amount = Integer.parseInt(amountTxt.getText());
				imagePath = imagePathlbl.getText();
				targetPath = targetPathlbl.getText();
				
				if(imagePath == null) {
					targetPath = "image/CouponPics/default.png";
				} 

				// Create the coupon
				Coupon coupon = new Coupon(title, startDate, endDate, amount, couponType, messege, price, targetPath);
				companyFacade.createCoupon(coupon);
				
				if(imagePath != null) {
					// Copy coupon image
					FileInputStream in = new FileInputStream(imagePath);
					FileOutputStream out = new FileOutputStream(targetPath);
					int bytesRead = 0;
					byte[] bucket = new byte[256];
					while ((bytesRead = in.read(bucket)) > -1) {
						out.write(bucket, 0, bytesRead);
					}
					out.close();
					in.close();
				}
			} catch (NumberFormatException priceE) {
				JOptionPane.showMessageDialog(null, "Iligal price!");
			} catch (CouponTitleAlreadyExistException titleE) {
				JOptionPane.showMessageDialog(null, "Coupon title already exist");
			} catch (CompanyFacadeException facadeE) {
				JOptionPane.showMessageDialog(null, facadeE.getMessage());
			} catch (Exception ioE){
				ioE.printStackTrace();
			}
		});

		// clear form button
		JButton clearFormBtn = new JButton("Clear From");
		clearFormBtn.addActionListener(e -> {
			couponTitle.setText(null);
			amountTxt.setText(null);
			typeBox.setSelectedIndex(-1);
			messegeTxt.setText(null);
			priceTxt.setText(null);
			imagePathlbl.setText(null);
		});
		southPanel.add(clearFormBtn, BorderLayout.EAST);
	}
}
