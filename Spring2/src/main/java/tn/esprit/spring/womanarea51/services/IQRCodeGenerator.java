package tn.esprit.spring.womanarea51.services;

import java.io.IOException;

import com.google.zxing.WriterException;

public interface IQRCodeGenerator {
	
	public String generateQRCodeImage (String text, int width, int height, String filePath) throws WriterException, IOException;

}
