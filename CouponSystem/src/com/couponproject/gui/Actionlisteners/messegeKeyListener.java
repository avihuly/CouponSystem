package com.couponproject.gui.Actionlisteners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class messegeKeyListener implements KeyListener {
	private JTextArea messegeTxt;

	public messegeKeyListener(JTextArea messegeTxt) {
		this.messegeTxt = messegeTxt;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(messegeTxt.getText().length());
		if (messegeTxt.getText().length() > 500)
			e.consume();
	}

}
