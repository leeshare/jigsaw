package com.jigsaw.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jigsaw.model.SiteUser;

public class LoginController extends Controller {

	//访问路由为 /login
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
	public void login2(){

		renderText("redisText in login2.");
		
		getModel(SiteUser.class, "");
	}
}
