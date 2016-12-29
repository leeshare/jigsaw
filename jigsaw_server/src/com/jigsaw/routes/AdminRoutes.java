package com.jigsaw.routes;

import com.jfinal.config.Routes;
import com.jigsaw.controller.AdminController;
import com.jigsaw.controller.UserController;

public class AdminRoutes extends Routes {

	public void config(){
		add("/admin", AdminController.class);
		add("/admin/user", UserController.class);
	}
	
}
