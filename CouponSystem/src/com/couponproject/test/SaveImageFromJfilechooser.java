package com.couponproject.test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveImageFromJfilechooser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SaveImageFromJfilechooser saveImageFromJfilechooser = new SaveImageFromJfilechooser();

		JFileChooser fileChooser = new JFileChooser();
		saveImageFromJfilechooser.add(fileChooser);
		saveImageFromJfilechooser.setBounds(100, 100, 500, 400);
		// saveImageFromJfilechooser.setVisible(true);

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images     (jpg/png/gif/bmp)", "jpg", "png", "gif", "bmp"));

		int result = fileChooser.showOpenDialog(null);

		switch (result) {
		case JFileChooser.CANCEL_OPTION:
			saveImageFromJfilechooser.dispose();
			break;
		case JFileChooser.APPROVE_OPTION:
			String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
			String imageName = fileChooser.getSelectedFile().getName();
			FileInputStream in;
			try {
				in = new FileInputStream(imagePath);

				String targetPath = "image/CouponPics/" + imageName;
				System.out.println(targetPath);
				FileOutputStream out;

				out = new FileOutputStream(targetPath);

				int bytesRead = 0;
				byte[] bucket = new byte[256];

				// Use the bucket to move information from src to dests
				while ((bytesRead = in.read(bucket)) > -1) {
					out.write(bucket, 0, bytesRead);
				}
				out.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			break;
		}
	}
}