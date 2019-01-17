package com.ccg.thread;

public class MyThreadUtils {

	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
			//catch此异常后要手动终止异常，即线程中断标志设置为true。
			Thread.currentThread().interrupt();
		}
	}
}
