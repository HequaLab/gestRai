package com.hequalab.rai.api.read.views;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractViewWriter<T extends BaseEntity> {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(AbstractViewWriter.class);
	
    private final SessionFactory sessionFactory;

    private final Class<T> type;

    private final String idName;

    public AbstractViewWriter(SessionFactory sessionFactory, Class<T> type, String idName) {
        this.sessionFactory = sessionFactory;
        this.type = type;
        this.idName = idName;
    }

    public Criteria criteria() {
        return session().createCriteria(type);
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
	public T find(Object idValue) {
        return (T) criteria().add(Restrictions.eq(idName, idValue)).uniqueResult();
    }

}