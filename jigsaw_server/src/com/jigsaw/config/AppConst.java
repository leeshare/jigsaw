package com.jigsaw.config;

public class AppConst {

	//redis插件名 plugin_redis_user
	public static final String PLUGIN_REDIS_USER = "userRedis";
	
	public static final String REDIS_KEY_TICKET = "ticket:";

	public static final String REDIS_KEY_SIGNIN = "signin:";
	
	public static final String REDIS_KEY_CURRENTUSER = "currentuser:";

	
	public static final int LOGIN_FAIL_PWD_MAX_TIME = 5;//默认用户尝试登录次数5次
	public static final int LOGIN_FAIL_PWD_LOCK_PERIOD = 30;//默认用户锁周期：30分钟;
}
