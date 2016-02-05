package com.hequalab.rai.api;

import org.hibernate.SessionFactory;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.bazaarvoice.dropwizard.redirect.RedirectBundle;
import com.bazaarvoice.dropwizard.redirect.UriRedirect;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.hequalab.rai.api.auth.AccessTokenDAO;
import com.hequalab.rai.api.auth.SimpleAuthenticator;
import com.hequalab.rai.api.read.views.filiali.FilialiViewWriter;
import com.hequalab.rai.api.read.views.fornitori.FornitoriViewWriter;
import com.hequalab.rai.api.read.views.lotti.LottiViewWriter;
import com.hequalab.rai.api.read.views.luoghi.LuoghiViewWriter;
// #AnchorImport
import com.hequalab.rai.api.read.views.produzioni.ProduzioniViewWriter;
import com.hequalab.rai.api.read.views.richiestanuovoservizio.RichiestaNuovoServizioViewWriter;
import com.hequalab.rai.api.read.views.servizi.ServiziViewWriter;
import com.hequalab.rai.api.read.views.user.UserViewWriter;
import com.hequalab.rai.api.resources.EventStoreRes;
import com.hequalab.rai.api.resources.FilialiRes;
import com.hequalab.rai.api.resources.FornitoriRes;
import com.hequalab.rai.api.resources.LottiRes;
import com.hequalab.rai.api.resources.LuoghiRes;
import com.hequalab.rai.api.resources.OAuth2Resource;
import com.hequalab.rai.api.resources.ProduzioniRes;
import com.hequalab.rai.api.resources.RichiestaNuovoServizioRes;
import com.hequalab.rai.api.resources.ServiziRes;
import com.hequalab.rai.api.resources.UsersRes;
import com.hequalab.rai.api.utility.CheckEmailWithReport;
import com.hequalab.rai.api.utility.MailClientConf;
import com.hequalab.rai.api.utility.ScheduleReportManager;
import com.hequalab.rai.api.write.ApiContext;
import com.hequalab.rai.api.write.eventstore.ApiEventStoreDao;
import com.hequalab.rai.api.write.eventstore.JacksonIdentitySerializer;
import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.hequalab.rai.dddd.DefaultAggregateSessionFactory;
import com.hequalab.rai.dddd.DefaultEventBus;
import com.hequalab.rai.dddd.DefaultEventStore;
import com.hequalab.rai.dddd.EventDispatcher;
import com.hequalab.rai.dddd.EventPublisher;
import com.hequalab.rai.dddd.EventStore;
import com.hequalab.rai.dddd.EventStoreDao;
import com.hequalab.rai.dddd.UUIDIdentity;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.oauth.OAuthProvider;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Api extends Application<ApiConf> {

    static public MailClientConf mailClientConf;

    private final HibernateBundle<ApiConf> hibernate = new ApiHibernateBundle(
	    "com.hequalab.rai");

    public static void main(String[] args) throws Exception {
	new Api().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApiConf> bootstrap) {
	bootstrap
		.addBundle(new AssetsBundle("/META-INF/resources/static/webapp",
			"/webapp/", "index.html", "webapp"));
	bootstrap.addBundle(new RedirectBundle(new UriRedirect("/", "/webapp/"),
		new UriRedirect("/webapp", "/webapp/")));
	bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/files/"));

	bootstrap.addBundle(hibernate);
	SimpleModule module = new SimpleModule();
	module.addSerializer(UUIDIdentity.class,
		new JacksonIdentitySerializer());
	bootstrap.getObjectMapper().registerModule(module);
	bootstrap.getObjectMapper().registerModule(new JodaModule());
	bootstrap.getObjectMapper().configure(
		com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
		false);
    }

    @Override
    public void run(ApiConf conf, Environment env) throws Exception {

	// Scheduler
	CheckEmailWithReport c = new CheckEmailWithReport();
	c.doit();

	// Configurazione client mail letta dal file config Yaml
	MailClientConf mailClientConf = conf.getMailClientConf();

	AccessTokenDAO accessTokenDAO = new AccessTokenDAO();

	// Hibernate session
	SessionFactory hibSessionFactory = hibernate.getSessionFactory();

	// Memory Bus (dispatcher and publisher)
	DefaultEventBus<ApiContext> bus = new DefaultEventBus<ApiContext>();
	EventDispatcher dispatcher = bus;
	EventPublisher<ApiContext> publisher = bus;

	// EventStore
	EventStoreDao<ApiContext> dao = new ApiEventStoreDao(hibSessionFactory);
	EventStore<ApiContext> store = new DefaultEventStore<ApiContext>(dao,
		publisher);

	// Context
	ApiContextHolder contextHolder = new ApiContextHolder();

	/*
	 * View Services (for each view svc)
	 */

	// User
	UserViewWriter userViewSvc = new UserViewWriter(hibSessionFactory);
	dispatcher.subscribe(userViewSvc);

	// Action
		

	// MailSender
//	RichiestaNuovoServizioMailSender richiestaNuovoServizioMailSender = new RichiestaNuovoServizioMailSender(
//		mailClientConf, hibSessionFactory);
//	dispatcher.subscribe(richiestaNuovoServizioMailSender);

	// #AnchorViewWriter
	ProduzioniViewWriter produzioniViewSvc = new ProduzioniViewWriter(
		hibSessionFactory);
	dispatcher.subscribe(produzioniViewSvc);

	LottiViewWriter lottiViewSvc = new LottiViewWriter(hibSessionFactory);
	dispatcher.subscribe(lottiViewSvc);
	LuoghiViewWriter luoghiViewSvc = new LuoghiViewWriter(
		hibSessionFactory);
	dispatcher.subscribe(luoghiViewSvc);
	RichiestaNuovoServizioViewWriter richiestanuovoservizioViewSvc = new RichiestaNuovoServizioViewWriter(
		hibSessionFactory);
	dispatcher.subscribe(richiestanuovoservizioViewSvc);
	ServiziViewWriter serviziViewSvc = new ServiziViewWriter(
		hibSessionFactory);
	dispatcher.subscribe(serviziViewSvc);
	FornitoriViewWriter fornitoriViewSvc = new FornitoriViewWriter(
		hibSessionFactory);
	dispatcher.subscribe(fornitoriViewSvc);
	FilialiViewWriter filialiViewSvc = new FilialiViewWriter(
		hibSessionFactory);
	dispatcher.subscribe(filialiViewSvc);

	// Session Factory
	AggregateSessionFactory sessionFactory = new DefaultAggregateSessionFactory<ApiContext>(
		store, contextHolder);

	// E' qui che setta l'id per tutti gli utenti
	env.jersey().register(new ApiResourceMethodDispatchAdapter(
		sessionFactory, contextHolder));

	
	/*
	 * Resources (for each resource)
	 */

	// User
	env.jersey().register(new UsersRes(sessionFactory, hibSessionFactory));

	// EventStore
	env.jersey()
		.register(new EventStoreRes(sessionFactory, hibSessionFactory));

	// OAuth2
	env.jersey().register(new OAuth2Resource(sessionFactory,
		hibSessionFactory, "password", accessTokenDAO));
	env.jersey().register(new OAuthProvider<>(
		new SimpleAuthenticator(accessTokenDAO), "oauth2-provider"));

	// #AnchorRes
	env.jersey()
		.register(new ProduzioniRes(sessionFactory, hibSessionFactory));

	env.jersey().register(new LottiRes(sessionFactory, hibSessionFactory));

	env.jersey().register(new LuoghiRes(sessionFactory, hibSessionFactory));

	env.jersey().register(new RichiestaNuovoServizioRes(sessionFactory,
		hibSessionFactory));

	env.jersey()
		.register(new ServiziRes(sessionFactory, hibSessionFactory));

	env.jersey()
		.register(new FornitoriRes(sessionFactory, hibSessionFactory));

	env.jersey()
		.register(new FilialiRes(sessionFactory, hibSessionFactory));

	// Schedule manager
	ScheduleReportManager se = new ScheduleReportManager();
	se.startAsync();

	/*
	 * // Prendo le immagini dal PDF try { String sourceDir = "split.pdf";//
	 * Paste pdf files in PDFCopy folder // to read String destinationDir =
	 * "email/test/"; File oldFile = new File(sourceDir); if
	 * (oldFile.exists()) { PDDocument document =
	 * PDDocument.load(sourceDir);
	 * 
	 * List<PDPage> list = document.getDocumentCatalog().getAllPages();
	 * 
	 * String fileName = oldFile.getName().replace(".pdf", "_cover"); int
	 * totalImages = 1; for (PDPage page : list) { PDResources pdResources =
	 * page.getResources();
	 * 
	 * Map pageImages = pdResources.getImages(); if (pageImages != null) {
	 * 
	 * Iterator imageIter = pageImages.keySet().iterator(); while
	 * (imageIter.hasNext()) { String key = (String) imageIter.next();
	 * PDXObjectImage pdxObjectImage = (PDXObjectImage) pageImages
	 * .get(key); pdxObjectImage.write2file(destinationDir + fileName + "_"
	 * + totalImages); totalImages++; } } } } else { System.err.println(
	 * "File not exists"); } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * String prova = new QrDecoder().readQRCode("test.png");
	 * System.out.println("DECODER :" + prova);
	 */

    }

}
