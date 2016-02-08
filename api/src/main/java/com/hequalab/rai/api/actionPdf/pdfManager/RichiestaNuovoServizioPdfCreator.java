package com.hequalab.rai.api.actionPdf.pdfManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.hibernate.SessionFactory;

import com.hequalab.rai.api.read.views.richiestanuovoservizio.RichiestaNuovoServizioView;
import com.hequalab.rai.dddd.EventListener;
import com.hequalab.rai.domain.richiestanuovoservizio.events.RichiestaServizioApprovata;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RichiestaNuovoServizioPdfCreator {

	private final SessionFactory sessionFactory;

	public RichiestaNuovoServizioPdfCreator(
			SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	@SuppressWarnings("unchecked")
	@EventListener
	public void apply(RichiestaServizioApprovata event) throws JRException, IOException {

		// Genero il report
		RichiestaNuovoServizioView uv = (RichiestaNuovoServizioView) sessionFactory.getCurrentSession()
				.createQuery(
						"from RichiestaNuovoServizioView where richiestanuovoservizioId = :id")
				.setParameter("id", event.getId()).uniqueResult();

		List<RichiestaNuovoServizioView> data1 = new ArrayList<RichiestaNuovoServizioView>();
		data1.add(uv);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				data1);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("report_richiesta.jrxml");

		@SuppressWarnings("rawtypes")
		Map parameters = new HashMap();

		parameters.put("logo",
				ImageIO.read(getClass().getResource("RAI_logo.png")));
		parameters.put("idRichiesta", event.getId().toString());

		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperDesign);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, beanColDataSource);

		String nomeFile = "report_" + event.getId().toString() + "_approvato.pdf";
		new java.io.File("static_assets/files/").mkdirs();
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"static_assets/files/" + nomeFile);

	}

}
