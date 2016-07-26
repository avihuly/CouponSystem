package com.couponproject.test;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveImageFromJfilechooser extends JFrame {

	public static void main(String[] args) {
		SaveImageFromJfilechooser saveImageFromJfilechooser = new SaveImageFromJfilechooser();

		JFileChooser fileChooser = new JFileChooser();
		saveImageFromJfilechooser.add(fileChooser);
		saveImageFromJfilechooser.setBounds(100, 100, 500, 400);
		// saveImageFromJfilechooser.setVisible(true);

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".jpg", "jpg"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".png", "png"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".gif", "gif"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".bmp", "bmp"));

		int result = fileChooser.showOpenDialog(null);

		switch (result) {
		case JFileChooser.CANCEL_OPTION:
			saveImageFromJfilechooser.dispose();
			break;
		case JFileChooser.APPROVE_OPTION:
			ImageIO.write(new , "png", fileChooser.getSelectedFile());

			break;
		}
	}
}
