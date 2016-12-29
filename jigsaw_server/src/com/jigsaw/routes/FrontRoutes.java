package com.jigsaw.routes;

import com.jfinal.config.Routes;
import com.jigsaw.controller.LoginController;

public class FrontRoutes extends Routes {

	public void config(){
		add("/login", LoginController.class);
	}
}
