package com.couponproject.gui.frames;

import java.awt.*;
import javax.swing.*;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.Actionlisteners.AllCouponsActionListener;
import com.couponproject.gui.Actionlisteners.CouponTableListSelectionListener;
import com.couponproject.gui.Actionlisteners.PurchasedCouponsActionListener;
import com.couponproject.gui.frames.helpers.TemplateFrame;

public class CustomerMainFrame extends TemplateFrame {
	private JTable tableCouponData = new JTable();

	public CustomerMainFrame(CustomerFacade customerFacade) {
		// frame properties
		super("Coupons (logged as customer)",750,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// ***********
		// South Panel
		// ***********
		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		// ***********
		// East Panel
		// ***********
		JPanel eastPanel = new JPanel();
		getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// ***********
		// Center Panel
		// ***********
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new BorderLayout());

		JLabel lblCouponPic = new JLabel("");
		CenterPanel.add(lblCouponPic, BorderLayout.SOUTH);

		JScrollPane sp = new JScrollPane(tableCouponData);
		sp.setBorder(BorderFactory.createEmptyBorder());
		tableCouponData.setShowGrid(false);
		tableCouponData.setShowHorizontalLines(true);

		CenterPanel.add(sp, BorderLayout.CENTER);

		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);

		// make it all fit
		CenterPanel.setPreferredSize(new Dimension(750, 500));
		pack();

		// *************************
		// Buttons & ActionListeners
		// *************************
		setCustomerHomeBntLayout(tableCouponData, westPanel, customerFacade);

		// Coupon Table ActionListeners
		// ----------------------------
		ListSelectionModel selectedModel = tableCouponData.getSelectionModel();
		selectedModel.addListSelectionListener(new CouponTableListSelectionListener(tableCouponData, lblCouponPic));
	}
	// *************
	// end of frame
	// *************

	// setCustomerHomeBntLayout
	public static void setCustomerHomeBntLayout(JTable tableCouponData, JPanel Panel, CustomerFacade customerFacade) {
		// Buttons ActionListeners
		Panel.removeAll(); // clear panel
		Panel.setLayout(new GridLayout(10, 1, 0, 0));

		// All Purchased Coupons
		JButton btnMyCoupons = new JButton("All My Coupons");
		btnMyCoupons.addActionListener(new PurchasedCouponsActionListener(tableCouponData, Panel, customerFacade));

		JButton bntBrowseCoupons = new JButton("Browse Coupons");
		bntBrowseCoupons.addActionListener(new AllCouponsActionListener(tableCouponData, Panel, customerFacade));

		// Adding buttons to westPanel
		Panel.add(btnMyCoupons);
		Panel.add(bntBrowseCoupons);

		// revalidate & repaint
		Panel.revalidate();
		Panel.repaint();
	}
}
