package com.ccg.thread;

import org.junit.Test;

import com.ccg.time.MyTimeStampUtils;

public class MyThreadUtilsTest {
	/**
	 * 用多线程测试，获取synchronized同步16位时间戳
	 */
	@Test
	public void testGetSynchronizedTime() {
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(MyTimeStampUtils.getSynchronizedTime());
				}
			}).start();
		}
    }
}
