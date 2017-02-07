package com.jigsaw.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jigsaw.config.AppConfig;
import com.jigsaw.config.AppConst;
import com.jigsaw.interceptor.ControllerInterceptor;

@Before(ControllerInterceptor.class)
public class UserController extends Controller {

	//@Clear
	@ActionKey("/login")
	public void index(){
		System.out.println("/login");
		
		 renderJson("{\"message\":\"Please input password!\"}");
	}
	
	@ActionKey("/auth")
	public void auth(){
		Cache userCache = Redis.use(AppConst.PLUGIN_REDIS_USER);
		
	}
	
	@ActionKey("logout")
	public void logout(){
		renderJson("{\"result\":true}");
	}
}
