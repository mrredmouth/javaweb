package com.ccg.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听系统的启动和销毁
 * @author Administrator
 *
 */
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("web应用系统启动....");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web应用系统销毁....");
	}

}
