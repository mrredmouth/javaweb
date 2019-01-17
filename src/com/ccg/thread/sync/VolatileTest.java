package com.ccg.thread.sync;

import com.ccg.thread.MyThreadUtils;

/**
 * volatile 最轻量的同步机制，只保存内存可见性，不同的线程共享变量的同一个地址，不会各自新增变量的地址。
 * 用地：只有一个线程进行修改，其他的线程都是读取，这个时候要求可见性，低原子性，可以用volatile。
 * 验证没有原子性。
 */
public class VolatileTest {

	private static class VolatileVar implements Runnable{
		
		private static Object object = new Object();
		//不加上synchronized，则a不具备原子性，值是无规则的。
		private volatile int a = 0;
		@Override
		public void run() {
			//object是全局静态变量，则多个此线程会共用一把锁
			synchronized(object){
				a++;
				System.out.println(Thread.currentThread().getName() + ": " + a);
				MyThreadUtils.sleep(5);
				a++;
				System.out.println(Thread.currentThread().getName() + ": " + a);
			}
		}
	}
	
	public static void main(String[] args) {
		VolatileVar volatileVar = new VolatileVar();
		Thread thread1 = new Thread(volatileVar);
		Thread thread2 = new Thread(volatileVar);
		Thread thread3 = new Thread(volatileVar);
		Thread thread4 = new Thread(volatileVar);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
	
}
