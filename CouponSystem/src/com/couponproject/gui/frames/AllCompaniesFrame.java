package com.couponproject.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.couponproject.exception.AdminFacadeException;
import com.couponproject.facade.AdminFacade;
import com.couponproject.gui.GuiUtil;
import com.couponproject.gui.Actionlisteners.RemoveCompanyActionListener;

public class AllCompaniesFrame extends JFrame {
	// **********
	// Attributes
	// **********
	JTable CompaniesTable = new JTable();

	// ***********
	// constructor
	// ***********
	public AllCompaniesFrame(AdminFacade adminFacade) {
		// frame properties
		super("Admin - All Companies");
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 750, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		// set layout
		getContentPane().setLayout(new BorderLayout());
		// Set Frame's Icon And MenuBar
		GuiUtil.setFrameIconAndMenu(this);

		// ***********
		// North Panel
		// ***********
		GuiUtil.setLogoBySize(this, 750, 75);

		// **********************
		// West Panel - bnt panel
		// **********************
		JPanel westPanel = new JPanel();
		getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridLayout(10, 1, 0, 0));

		JButton bntRemoveCompany = new JButton("Remove company");
		westPanel.add(bntRemoveCompany);

		JButton bntUpdateCompany = new JButton("Update company");
		westPanel.add(bntUpdateCompany);

		// **************************
		// Center Panel - Table panel
		// **************************
		JPanel CenterPanel = new JPanel();
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new BorderLayout());

		JScrollPane sp = new JScrollPane(CompaniesTable);
		CenterPanel.add(sp, BorderLayout.CENTER);
		try {
			GuiUtil.CompaniesToTable(CompaniesTable, adminFacade.getAllCompanies());
		} catch (AdminFacadeException e) {
			// TODO:
		}

		// make it all fit
		pack();

		// ***************
		// ActionListeners
		// ***************
		// RemoveCompany
		bntRemoveCompany.addActionListener(new RemoveCompanyActionListener(adminFacade, CompaniesTable));

		// UpdateCompany
		bntUpdateCompany.addActionListener(e -> {
			UpDateCompanyFrame companyInfoFrame = new UpDateCompanyFrame(adminFacade, CompaniesTable);
			companyInfoFrame.setVisible(true);
		});

	}
}
