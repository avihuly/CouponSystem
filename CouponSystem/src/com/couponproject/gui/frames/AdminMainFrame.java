package com.couponproject.gui.frames;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.couponproject.dbdao.CouponDBDAO;
import com.couponproject.exception.CouponSystemException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.facade.CustomerFacade;
import com.couponproject.gui.CouponByPriceSliders;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.CouponTableListSelectionListener;

public class AdminMainFrame extends JFrame {

	public AdminMainFrame(AdminFacade adminFacade) {
		// frame properties
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		// set layout
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 750, 75);

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

		// ***********
		// Center Panel
		// ***********
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);

		// ***********
		// West Panel
		// ***********
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
                     		
		// *************************
		// Buttons & ActionListeners
		// *************************

	}
}
