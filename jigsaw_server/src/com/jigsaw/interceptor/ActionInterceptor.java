package com.jigsaw.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ActionInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv){
		System.out.println(String.format("方法拦截器：控制器为 %s, 方法为 %s", inv.getController(), inv.getMethodName()));
	}
}
