package com.hequalab.rai.dddd;

public interface EventPublisher<C extends Context> {

	EventStream<C> publish(EventStream<C> stream);
	
}