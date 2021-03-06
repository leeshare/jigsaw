package com.jigsaw.model;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import com.jigsaw.model.base.BasePassportTicket;
import com.mysql.jdbc.StringUtils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class PassportTicket extends BasePassportTicket<PassportTicket> {
	public static final PassportTicket dao = new PassportTicket();
	
	public PassportTicket getById(String token){
		return dao.findById(token);
		
	}
	
	public String AppEnvironment;
	public java.lang.String getAppEnvironment() {
		return AppEnvironment;
	}
	public void setAppEnvironment(java.lang.String appEnvironment) {
		AppEnvironment = appEnvironment;
	}
	
public PassportTicket persistentTicket(PassportSigninInfo signin, HttpServletRequest request){
	
		String id = java.util.UUID.randomUUID().toString();
		String appId = StringUtils.isNullOrEmpty(request.getParameter("aid")) ? "ManageSite" : request.getParameter("aid");
		String signinUrl = StringUtils.isNullOrEmpty(request.getParameter("ru")) ? request.getRequestURL().toString() : URLDecoder.decode(request.getParameter("ru"));
		String signinIp = StringUtils.isNullOrEmpty(request.getParameter("ip")) ? getClientIp(request) : request.getParameter("ip");
		String logoffUrl = StringUtils.isNullOrEmpty(request.getParameter("lou")) ? "#" : request.getParameter("lou");
		dao.set("APP_SIGNIN_ID", id)
		.set("SIGNIN_ID", signin.getSigninId())
		.set("APP_ID", appId)
		.set("APP_SIGNIN_URL", signinUrl)
		.set("APP_SIGNIN_IP", signinIp)
		.set("APP_LOGOFF_URL", logoffUrl)
		.save();
		
		return dao.findById(id);
	}
	
	private static String getClientIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if(!StringUtils.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1)
				return ip.substring(0, index);
			else
				return ip;
		}
		ip = request.getHeader("X-Real-IP");
		if(!StringUtils.isNullOrEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}
}
