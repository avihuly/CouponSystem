package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.omg.CORBA.portable.ValueOutputStream;

import com.couponproject.beans.Coupon;
import com.couponproject.constants.CouponType;
import com.couponproject.dbdao.CompanyDBDAO;
import com.couponproject.exception.CompanyFacadeException;
import com.couponproject.exception.CouponTitleAlreadyExistException;
import com.couponproject.facade.CompanyFacade;

public class SubmittingFormNewCouponActionListener implements ActionListener {

	// **********
	// Attributes
	// **********
	JFrame newCouponForm;
	CompanyFacade companyFacade;
	
	JTextField txtCouponTitle;
	JFormattedTextField  txtStartDate;
	JFormattedTextField  txtEndDate;
	JTextField txtAmount;
	JTextField txtType;
	JTextField txtMessege;
	JTextField txtPrice;
	JTextField txtImage;
	
	String couponTitle;
	LocalDate startDate;
	LocalDate endDate;
	int amount;
	CouponType type;
	String messege;
	double price;
	String image;
	
	// ***********
	// Constructor
	// ***********
	
	public SubmittingFormNewCouponActionListener(JFrame newCouponForm, CompanyFacade companyFacade, 
			JTextField txtCouponTitle, JFormattedTextField  txtStartDate, JFormattedTextField  txtEndDate, JTextField txtAmount,
			JTextField txtType, JTextField txtMessege, JTextField txtPrice, JTextField txtImage) 
	{
		this.newCouponForm=newCouponForm;
		this.companyFacade=companyFacade;
		this.txtCouponTitle=txtCouponTitle;
		this.txtStartDate=txtStartDate;
		this.txtEndDate=txtEndDate;
		this.txtAmount=txtAmount;
		this.txtType=txtType;
		this.txtMessege=txtMessege;
		this.txtPrice=txtPrice;
		this.txtImage=txtImage;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		couponTitle=txtCouponTitle.getText();
		startDate=(LocalDate) txtStartDate.getValue();
		System.out.println(startDate);
		endDate=(LocalDate) txtEndDate.getValue();
		amount=Integer.parseInt(txtAmount.getText());
		type=CouponType.valueOf(txtType.getText());
		messege=txtMessege.getText();
		price=Double.parseDouble(txtPrice.getText());
		image=txtImage.getText();
		
		try {
			companyFacade.createCoupon(new Coupon(couponTitle, startDate, endDate, amount, type, messege, price, image));
		} catch (CompanyFacadeException | CouponTitleAlreadyExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
