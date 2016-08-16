package com.couponproject.gui;

import com.couponproject.gui.frames.LoginFrame;
import com.couponproject.util.ResetDataBase;

public class GuiMain {
	public static void main(String[] args) {
		
		// ResetDataBase.execute();
		
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
}
