package com.jigsaw.model;

import java.util.HashSet;
import java.util.Set;

public class UserContextList {

	//public static Set<UserContext> currents = new HashSet<UserContext>();
	
	private static final ThreadLocal<UserContext> threadLocal = new ThreadLocal<UserContext>();
	
	public static UserContext getCurrent(){
		return threadLocal.get();
	}
	
	public static void setCurrent(UserContext uc){
		threadLocal.set(uc);
	}
	
	public static boolean IsAuthenticated(){
		UserContext uc = getCurrent();
		if(uc != null && uc.UserID > 0)
			return true;
		return false;
	}
}
