package com.jigsaw.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class LoginController extends Controller {

	//����·��Ϊ /login
	public void index() {
		//renderText("Hello JFinal World.");
		
		//render("login.html");
		
		String result = "hello lalalalala";
		renderJson(result);
	}
	
	//����·��Ϊ /login1
	@ActionKey("/login1")
	public void login(){
		//render("login.html");
		renderText("Hello JFinal World111.");
	}
	
	//����·��Ϊ  /login/login2
	public void login2(){

		renderText("Hello JFinal World22222.");
	}
}
