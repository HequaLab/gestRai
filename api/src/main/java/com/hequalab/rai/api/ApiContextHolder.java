package com.hequalab.rai.api;

import com.hequalab.rai.api.write.ApiContext;
import com.hequalab.rai.dddd.ContextProvider;
import com.hequalab.rai.domain.user.UserId;


public class ApiContextHolder implements ContextProvider<ApiContext> {

	private static ThreadLocal<ApiContext> current = new ThreadLocal<ApiContext>();

	@Override
	public ApiContext getCurrent() {
		return current.get();
	}
	
	public void start(UserId userId) {
		current.set(new ApiContext(userId));
	}

}