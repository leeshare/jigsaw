package com.jigsaw.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class UserController extends Controller {

	@ActionKey("/login")
	public void index(){
		
		
		 renderJson("{\"message\":\"Please input password!\"}");
	}
	
	@ActionKey("/auth")
	public void auth(){
		
	}
	
	@ActionKey("logout")
	public void logout(){
		
	}
}
