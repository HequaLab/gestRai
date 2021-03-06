package com.hequalab.rai.api.read.views.servizi;

/*
 * Class generated by AppWizard
 */

import org.hibernate.SessionFactory;
import com.hequalab.rai.api.read.views.AbstractViewWriter;
import com.hequalab.rai.api.write.ApiContext;
import com.hequalab.rai.dddd.EventListener;
import com.hequalab.rai.dddd.EventStream;
import com.hequalab.rai.domain.servizi.events.ServiziCreated;
import com.hequalab.rai.domain.servizi.events.ServiziDeleted;
import com.hequalab.rai.domain.servizi.events.ServiziUpdated;



public class ServiziViewWriter extends AbstractViewWriter<ServiziView> {

	public ServiziViewWriter(SessionFactory sessionFactory) {
		super(sessionFactory, ServiziView.class, "serviziId");
	}

	@EventListener
	public void apply(ServiziCreated event, EventStream<ApiContext> stream) {
		ServiziView u = new ServiziView();
		u.setServiziId(event.getId());
		u.setDescrizione(event.getDescrizione());
		u.setlotto(event.getlotto());
		u.setImporto(event.getImporto());
		u.setNote(event.getNote());
		u.setTipologia(event.getTipologia());
		u.setCodice(event.getCodice());
		u.setTipo(event.getTipo());
		session().save(u);
	}

	@EventListener
	public void apply(ServiziUpdated event, EventStream<ApiContext> stream) {
		ServiziView u = find(event.getId());
		u.setDescrizione(event.getDescrizione());
		u.setlotto(event.getlotto());
		u.setImporto(event.getImporto());
		u.setNote(event.getNote());
		u.setTipologia(event.getTipologia());
		u.setCodice(event.getCodice());
		u.setTipo(event.getTipo());
		session().update(u);
	}

	@EventListener
	public void apply(ServiziDeleted event, EventStream<ApiContext> stream) {
		session().delete(find(event.getId()));
	}

}