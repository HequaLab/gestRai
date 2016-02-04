package com.hequalab.rai.api.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import com.hequalab.rai.dddd.UUIDIdentity;

public class File extends UUIDIdentity {

    private static final long serialVersionUID = 1L;

    private String URL = null;

    public File(String fileEncoded, String fileName) {
	super();
	this.getUuid().toString();
	String contenuto = fileEncoded;

	// TODO: Aggiungere un eventuale controllo sul tipo di file
	try {
	    // this.URL = this.getUuid().toString() + "_" + fileName;
	    this.URL = fileName;
	    byte[] byteArray = this.decode(contenuto);
	    new java.io.File("static_assets/files/").mkdirs();
	    FileOutputStream imageOutFile = new FileOutputStream(
		    "static_assets/files/" + this.URL);
	    imageOutFile.write(byteArray);
	    imageOutFile.close();

	} catch (FileNotFoundException e) {
	    System.out.println("File::File not found" + e);
	    this.URL = null;
	} catch (IOException ioe) {
	    System.out.println("File::Exception while reading the file" + ioe);
	    this.URL = null;
	}
    }

    public String getUrl() {
	return this.URL == null ? null : "../files/" + this.URL;
    }

    
    public byte[] decode(String data) {
	return Base64.decodeBase64(data);
    }
    


}