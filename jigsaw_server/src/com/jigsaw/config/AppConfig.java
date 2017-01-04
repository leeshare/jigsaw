package com.jigsaw.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jigsaw.controller.LoginController;
import com.jigsaw.model._MappingKit;

public class AppConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
		PropKit.use("a_little_config.txt");  //加载少量必要配置，随后可用 PropKit.get(...)获取
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/login", LoginController.class);
		
		//me.add(new FrontRoutes());
		//me.add(new AdminRoutes());
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		//loadPropertyFile("redis_config.txt");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		//第一次加载的配置，可以直接使用 PropKit.get() 来取
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(c3p0Plugin);
		me.add(activeRecordPlugin);
		
		String redisHost = PropKit.use("redis_config.txt").get("host");
		//非第一次加载的配置，则需要每次通过use来制定配置文件名再取值
		int redisPort = PropKit.use("redis_config.txt").getInt("port");
		int redisTimeout = PropKit.use("redis_config.txt").getInt("timeout");
		String redisPassword = PropKit.use("redis_config.txt").get("password");
		RedisPlugin rp = new RedisPlugin("myRedis", redisHost, redisPort, redisTimeout, redisPassword);
		me.add(rp);
		
		//activeRecordPlugin.addMapping("Site_User", SiteUser.class);
		//上面的操作，都移到了 _MappingKit类中
		_MappingKit.mapping(activeRecordPlugin);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterJFinalStart(){
		//在项目启动前回调
		//比如在系统启动后创建调度线程
	}
	
	@Override
	public void beforeJFinalStop(){
		//在项目关闭前回调
		//比如在系统关闭前写回缓存
	}

}
