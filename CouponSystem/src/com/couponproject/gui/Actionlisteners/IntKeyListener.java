package com.couponproject.gui.Actionlisteners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IntKeyListener implements KeyListener {

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
		char c = e.getKeyChar();
		if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_DELETE))) {
			e.consume();
		}
	}

}
