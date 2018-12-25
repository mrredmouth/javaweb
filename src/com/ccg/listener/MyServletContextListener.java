package com.ccg.listener;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;

import com.ccg.io.properties.MyPropertyUtils;
import com.ccg.time.timetask.MyTaskRunner;

/**
 * 监听系统的启动和销毁
 * @author Administrator
 *
 */
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("web应用系统启动....");
		
		String issueFlag = "";
		try {
			issueFlag = MyPropertyUtils.getPropertyValue("sftp.properties","zjcw.screenDisplay.issueFlag","false");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(StringUtils.isNotBlank(issueFlag) && issueFlag.equals("true")){
			System.out.println(("----定时任务启动开始!--"));
			// 启动定时任务
			MyTaskRunner.getInstance().run();
			System.out.println("----定时任务启动结束!--");
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web应用系统销毁....");
	}

}
