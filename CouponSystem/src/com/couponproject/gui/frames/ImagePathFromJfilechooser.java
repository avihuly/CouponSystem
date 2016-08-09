package com.couponproject.gui.frames;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagePathFromJfilechooser extends JFrame {

	private static final long serialVersionUID = 1L;

	public ImagePathFromJfilechooser(JLabel imagePath, JLabel targetPath) {
		super();

		JFileChooser fileChooser = new JFileChooser();
		add(fileChooser);
		setBounds(100, 100, 500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(
				new FileNameExtensionFilter("Images     (jpg/ png/ gif/ bmp)", "jpg", "png", "gif", "bmp"));

		int result = fileChooser.showOpenDialog(null);
		switch (result) {
		case JFileChooser.CANCEL_OPTION:
			dispose();
			break;
		case JFileChooser.APPROVE_OPTION:
			imagePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
			targetPath.setText("image/CouponPics/" + fileChooser.getSelectedFile().getName());
			break;
		}
	}
}
