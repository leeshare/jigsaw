package com.jigsaw.interceptor;

import java.util.HashSet;
import java.util.Set;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.serializer.FstSerializer;
import com.jigsaw.config.AppConst;
import com.jigsaw.model.PassportSigninInfo;
import com.jigsaw.model.PassportTicket;
import com.jigsaw.model.UserContext;

///控制器 拦截器
public class ControllerInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		
		getArgs(inv);
		
		System.out.println("类拦截器" + inv.getControllerKey());
		System.out.println("Before method invoking");
		inv.invoke();
		System.out.println("After method invoking");
	}
	
	//根据ticket 读取用户Id
	private PassportSigninInfo getSigninInfoById(String signinId){
		PassportSigninInfo signinInfo = null;
		
		Cache userCache = Redis.use(AppConst.PLUGIN_REDIS_USER);
		byte[] objValue = userCache.get(AppConst.REDIS_KEY_SIGNIN + signinId);
		Object obj = FstSerializer.me.valueFromBytes(objValue);
		if(obj != null){
			signinInfo = (PassportSigninInfo)obj;
		}else {
			signinInfo = PassportSigninInfo.dao.findById(signinId);
			if(signinInfo != null){
				userCache.set( AppConst.REDIS_KEY_SIGNIN + signinId, FstSerializer.me.valueToBytes(signinInfo) );
			}else
				return null;
		}
		
		return signinInfo;
	}

	//根据客户端的token 来读取其在服务器上的状态
	private void getArgs(Invocation inv){
		//Object[] args = inv.getArgs();
		String token = inv.getController().getPara("token");
		if (token == null || token.isEmpty())
        {
            return;
        }
		token = token.split(",")[0];
		
		Cache userCache = Redis.use(AppConst.PLUGIN_REDIS_USER);
		byte[] ticketValue = userCache.get(AppConst.REDIS_KEY_TICKET + token);
		Object ticketObj = FstSerializer.me.valueFromBytes(ticketValue);
		
		PassportTicket ticket = null;
		if(ticketObj != null){
			ticket = (PassportTicket)ticketObj;
		} else {
			ticket = PassportTicket.dao.findById(token);
			if(ticket != null){
				userCache.set( AppConst.REDIS_KEY_TICKET + token, FstSerializer.me.valueToBytes(ticket) );
			}else
				return;
		}
		
		if(ticket != null){
			PassportSigninInfo signin = getSigninInfoById(ticket.getSigninId());
			if(signin != null){
				for(UserContext c : currents){
					if(c.UserID == signin.getUserId()){
						UserContext.CurrentUser = c;
						break;
					}
				}
				
				UserContext currentUser = new UserContext();
				currentUser.SigninID= ticket.getAppSigninId();
				currentUser.UserID = signin.getUserId();
				currentUser.UserName = signin.getUserName();
				currentUser.OrganizationID = ticket.getAppEnvironment() == null || ticket.getAppEnvironment().equals("") ? 0 : Integer.parseInt(ticket.getAppEnvironment());
				currentUser.AppCode = "";
				currentUser.IP = ticket.getAppSigninIp();
				currents.add(currentUser);
			}
		}
		
		return;
	}
}
