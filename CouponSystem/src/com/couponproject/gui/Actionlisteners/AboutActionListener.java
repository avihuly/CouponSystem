package com.couponproject.gui.Actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AboutActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
				"Coupons v1.1\n\n" + 
				"Author: Orit Blum & Avi Huly \n" + 
				"Release Date: 09.07.2016\n\n"+ 
				"No. 1 coupon app since coupons were invented",
				
				"Coupons v1.1", 
				JOptionPane.INFORMATION_MESSAGE);
	}
}
