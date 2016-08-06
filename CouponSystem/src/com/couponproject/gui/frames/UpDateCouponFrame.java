package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.Constants;

import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.GuiUtil;

public class UpDateCouponFrame extends JFrame {
	
	// **********
	// Attributes
	// **********
	CompanyFacade companyFacade;
	private JLabel lblCouponTitle;
	private JLabel lblStartDate;
	private JDatePickerImpl endDatePicker;
	private JLabel lblAmount;
	private JLabel lblType;
	private JLabel lblMessege;
	private JTextArea txtPrice;
	private JLabel lblImage;
	
	public UpDateCouponFrame(CompanyFacade companyFacade, JTable tableCouponData) {
		// frame properties
		super("Coupon - Coupon Update");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 400, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Component c = SwingUtilities.getRoot(tableCouponData);
				JFrame frame = (JFrame) c;
				frame.setEnabled(true);		
			}
		});

		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 300, 40);

		// selected coupon
		Coupon coupon;
		try {
			TableModel tableModel  = tableCouponData.getModel();	
			coupon = companyFacade.getCoupon((long) tableModel.getValueAt(tableCouponData.getSelectedRow(), Constants.couponTableIDIndex));
			// **************************
			// Center Panel - Table panel
			// **************************
			JPanel CenterPanel = new JPanel();
			getContentPane().add(CenterPanel, BorderLayout.CENTER);
			CenterPanel.setLayout(new GridLayout(9, 1, 5, 5));

			JLabel lblCouponIdVlaue = new JLabel(Long.toString(coupon.getId()));
			CenterPanel.add(lblCouponIdVlaue);

			lblCouponTitle = new JLabel(coupon.getTitle());
			CenterPanel.add(lblCouponTitle);
			
			lblStartDate = new JLabel(coupon.getStartDate().toString());
			CenterPanel.add(lblStartDate);
			
			
			// Creating date Picker 			
			endDatePicker = GuiUtil.datePickerInitialization();			
			
			endDatePicker.getModel().setDay(coupon.getEndDate().getDayOfMonth());
			endDatePicker.getModel().setMonth(coupon.getEndDate().getMonthValue());
			endDatePicker.getModel().setYear(coupon.getEndDate().getYear());
			
			
			
			

			// Adding date Picker to panel			
			CenterPanel.add(endDatePicker);
			
			lblAmount = new JLabel(Integer.toString(coupon.getAmount()));
			CenterPanel.add(lblAmount);
			
			lblType = new JLabel(coupon.getType().name());
			CenterPanel.add(lblType);
			
			lblMessege = new JLabel(coupon.getMessage());
			CenterPanel.add(lblMessege);
			
			txtPrice = new JTextArea(Double.toString(coupon.getPrice()));
			CenterPanel.add(txtPrice);

			// **********************
			// West Panel - bnt panel
			// **********************
			JPanel westPanel = new JPanel();
			getContentPane().add(westPanel, BorderLayout.WEST);
			westPanel.setLayout(new GridLayout(9, 1, 5, 5));
			
			JLabel labelCouponId = new JLabel("Coupon ID:");
			westPanel.add(labelCouponId);
			
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
				
			// ***********
			// South Panel
			// ***********
			JPanel southPanel = new JPanel();
			southPanel.setLayout(new BorderLayout());
			
			// ---------------------
			// Update ActionListener
			// ---------------------
			JButton bntUpdate = new JButton("Update");
			// Update ActionListener
			bntUpdate.addActionListener(updateE ->{
				// coupon.setEndDate(endDate);
				try {
					java.util.Date utilDate = (java.util.Date) endDatePicker.getModel().getValue();
					
					LocalDate selectedEndDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					Double price = Double.parseDouble(txtPrice.getText());
					
					coupon.setEndDate(selectedEndDate);
					coupon.setPrice(price);
					companyFacade.updateCoupon(coupon);
					
					int selectedRow = tableCouponData.getSelectedRow();
					DefaultTableModel model = (DefaultTableModel) tableCouponData.getModel(); 
					
					model.setValueAt(selectedEndDate, selectedRow, Constants.couponTableEndDateIndex);
					model.setValueAt(price, selectedRow, Constants.couponTablePriceIndex);
					
					tableCouponData.getParent().revalidate();
					tableCouponData.getParent().repaint();
					
					Component c = SwingUtilities.getRoot(tableCouponData);
					JFrame frame = (JFrame) c;
					frame.setEnabled(true);
					
					GuiUtil.disposeFrameByEvent(updateE);
				} catch (CompanyFacadeException | CouponTitleAlreadyExistException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Price must be a number"); 
				}});				
			southPanel.add(bntUpdate, BorderLayout.SOUTH);
					
			lblImage = new JLabel();
			Image couponImg;
			try {
				couponImg = ImageIO.read(new File(coupon.getImage()));
				ImageIcon couponIcon = new ImageIcon(couponImg.getScaledInstance(400, 130, Image.SCALE_SMOOTH));
				lblImage.setIcon(couponIcon);
			} catch (IOException ImgE) {
				ImgE.printStackTrace();
				lblImage.setText("Coupon picture wasn't able to be loaded!");
			}	
			
			southPanel.add(lblImage, BorderLayout.NORTH);
			
	
			getContentPane().add(southPanel, BorderLayout.SOUTH);
			
		} catch (CompanyFacadeException e) {} 
	}
}
