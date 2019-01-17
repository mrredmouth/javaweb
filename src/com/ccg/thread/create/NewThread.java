package com.ccg.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一、创建线程方法三种：继承Thread类;实现Runnable接口;实现Callable接口。
 * 二、安全的终止线程方法：（stop()，不安全，被弃用）
 * 	interrupt():			不会中断线程，只是设置中断标志位值为true。父类Thread的public方法。
 * 	isInterrupted():		返回中断标志位的值。父类Thread的public方法。
 * 	Thread.interrupted():	返回中断标志位的值
 */
public class NewThread {

	/**
	 * 创建线程方式一：继承Thread类
	 */
	private static class CreateThread1 extends Thread{
		@Override
		public void run(){
			//测试线程中断。调用父类Thread的public方法，返回中断标志位的值,true表示中断，否则线程不中断
			while(!isInterrupted()){
				try {
					//睡100ms，但是线程在20ms后中断了，中断后catch了InterruptedException异常。
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " thread flag is " + isInterrupted());
					e.printStackTrace();
					//catch后中断标志位值会设为false,并没有被中断，线程仍会执行。所以，catch异常后要手动中断。
					Thread.currentThread().interrupt();
				}
				System.out.println(Thread.currentThread().getName() + " extends Thread");
			}
			System.out.println(Thread.currentThread().getName() + " thread flag is " + isInterrupted());
		}
	}
	
	/**
	 * 创建线程方式二：实现Runnable接口。
	 * Thread.currentThread()：返回当前线程
	 */
	private static class CreateThread2 implements Runnable{
		@Override
		public void run() {
			//测试线程中断
			while(!Thread.currentThread().isInterrupted()){
				System.out.println(Thread.currentThread().getName() + " implements Runnable");
			}
			System.out.println(Thread.currentThread().getName() + " thread flag is " + Thread.currentThread().isInterrupted());
		}
	}
	
	/**
	 * 创建线程方式三：实现Callable接口；
	 * 有返回值，使用时需要与Future或者FutureTask一起使用。
	 */
	private static class CreateThread3 implements Callable<String>{

		@Override
		public String call() throws Exception {
			System.out.println(Thread.currentThread().getName() + " implements Callable");
			return "CallResult";
		}
	}
	
	/**
	 * 无论哪种线程创建方式，最终都是生成的Thread对象
	 */
	public static void main1(String[] args) throws InterruptedException, ExecutionException {
		
		Thread thread0 = new CreateThread1();
		thread0.start();
		Thread.sleep(20);		//20毫秒内，run方法里的while一直循环
		thread0.interrupt(); 	//中断后，while循环退出
		
		CreateThread2 createThread2 = new CreateThread2();
		Thread thread1 = new Thread(createThread2);
		thread1.start();
		Thread.sleep(20);
		thread1.interrupt(); 	//中断后，while循环退出
		
		CreateThread3 createThread3 = new CreateThread3();
		FutureTask<String> futureTask = new FutureTask<>(createThread3);
		Thread thread2 = new Thread(futureTask);
		thread2.start();
		System.out.println(futureTask.get()); //获取执行线程返回的结果
	}

	public static void main(String[] args) {
		Thread thread0 = new CreateThread1();
		thread0.start();
		try {
			//20毫秒内，run方法里的while一直循环
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Thread.currentThread: " + Thread.currentThread().getName());
			Thread.currentThread().interrupt();
		}
		
		//设置优先级，默认是5。在不同的虚拟机和操作系统上，设置效果不同，故此优先级设置不可靠。
		thread0.setPriority(5);
		//中断线程，while循环退出
		thread0.interrupt(); 	
	}
}
