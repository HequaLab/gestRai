package com.hequalab.rai.api.actionMail.mail.richiestanuovoservizio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.mail.EmailException;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

import com.hequalab.rai.api.read.views.mailToken.MailTokenView;
import com.hequalab.rai.api.read.views.user.UserView;
import com.hequalab.rai.api.utility.ClientMail;
import com.hequalab.rai.api.utility.MailClientConf;
import com.hequalab.rai.dddd.EventListener;
import com.hequalab.rai.domain.richiestanuovoservizio.events.RichiestaNuovoServizioCreated;

import io.dropwizard.hibernate.UnitOfWork;

public class RichiestaNuovoServizioMailSender {

	private final SessionFactory sessionFactory;
	private final MailClientConf mailClientConf;

	public RichiestaNuovoServizioMailSender(MailClientConf mf,
			SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.mailClientConf = mf;
	}

	@UnitOfWork
	@EventListener
	public void apply(RichiestaNuovoServizioCreated event) throws EmailException {

	

				// Creo il token di accesso per l'email e invio l'email ai
				// MANAGER
				// dell'utente e agli amministratori
				@SuppressWarnings("unused")
				List<MailTokenView> newToken = new ArrayList<MailTokenView>();
				UserView user = (UserView) sessionFactory.getCurrentSession()
						.createQuery("from UserView where userId = :id")
						.setParameter("id", event.getUser()).uniqueResult();

				String[] superiori = user.getSuperiore().split(",");

				List<String> amministratori = mailClientConf.getMailAmministratori();

				// Invio la mail agli amministratori
				for (String s : amministratori) {
					ClientMail cMail;
					try {
						cMail = new ClientMail(mailClientConf);
					} catch (EmailException e) {
						// TODO Auto-generated catch block
						continue;
					}
					try {
						String testo = "E' stato richiesto un nuovo servizio: <br> <br>"
								+ "Lotto: " + event.getLotto() + "<br>"
								+ "Servizio: <b>" + event.getNome() + "</b><br>"
								+ "Voce: " + event.getVoce() + "<br>"
								+ "Produzione: <b>" + event.getProduzione() + "</b><br>"
								+ "Matricola: <b></b>" + event.getMatricola()
								+ "</b><br>" + "Data: <b>" + event.getData()
								+ "</b><br>" + "Data fine: <b>" + event.getDataFine()
								+ "</b><br>" + "Ora: <b>" + event.getOra() + "</b><br>"
								+ "Ore/Unit&#225: <b>" + event.getOre() + "</b><br>"
								+ "Uorg: <b>" + event.getUorg() + "</b><br>"
								+ "Luogo: <b>" + event.getLuogo() + "</b><br>"
								+ "Utente richiedente: <b>" + event.getOperatore()
								+ "</b><br>Note: <b>" + event.getNote();

						cMail.sendEmail(s, "Richiesto nuovo servizio", testo);
					} catch (EmailException e1) {
						e1.printStackTrace();
						continue;
					}
				}

				for (String s : superiori) {
					UserView superiore = (UserView) sessionFactory.getCurrentSession()
							.createQuery("from UserView where userName = :userName")
							.setParameter("userName", s).uniqueResult();
					if (superiore == null)
						continue;

					MailTokenView e = new MailTokenView();
					e.setUser(superiore.getUserId());
					e.setExpiration(new DateTime(
							DateTime.now().getMillis() + (48 * 3600 * 1000))
									.toLocalDateTime());
					e.setAccessToken(UUID.randomUUID().toString());
					sessionFactory.getCurrentSession().save(e);

					// Invio l'email al Manager
					String mail = superiore.getEmail();
					if (mail == null)
						continue;

					String styleAccept = "border:1px solid #666; cursor:pointer;display:block;margin:10px auto 0;padding:5px;text-align:center; text-decoration:none;width:220px;-moz-border-radius:15px;-webkit-border-radius:15px;background:#20c167;background-image:-moz-linear-gradient(top, #20c167, #157f44);background-image:-o-linear-gradient(top, #20c167, #157f44);background-image:-webkit-gradient(linear, center top, center bottom,from(#20c167), to(#157f44));background-image:linear-gradient(top, #20c167, #157f44);border-radius:15px;color:#FFF;font-size:.75em;font-weight:bold;text-shadow:-1px -1px 0 #444;";
					String styleDecline = "border:1px solid #666; cursor:pointer;display:block;margin:10px auto 0;padding:5px;text-align:center; text-decoration:none;width:220px;-moz-border-radius:15px;-webkit-border-radius:15px;background:#D32F2F;background-image:-moz-linear-gradient(top,#E53935, #C62828);background-image:-o-linear-gradient(top,#E53935, #C62828);background-image:-webkit-gradient(linear, center top, center bottom,from(#E53935), to(#C62828));background-image:linear-gradient(top, #E53935, #C62828);border-radius:15px;color:#FFF;font-size:.75em;font-weight:bold;text-shadow:-1px -1px 0 #444;";

					ClientMail cMail;
					try {
						cMail = new ClientMail(mailClientConf);
					} catch (EmailException e2) {
						// TODO Auto-generated catch block
						continue;
					}

					try {
						String testo = "E' stato richiesto un nuovo servizio: <br> <br>"
								+ "Lotto: " + event.getLotto() + "<br>"
								+ "Servizio: <b>" + event.getNome() + "</b><br>"
								+ "Voce: " + event.getVoce() + "<br>"
								+ "Produzione: <b>" + event.getProduzione() + "</b><br>"
								+ "Matricola: <b></b>" + event.getMatricola()
								+ "</b><br>" + "Data: <b>" + event.getData()
								+ "</b><br>" + "Data fine: <b>" + event.getDataFine()
								+ "</b><br>" + "Ora: <b>" + event.getOra() + "</b><br>"
								+ "Ore/Unit&#225: <b>" + event.getOre() + "</b><br>"
								+ "Uorg: <b>" + event.getUorg() + "</b><br>"
								+ "Luogo: <b>" + event.getLuogo() + "</b><br>"
								+ "Utente richiedente: <b>" + event.getOperatore()
								+ "</b><br>Note: <b>" + event.getNote() + "</b><br>"
								+ "<br><br> <a style=\"" + styleAccept
								+ "\" type=\"Bytton\" href=\"" + event.getBaseUri()
								+ "richiestaNuovoServizio/approvaRichiesta/"
								+ event.getId().toString() + "/" + e.getAccessToken()
								+ "\">Approva richiesta</a>" + "<br> <a style=\""
								+ styleDecline + "\" type=\"Bytton\" href=\""
								+ event.getBaseUri()
								+ "richiestaNuovoServizio/rifiutaRichiesta/"
								+ event.getId().toString() + "/" + e.getAccessToken()
								+ "\">Rifiuta richiesta</a>";

						cMail.sendEmail(mail, "Richiesto nuovo servizio", testo);
					} catch (EmailException e1) {

						continue;
					}
				} // fine codice normale

			

	} // func

	public MailClientConf getMailClientConf() {
		return mailClientConf;
	}

}
