package com.jigsaw.test;

import com.jfinal.aop.Duang;
import com.jfinal.aop.Enhancer;
import com.jigsaw.interceptor.OrderService;

public class TestMain {

	public void main(String[] args){
		//使用 Duang.duang 方法在任何地方对目标进行增强
		OrderService service = Duang.duang(OrderService.class);
		//调用 payment方法时会触发拦截器
		service.payment(1, 11);
		
		//使用 Enhancer.enhancer方法在任何对方对目标进行增强
		OrderService service2 = Enhancer.enhance(OrderService.class);
		service2.payment(2, 22);
	}
}
