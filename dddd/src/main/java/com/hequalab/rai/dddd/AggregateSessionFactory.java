package com.hequalab.rai.dddd;


public interface AggregateSessionFactory {

	AggregateSession getCurrent();

	void createSession();

	void closeSession();

}