package com.hequalab.rai.api;

import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.sun.jersey.api.model.AbstractResourceMethod;
import com.sun.jersey.spi.container.ResourceMethodDispatchProvider;
import com.sun.jersey.spi.dispatch.RequestDispatcher;

public class ApiResourceMethodDispatchProvider implements
		ResourceMethodDispatchProvider {

	private final ResourceMethodDispatchProvider provider;
	
	private final AggregateSessionFactory sessionFactory;

    private final ApiContextHolder contextHolder;

	public ApiResourceMethodDispatchProvider(
			ResourceMethodDispatchProvider provider,
			AggregateSessionFactory sessionFactory,
			ApiContextHolder contextHolder) {
		this.provider = provider;
		this.sessionFactory = sessionFactory;
		this.contextHolder = contextHolder;
	}

	@Override
	public RequestDispatcher create(AbstractResourceMethod abstractResourceMethod) {
		final RequestDispatcher dispatcher = provider.create(abstractResourceMethod);
		return new ApiRequestDispacher(dispatcher, sessionFactory, contextHolder);
	}

}