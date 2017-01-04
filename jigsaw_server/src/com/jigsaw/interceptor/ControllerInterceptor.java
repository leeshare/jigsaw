package com.jigsaw.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ControllerInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		System.out.println("类拦截器");
		System.out.println("Before method invoking");
		inv.invoke();
		System.out.println("After method invoking");
	}

}
