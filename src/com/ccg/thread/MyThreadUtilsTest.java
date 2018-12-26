package com.ccg.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ccg.time.MyTimeStampUtils;

public class MyThreadUtilsTest {

	/** commons.logging日志控件：org.apache.commons.logging.Log */
	private static final Log logger = LogFactory.getLog(MyThreadUtilsTest.class);
	/**
	 * 用多线程测试，获取synchronized同步16位时间戳
	 */
	@Test
	public void testGetSynchronizedTime() {
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					logger.info(MyTimeStampUtils.getSynchronizedTime());
				}
			}).start();
		}
    }
}
