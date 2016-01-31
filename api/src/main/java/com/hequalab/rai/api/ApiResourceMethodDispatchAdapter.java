package com.hequalab.rai.api;

import javax.ws.rs.ext.Provider;

import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.sun.jersey.spi.container.ResourceMethodDispatchAdapter;
import com.sun.jersey.spi.container.ResourceMethodDispatchProvider;

@Provider
public class ApiResourceMethodDispatchAdapter  implements ResourceMethodDispatchAdapter {

	private final AggregateSessionFactory sessionFactory;
	
    private final ApiContextHolder contextHolder;

    
	public ApiResourceMethodDispatchAdapter(
			AggregateSessionFactory sessionFactory,
			ApiContextHolder contextHolder) {
		this.sessionFactory = sessionFactory;
		this.contextHolder = contextHolder;
	}


	@Override
	public ResourceMethodDispatchProvider adapt(
			ResourceMethodDispatchProvider provider) {
		return new ApiResourceMethodDispatchProvider(provider, sessionFactory, contextHolder);
	}

}