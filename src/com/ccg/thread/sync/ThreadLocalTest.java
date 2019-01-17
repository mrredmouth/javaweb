package com.ccg.thread.sync;


/**
 *	threadLocal,多个线程共用此变量，不会受影响 。
 *	threadLocal是多个线程的拷贝，缓存在map中，内存消耗比较大。
 */
public class ThreadLocalTest {

	protected static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		@Override
		protected Integer initialValue(){
			return 1;
		}
	};
	
	/**
	 * 测试线程，将ThreadLocal变量的值变化，并写回，查看线程之间是否影响。
	 */
	public static class TestThread implements Runnable{
		int id;
		public TestThread(int id){
			this.id = id;
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ":start");
			Integer s = threadLocal.get();
			s += id;
			threadLocal.set(s);
			System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
		}
	}
	
	public void startThreadArray(){
		Thread[] runs = new Thread[3];
		for(int i=0;i<runs.length;i++){
			runs[i] = new Thread(new TestThread(i));
		}
		for(int i=0;i<runs.length;i++){
			runs[i].start();
		}
	}
	
	public static void main(String[] args) {
		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		threadLocalTest.startThreadArray();
	}
}
