package com.couponproject.gui.frames.helpers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.couponproject.gui.Actionlisteners.AboutActionListener;


public class TemplateFrame extends JFrame {
	// ***********
	// constructor
	// ***********
	public TemplateFrame(String Title, int width, int height) {
		// frame properties
		super(Title);
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, width, height);
		// Set Layout
		getContentPane().setLayout(new BorderLayout(0, 0));

		// --------
		// Icon set
		// --------
		try {
			// getting image refrains
			File input = new File("image/frameIcon.png");
			Image frameImg = ImageIO.read(input);
			// loading icon to frame
			setIconImage(frameImg);
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
		}

		// -------
		// MenuBar
		// -------
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// MenuItem - About
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		// About Icon - "image/about.png"
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new AboutActionListener());
		try {
			// getting image refrains
			File input = new File("image/about.png");
			Image aboutImg = ImageIO.read(input);
			// resizing image to fit panel
			Image aboutImgResized = aboutImg.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			// converting image to icon
			ImageIcon aboutIcon = new ImageIcon(aboutImgResized);
			// loading icon to menu item
			mntmAbout.setIcon(aboutIcon);
		} catch (IOException ie) {
		}
		mnHelp.add(mntmAbout);

		// -----------
		// North Panel
		// -----------
		JPanel northPanel = new JPanel();
		Image logoImg;
		try {
			// getting image refrains
			File input = new File("image/couponLogo.jpg");
			logoImg = ImageIO.read(input);
			// resizing image to fit panel
			Image logoImgResized = logoImg.getScaledInstance((int)(width*0.95), (int)(height*0.15), Image.SCALE_SMOOTH);
			// converting image to icon
			ImageIcon logoIcon = new ImageIcon(logoImgResized);
			// loading icon to Jlabel
			JLabel logoLabel = new JLabel("", logoIcon, JLabel.CENTER);
			// loading Jlabel to panel
			northPanel.add(logoLabel);
			// adding to frame's BorderLayout.NORTH
			getContentPane().add(northPanel, BorderLayout.NORTH);
		} catch (IOException ie) {}
	}
}
