package com.hequalab.rai.api;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;

import javax.persistence.Entity;

import org.hibernate.cfg.Configuration;
import org.hibernate.usertype.UserType;

import com.hequalab.rai.api.reflection.ReflectionUtils;

public class ApiHibernateBundle extends HibernateBundle<ApiConf> {

	private final String pckg;
	
    @Override
    public DataSourceFactory getDataSourceFactory(ApiConf configuration) {
        return configuration.getDataSourceFactory();
    }

	protected ApiHibernateBundle(String pckg) {
		this(pckg, new SessionFactoryFactory());
	}

	protected ApiHibernateBundle(String pckg, SessionFactoryFactory sessionFactoryFactory) {
		super(ReflectionUtils.findClassesAnnotatatedBy(pckg, Entity.class), sessionFactoryFactory);
		this.pckg = pckg;
	}

    @Override
	protected void configure(Configuration configuration) {
		for (Class<?> type : ReflectionUtils.findClassesByBaseType(pckg, UserType.class)) {
			try {
				UserType userType = (UserType)type.newInstance();
				configuration.registerTypeOverride(userType, new String[] {userType.getClass().getSimpleName()});
			} catch (InstantiationException | IllegalAccessException e) {
				throw new RuntimeException(e);
			};
		}
		super.configure(configuration);
	}

}