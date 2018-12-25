package com.ccg.time.timetask;

import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 通过单例的run方式开始执行定时任务，可以放在监听器中在项目启动的时候执行此定时任务
 * MyTaskRunner.getInstance().run()
 * @author Administrator
 *
 */
public class MyTaskRunner {

	private static final Log logger = LogFactory.getLog(MyTaskRunner.class);
	private static MyTaskRunner instances = null;
	
	private MyTaskRunner() {}

	/**
	 * 获取单例
	 * @return
	 */
	public static MyTaskRunner getInstance() {
		return instances == null ? new MyTaskRunner() : instances;
	}
	
	public void run() {
		try {
			Timer timer = new Timer(true); 
			
			//time：第一次执行时间，每隔24*60*60*1000ms执行一次
			//timer.schedule(new MyTask(), DateTimeUtil.getDate(14,30,0), 24*60*60*1000);
			
			//5s后执行，每2s执行一次
			timer.schedule(new MyTask(), 5000, 2000);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("定时任务列表加载出错信息："+e.getMessage());
		} finally {
			//程序异常
			logger.debug("定时任务列表加载！");
		}
	}

}
