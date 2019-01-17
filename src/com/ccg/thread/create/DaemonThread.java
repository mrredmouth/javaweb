package com.ccg.thread.create;

import com.ccg.thread.MyThreadUtils;

/**
 * 守护线程
 */
public class DaemonThread {
	
	private static class CreateThread1 extends Thread{
		@Override
		public void run(){
			try {
				//测试线程中断。调用父类Thread的public方法，返回中断标志位的值,true表示中断，否则线程不中断
				while (!isInterrupted()) {
					System.out.println(Thread.currentThread().getName() + " extends Thread");
				} 
				System.out.println(Thread.currentThread().getName() + " thread flag1 is " + isInterrupted());
			} finally {
				System.out.println(".....................finally");
			}
			System.out.println(Thread.currentThread().getName() + " thread flag2 is " + isInterrupted());
		}
	}


	public static void main(String[] args) {
		Thread thread0 = new CreateThread1();
		//设置为守护线程，必须放在start前面
		thread0.setDaemon(true);
		thread0.start();
		MyThreadUtils.sleep(5);
		//当main主线程执行完退出后，守护线程thread0无需interrupt会自动中断，后面的以及finally中的语句不保证会被执行
	}
}
