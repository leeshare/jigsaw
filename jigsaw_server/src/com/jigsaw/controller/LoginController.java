package com.jigsaw.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class LoginController extends Controller {

	public void index() {
		//renderText("Hello JFinal World.");
		
		//render("login.html");
		
		String result = "hello lalalalala";
		renderJson(result);
	}
	
//	@ActionKey("/login")
//	public void login(){
//		render("login.html");
//	}
}
