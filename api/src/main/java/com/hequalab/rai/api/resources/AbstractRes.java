package com.hequalab.rai.api.resources;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import com.hequalab.rai.api.dtos.Page;
import com.hequalab.rai.api.dtos.PageEvent;
import com.hequalab.rai.api.read.views.BaseEntity;
import com.hequalab.rai.dddd.AggregateSession;
import com.hequalab.rai.dddd.AggregateSessionFactory;

public class AbstractRes {

    protected final SessionFactory sessionFactory;

    protected final AggregateSessionFactory aggregateSessionFactory;

    public AbstractRes(AggregateSessionFactory aggregateSessionFactory, SessionFactory sessionFactory) {
        this.aggregateSessionFactory= aggregateSessionFactory;
    	this.sessionFactory = sessionFactory;
    }

    public Session hibSess() {
    	return sessionFactory.getCurrentSession();
    }

    public AggregateSession aggSess() {
    	return aggregateSessionFactory.getCurrent();
    }
    
    @SuppressWarnings("unchecked")
	public static <T extends BaseEntity> Page<T> page(Criteria criteria, int page, int size) {
        long totalItems = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        criteria.setProjection(null).setResultTransformer(Criteria.ROOT_ENTITY);    	
        criteria.setMaxResults(size);
        criteria.setFirstResult((page - 1)	 * size);
		return new Page<T>(size, totalItems, (List<T>) criteria.list());
	}

	public static <T extends BaseEntity> Page<T> page(List<T> objects, int page, int size) {
        long totalItems = objects.size();
		return new Page<T>(size, totalItems, (List<T>) objects);
	}
	
	public static <T> PageEvent<T> pageEvent(List<T> objects, int page, int size) {
        long totalItems = objects.size();
		return new PageEvent<T>(size, totalItems, (List<T>) objects);
	}

}