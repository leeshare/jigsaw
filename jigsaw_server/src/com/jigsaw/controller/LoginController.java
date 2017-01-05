package com.jigsaw.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jigsaw.interceptor.ControllerInterceptor;
import com.jigsaw.interceptor.ActionInterceptor;
import com.jigsaw.model.SiteUser;

@Before(ControllerInterceptor.class)
public class LoginController extends Controller {
	
	//访问路由为 /login
	@Before(ActionInterceptor.class)
	public void index() {
		//renderText("Hello JFinal World.");
		
		//render("login.html");
		
		String result = "renderJson in index";
		renderJson(result);
	}
	
	//访问路由为 /login1
	@ActionKey("/login1")
	public void login(){
		//render("login.html");
		renderText("Hello JFinal World111.");
	}
	
	//访问路由为 /login/login2
	@Clear 
	public void login2(){

		renderText("redisText in login2.");
		
		//getModel(SiteUser.class, "");
		
		//new SiteUser().operateUser();
		new SiteUser().operateRecord();
	}
	
	@Clear(ControllerInterceptor.class)
	@Before(ActionInterceptor.class)
	public void login3(){
		renderText("redisText in login3");
	}
}
