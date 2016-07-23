package com.couponproject.gui;

import javax.swing.JSlider;
import javax.swing.JTable;

import com.couponproject.facade.CustomerFacade;

public class CouponByPriceSliders extends JSlider {
	static final int MIN_PRICE = 10;
	static final int MAX_PRICE = 100;
	static final int INIT_PRICE = 10;
	
	public CouponByPriceSliders(JTable tableCouponData, CustomerFacade customerFacade) {
		super(JSlider.HORIZONTAL, MIN_PRICE, MAX_PRICE, INIT_PRICE);
		setMajorTickSpacing(10);
		setPaintLabels(true);
		setPaintTicks(true);

		addChangeListener(e -> {
			try {
				JSlider Source = (JSlider) e.getSource();  
				double price = Source.getValue();
				
				GuiUtil.CouponsToTable(tableCouponData, customerFacade.getAllPurchasedCouponsByPrice(price));
			} catch (Exception e1) {}
		});

	}

}
