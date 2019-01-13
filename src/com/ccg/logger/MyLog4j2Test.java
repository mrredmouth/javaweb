package com.ccg.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class MyLog4j2Test {

	/** log4j日志控件： org.apache.logging.log4j.Logger */
	private static final Logger logger1 = LogManager.getLogger(MyLog4j2Test.class);
	
	/** commons.logging日志控件：org.apache.commons.logging.Log */
	private static final Log logger2 = LogFactory.getLog(MyLog4j2Test.class);

	@Test
	public void testlogger1(){
		logger1.info("定时任务info");
		logger1.warn("定时任务warn");
		logger1.error("停止定时error");
		try{
			@SuppressWarnings("unused")
			int a = 1/0;
		}catch (Exception e) {
			logger1.error(e.getMessage(),e);
		}
	}
	@Test
	public void testlogger2(){
		logger2.info("定时任务info");
		logger2.warn("定时任务warn");
		logger2.error("停止定时error");
		try{
			@SuppressWarnings("unused")
			int a = 1/0;
		}catch (Exception e) {
			logger2.error("定时任务列表加载出错信息",e);
		}
	}
}
