package com.couponproject.gui.Actionlisteners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessegeKeyListener implements KeyListener {
	private JTextArea messegeTxt;

	public MessegeKeyListener(JTextArea messegeTxt) {
		this.messegeTxt = messegeTxt;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if (messegeTxt.getText().length() > 500)
			e.consume();
	}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	

}
