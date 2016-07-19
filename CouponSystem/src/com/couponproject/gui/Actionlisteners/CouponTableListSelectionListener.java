package com.couponproject.gui.Actionlisteners;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CouponTableListSelectionListener implements ListSelectionListener {

	JLabel lblCouponPic;
	JTable tableCouponData;

	public CouponTableListSelectionListener(JTable tableCouponData, JLabel lblCouponPic) {
		this.lblCouponPic = lblCouponPic;
		this.tableCouponData = tableCouponData;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel selectedModel = (ListSelectionModel) e.getSource();
		int selectedRow = selectedModel.getMinSelectionIndex();

		// getting image path
		String ImagePath = (String) tableCouponData.getModel().getValueAt(selectedRow, 8);
		System.out.println(ImagePath);
		
		// getting Image object instance
		File input = new File(ImagePath);
		Image couponImg;
		try {
			couponImg = ImageIO.read(input);

			// converting image to icon
			ImageIcon couponIcon = new ImageIcon(couponImg);

			// loading icon to Jlabel
			lblCouponPic.setIcon(couponIcon);
			lblCouponPic.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			lblCouponPic.revalidate();
			lblCouponPic.repaint();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
