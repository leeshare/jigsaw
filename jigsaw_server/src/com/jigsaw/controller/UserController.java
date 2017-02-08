package com.jigsaw.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.server.handler.ContextHandler;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jigsaw.config.AppConfig;
import com.jigsaw.config.AppConst;
import com.jigsaw.interceptor.ControllerInterceptor;
import com.jigsaw.model.PassportSigninInfo;
import com.jigsaw.model.PassportTicket;
import com.jigsaw.model.SiteUser;
import com.jigsaw.model.UserContextList;
import com.jigsaw.validator.UserValidator;

@Before(ControllerInterceptor.class)
public class UserController extends Controller {

	//@Clear
	@ActionKey("/login")
	//@Before(UserValidator.class)
	public void index(){
		System.out.println("/login");
		String loginName = getPara("loginname"); 
	    String password = getPara("password");
	    if(loginName == null || password == null || loginName.equals("") || password.equals("")){
	    	renderJson("{\"message\":\"请输入用户名和密码!\"}");
	    	//return;
	    }
	    loginName = "sysadmin";
	    password = "a111111";
	    
	    HttpServletRequest request = this.getRequest();
	    
	    SiteUser user = (new SiteUser()).signIn(loginName, password);
		user.currentLoginName = loginName;
	    user.authServer = request.getRemoteHost() + ":" + request.getRemotePort();
	    PassportSigninInfo signin = (new PassportSigninInfo()).persistentSignIn(user, true, false);
	    PassportTicket ticket = (new PassportTicket()).persistentTicket(signin, request);
	    if(user == null)
	    	renderJson("{\"message\":\"用户名或密码不正确!\"}");
	    else {
	    	renderJson(user);
	    }
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
