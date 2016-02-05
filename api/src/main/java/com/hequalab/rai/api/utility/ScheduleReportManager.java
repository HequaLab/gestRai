package com.hequalab.rai.api.utility;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;

import org.apache.commons.mail.EmailException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import com.google.common.util.concurrent.AbstractScheduledService;

public class ScheduleReportManager extends AbstractScheduledService {

	final MailReceiverConf mf;
	static MailClientConf mc = null;

	public ScheduleReportManager(MailReceiverConf mf, MailClientConf mc) {
		this.mf = mf;
		ScheduleReportManager.mc = mc;
	}

	@Override
	protected void runOneIteration() throws Exception {
		// Ogni 5 minuti
		System.out.println(" PARTITO SCHEDULER");
		Folder folder = null;
		Store store = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			store = session.getStore("imaps");
			store.connect(mf.getHostName(), mf.getUserName(),
					mf.getPwd());
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
			System.out.println(" CI SONO " + messages.length + " MESSAGGI DA LEGGERE");
			for (int i = 0; i < messages.length; ++i) {
				Message msg = messages[i];

				String from = "nessuno";
				System.out.println("Ricevuto da:" + ((InternetAddress)msg.getFrom()[0]).getAddress());
				from = ((InternetAddress)msg.getFrom()[0]).getAddress();
				
				String subject = msg.getSubject();

				new java.io.File("email/test/").mkdirs();
				String filename = "email/test/" + subject;
				saveParts(from, msg.getContent(), filename);
				msg.setFlag(Flags.Flag.RECENT, true);
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

	@Override
	protected Scheduler scheduler() {
		return Scheduler.newFixedRateSchedule(0, 2, TimeUnit.MINUTES);
	}

	@Override
	protected void startUp() throws MessagingException, IOException {
		// Init

	}

	@Override
	protected void shutDown() {
		// Stop dello schedule
	}

	public static void saveParts(String from, Object content, String filename)
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
						saveParts(from, part.getContent(), filename);
					} else {
						String extension = "";
						if (part.isMimeType("text/html")) {
							extension = "html";
						} else {
							if (part.isMimeType("text/plain")) {
								extension = "txt";
							} else {
								extension = part.getDataHandler().getName();
							}
							filename = filename + "." + extension;

							// Inserirsi qui e prende le immagini allegate.

							// try {
							// out = new FileOutputStream(
							// new java.io.File(filename));
							// } catch (FileNotFoundException e) {
							// // TODO Auto-generated catch block
							// continue;
							// }
							in = part.getInputStream();
							try {
								parsePdf(in, from);
							} catch (Exception e) {
								continue;
							}

							// int k;
							// while ((k = in.read()) != -1) {
							// out.write(k);
							// }

							// Processare il file

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

	// Ricevo l'input stream con il pdf
	static void parsePdf(InputStream in, String from) {
		System.out.println("PARTITO SPLIT PDF");
		PDDocument document;
		try {
			document = PDDocument.load(in);
		} catch (IOException e) {
			return;
		}
		List<PDPage> list = document.getDocumentCatalog().getAllPages();
		List<String> salvati = new ArrayList<String>();

		for (PDPage page : list) {
			PDResources pdResources = page.getResources();

			Map pageImages;
			try {
				pageImages = pdResources.getImages();
			} catch (IOException e) {

				continue;
			}
			if (pageImages != null) {
				Iterator imageIter = pageImages.keySet().iterator();
				while (imageIter.hasNext()) {
					String key = (String) imageIter.next();
					PDXObjectImage pdxObjectImage = (PDXObjectImage) pageImages
							.get(key);
					try {
						OutputStream o;
						BufferedImage img = pdxObjectImage.getRGBImage();

						// Leggo l'id presente nell'immagine
						String qrCode = null;
						try {
							qrCode = new QrDecoder().readQRCode(img);
							if (qrCode == null)
								continue;

							// Salvo il file
							PDDocument doc = new PDDocument();
							document.addPage(page);
							document.save("static_assets/files/report_" + qrCode + "_erogato.pdf");
							document.close();
							salvati.add(qrCode);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							continue;
						}

					} catch (IOException e) {
						continue;
					}

				}
			}
		}

		System.out.println("SALVATI: " + salvati.size());
		if (salvati.size() > 0) {
		
			String messaggio = "Sono stati inseriti i report dei seguenti ticket:<br>";
			for (String s : salvati){
				messaggio += "Caricato report con ticket: " + s + "<br>";
			}
			messaggio += "<br><b>Nota bene: In base alla qualità dei file ricevuti alcuni report potrebbero non essere riconosciuti</b>.";

			try {
				System.out.println("Inizio..");
				System.out.println("Rispondo a:" + from);
				ClientMail cf = new ClientMail(mc);
				
				cf.sendEmail(from, "Ricevuta email", messaggio);
			} catch (EmailException e) {
				System.out.println("... errore email");
				e.printStackTrace();
			}

		}

	}

}
