package com.hequalab.rai.api.utility;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;
import com.google.zxing.qrcode.QRCodeReader;

public class QrDecoder {

    public static String readQRCode(InputStream in)
	    throws FileNotFoundException, IOException, NotFoundException, ChecksumException, FormatException {
	BinaryBitmap binaryBitmap = new BinaryBitmap(
		new HybridBinarizer(new BufferedImageLuminanceSource(
			ImageIO.read(in))));
	Result[] qrCodeResult =new QRCodeMultiReader().decodeMultiple(binaryBitmap);
	
	String s = "";
	for (Result s1:qrCodeResult){
	    s += s1.getText(); 
	}
	return s;
    }
    
    public static String readQRCode(BufferedImage qrcodeImage) throws Exception{
    	//Die Parameter anlegen
    	Hashtable<DecodeHintType, Object> hintMap = new Hashtable<DecodeHintType, Object>();
           hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
           
    	//Bild zu BinaryBitmap verwandeln
    	BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(qrcodeImage);
    	BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

    	//QR Leser initialisieren...
    	QRCodeReader reader = new QRCodeReader();
    	Result result;
    	//...und lesen:
    	result = reader.decode(bitmap,hintMap);
    	
    	return result.getText();
    }
     

}
