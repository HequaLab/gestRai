package com.hequalab.rai.api.utility;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;

public class CheckEmailWithReport {

    public CheckEmailWithReport() {
    }

    public static void doit() throws MessagingException, IOException {
	Folder folder = null;
	Store store = null;
	try {
	    Properties props = System.getProperties();
	    props.setProperty("mail.store.protocol", "imaps");

	    Session session = Session.getDefaultInstance(props, null);
	    // session.setDebug(true);
	    store = session.getStore("imaps");
	    store.connect("imap.gmail.com", "klitonfx@gmail.com",
		    "pushedx1993");
	    folder = store.getFolder("Inbox");

	    /*
	     * Others GMail folders : [Gmail]/All Mail This folder contains all
	     * of your Gmail messages. [Gmail]/Drafts Your drafts. [Gmail]/Sent
	     * Mail Messages you sent to other people. [Gmail]/Spam Messages
	     * marked as spam. [Gmail]/Starred Starred messages. [Gmail]/Trash
	     * Messages deleted from Gmail.
	     */

	    folder.open(Folder.READ_WRITE);
	    Message messages[] = folder
		    .search(new FlagTerm(new Flags(Flag.SEEN), false));

	    System.out.println(
		    "Ci sono " + messages.length + " email non lette.");
	    for (int i = 0; i < messages.length; ++i) {
		System.out.println("MESSAGE #" + (i + 1) + ":");
		Message msg = messages[i];

		String from = "unknown";
		if (msg.getReplyTo().length >= 1) {
		    from = msg.getReplyTo()[0].toString();
		} else if (msg.getFrom().length >= 1) {
		    from = msg.getFrom()[0].toString();
		}

		String subject = msg.getSubject();
		System.out.println("Saving ... " + subject + " " + from);

		// you may want to replace the spaces with "_"
		// the TEMP directory is used to store the files
		new java.io.File("email/test/").mkdirs();
		String filename = "email/test/" + subject;
		saveParts(msg.getContent(), filename);
		msg.setFlag(Flags.Flag.SEEN, true);
		// to delete the message
		// msg.setFlag(Flags.Flag.DELETED, true);
	    }
	} finally {
	    if (folder != null) {
		folder.close(true);
	    }
	    if (store != null) {
		store.close();
	    }
	}
    }

    public static void saveParts(Object content, String filename)
	    throws MessagingException, IOException {
	OutputStream out = null;
	InputStream in = null;
	try {
	    if (content instanceof Multipart) {
		Multipart multi = ((Multipart) content);
		int parts = multi.getCount();
		for (int j = 0; j < parts; ++j) {
		    MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);
		    if (part.getContent() instanceof Multipart) {
			// part-within-a-part, do some recursion...
			saveParts(part.getContent(), filename);
		    } else {
			String extension = "";
			if (part.isMimeType("text/html")) {
			    extension = "html";
			} else {
			    if (part.isMimeType("text/plain")) {
				extension = "txt";
			    } else {
				// Try to get the name of the attachment
				extension = part.getDataHandler().getName();
			    }
			    filename = filename + "." + extension;
			    System.out.println("... " + filename);

			    // Inserirsi qui e prende le immagini allegate.

			    try {
				out = new FileOutputStream(
					new java.io.File(filename));
			    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				continue;
			    }
			    in = part.getInputStream();
			    int k;
			    while ((k = in.read()) != -1) {
				out.write(k);
			    }
			}
		    }
		}
	    }
	} finally {
	    if (in != null) {
		in.close();
	    }
	    if (out != null) {
		out.flush();
		out.close();
	    }
	}
    }

    public static void main(String args[]) throws Exception {
	CheckEmailWithReport.doit();
    }
}