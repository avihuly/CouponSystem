package com.couponproject.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;


public class ImageRetter {
	
	public static String saveImageBase64(String name, String base64) {

		String[] parts = base64.split(",");
		String imageString = parts[1];
		
		byte[] data = Base64.getDecoder().decode(imageString);

		OutputStream stream = null;
		String filePath = "resources/img/CouponPics/" + name + ".png";
		
		try {
			stream = new FileOutputStream("WebContent/" + filePath);
			stream.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filePath;
	}
}
