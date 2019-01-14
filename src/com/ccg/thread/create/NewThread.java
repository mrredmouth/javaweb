package com.ccg.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {

	/**
	 * 创建线程方式一：继承Thread类
	 */
	private static class CreateThread1 extends Thread{
		@Override
		public void run(){
			super.run();
			System.out.println(Thread.currentThread().getName() + " extends Thread");
		}
	}
	/**
	 * 创建线程方式二：实现Runnable接口
	 */
	private static class CreateThread2 implements Runnable{
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " implements Runnable");
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
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Thread createThread1 = new CreateThread1();
		createThread1.start();
		
		CreateThread2 createThread2 = new CreateThread2();
		Thread thread2 = new Thread(createThread2);
		thread2.start();
		
		CreateThread3 createThread3 = new CreateThread3();
		FutureTask<String> futureTask = new FutureTask<>(createThread3);
		Thread thread3 = new Thread(futureTask);
		thread3.start();
		System.out.println(futureTask.get()); //获取执行线程返回的结果
	}
}
