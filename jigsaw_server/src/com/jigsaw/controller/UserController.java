package com.jigsaw.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jigsaw.config.AppConfig;
import com.jigsaw.config.AppConst;
import com.jigsaw.interceptor.ControllerInterceptor;
import com.jigsaw.model.UserContextList;

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
		if(!UserContextList.IsAuthenticated()){
			renderJson("{\"message\":\"无效认证!\"}");
		}else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date now = new Date();
			renderJson("{\"message\":\"认证有效\", \"current_time\":\"" + formatter.format(now) + "\"}");
		}
		
	}
	
	@ActionKey("logout")
	public void logout(){
		renderJson("{\"result\":true}");
	}
}
