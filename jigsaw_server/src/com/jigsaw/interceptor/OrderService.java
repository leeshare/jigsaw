package com.jigsaw.interceptor;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

public class OrderService {

	@Before(Tx.class)
	public void payment(int orderId, int userId){
		//service code here
	}
}
