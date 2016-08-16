package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.couponproject.facade.CompanyFacade;
import com.couponproject.gui.frames.NewCouponForm;

public class NewCompanysCouponActionListener implements ActionListener {

	// **********
	// Attributes
	// **********
	CompanyFacade companyFacade;
	
	// ***********
	// constructor
	// ***********
	public NewCompanysCouponActionListener(CompanyFacade companyFacade) {
		this.companyFacade = companyFacade;
	}

	// ***************
	// actionPerformed 
	// ***************
	@Override
	public void actionPerformed(ActionEvent e) {
		//open a frame with a form
		NewCouponForm newCouponForm = new NewCouponForm(companyFacade);
		newCouponForm.setVisible(true);
		
	}
	
}
