package com.hequalab.rai.api.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrDecoder {

    public static String readQRCode(String filePath)
	    throws FileNotFoundException, IOException, NotFoundException, ChecksumException, FormatException {
	BinaryBitmap binaryBitmap = new BinaryBitmap(
		new HybridBinarizer(new BufferedImageLuminanceSource(
			ImageIO.read(new FileInputStream(filePath)))));
	Result[] qrCodeResult =new QRCodeMultiReader().decodeMultiple(binaryBitmap);
	
	String s = "";
	for (Result s1:qrCodeResult){
	    s += s1.getText(); 
	}
	return s;
    }

}
