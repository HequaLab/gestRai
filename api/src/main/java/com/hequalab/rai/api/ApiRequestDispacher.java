package com.hequalab.rai.api;

import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.hequalab.rai.domain.user.UserId;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.spi.dispatch.RequestDispatcher;

public class ApiRequestDispacher implements RequestDispatcher {

    private final RequestDispatcher dispatcher;

    private final AggregateSessionFactory sessionFactory;

    private final ApiContextHolder contextHolder;

	public ApiRequestDispacher(RequestDispatcher dispatcher,
			AggregateSessionFactory sessionFactory,
			ApiContextHolder contextHolder) {
		this.dispatcher = dispatcher;
		this.sessionFactory = sessionFactory;
		this.contextHolder = contextHolder;
	}

	
	@Override
	public void dispatch(Object resource, HttpContext context) {
		sessionFactory.createSession();
		
		//TODO: Chiedere a Ernesto come mai usa sempre il solito userId in questo caso
		contextHolder.start(new UserId("7c98887c-213f-4e85-a114-92ea16833190"));
		try {
			dispatcher.dispatch(resource, context);
		} finally {
			sessionFactory.closeSession();			
		}
	}

}