package com.jigsaw.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.serializer.FstSerializer;
import com.jigsaw.config.AppConst;
import com.jigsaw.model.PassportTicket;

///控制器 拦截器
public class ControllerInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		
		getArgs(inv);
		
		System.out.println("类拦截器");
		System.out.println("Before method invoking");
		inv.invoke();
		System.out.println("After method invoking");
	}

	private PassportTicket getArgs(Invocation inv){
		//Object[] args = inv.getArgs();
		String token = inv.getController().getPara("token");
		if (token.equals(null) || token.isEmpty())
        {
            return null;
        }
		token = token.split(",")[0];
		
		Cache userCache = Redis.use(AppConst.PLUGIN_REDIS_USER);
		byte[] ticketValue = userCache.get(AppConst.REDIS_KEY_TICKET + token);
		Object ticketObj = FstSerializer.me.valueFromBytes(ticketValue);
		
		PassportTicket ticket = null;
		if(ticketObj != null){
			ticket = (PassportTicket)ticketObj;
		}else {
			ticket = PassportTicket.dao.findById(token);
			if(ticket != null){
				userCache.set( AppConst.REDIS_KEY_TICKET + token, FstSerializer.me.valueToBytes(ticket) );
			}else
				return null;
		}
		return ticket;
	}
	
}
