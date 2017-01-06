package com.jigsaw.test;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;

public class TestRedis {
	
	//web 环境下使用 RedisPlugin
	public void redisDemo(){
		Cache userCache = Redis.use("userRedis");
		userCache.set("key", "223");
		String value = userCache.get("key");
		
//		Cache photoCache = Redis.use("photoRedis");
//		photoCache.set("k", "v");
//		photoCache.get("k");
		
		userCache = Redis.use();
		userCache.set("jfinal", "awesome");
	}
	
	//非web 环境下使用 RedisPlugin
	public static void main(String[] args){
		String redisHost = PropKit.use("redis_config.txt").get("host");
		//非第一次加载的配置，则需要每次通过use来制定配置文件名再取值
		int redisPort = PropKit.use("redis_config.txt").getInt("port");
		int redisTimeout = PropKit.use("redis_config.txt").getInt("timeout");
		String redisPassword = PropKit.use("redis_config.txt").get("password");
		RedisPlugin rp = new RedisPlugin("userRedis", redisHost, redisPort, redisTimeout, redisPassword);
		
		rp.start();
		
		Redis.use().set("key", "123");
		String value = Redis.use().get("key");
		
	}
}
