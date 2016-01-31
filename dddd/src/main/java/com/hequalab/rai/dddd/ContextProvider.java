package com.hequalab.rai.dddd;


public interface ContextProvider<T extends Context> {

	T getCurrent();
	
}