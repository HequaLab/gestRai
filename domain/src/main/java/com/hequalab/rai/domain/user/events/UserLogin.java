package com.hequalab.rai.domain.user.events;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.user.UserId;

public class UserLogin extends DefaultEvent<UserId>{

	private String ip;
	
}
