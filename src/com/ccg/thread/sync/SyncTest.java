package com.ccg.thread.sync;

/**
 * synchronized	多线程的变量共享。
 * 对象锁：加在实例方法上，锁的是对象的实例。
 * 类锁：加在类方法上，锁的是类。
 * 类锁和对象锁是两个不同的锁，没有关联。
 * 同一个类的不同实例的对象所，也没有关联，可以并发的。
 */
public class SyncTest {

	//共享变量
	private int age = 100000;
	//递增。实例方法，方法属于对象的实例。
	public synchronized void add(){ //锁的是对象的实例，即对象锁
		age++;
	}
	//递增。静态方法/类方法。
	public static synchronized void add2(){ //锁的是类，即类锁
	}
	//递减
	public synchronized void reduce(){
		age--;
	}
	public synchronized int getAge(){
		return age;
	}
	
	private static class AddThread extends Thread{
		private SyncTest syncTest;
		public AddThread(SyncTest syncTest,String name){
			super(name);
			this.syncTest = syncTest;
		}
		@Override
		public void run(){
			for(int i=0;i<100000;i++){
				syncTest.add();
			}
			System.out.println(Thread.currentThread().getName() + " addThread: age is " + syncTest.getAge());
		}
	}

	private static class ReduceThread extends Thread{
		private SyncTest syncTest;
		public ReduceThread(SyncTest syncTest,String name){
			super(name);
			this.syncTest = syncTest;
		}
		@Override
		public void run(){
			for(int i=0;i<100000;i++){
				syncTest.reduce();
			}
			System.out.println(Thread.currentThread().getName() + " reduceThread: age is " + syncTest.getAge());
		}
	}
	
	/**
	 * 打印出来addThread和reduceThread，后结束的那个打印出来的始终是100000，值不变。
	 * 第一次打印出的age值不等于100000，是因为第一个执行后第二个线程仍然在执行，把变量改变了。
	 */
	public static void main(String[] args) {
		SyncTest syncTest1 = new SyncTest();
		Thread addThread = new AddThread(syncTest1,"addThread");
		Thread reduceThread = new ReduceThread(syncTest1,"reduceThread");
		addThread.start();
		reduceThread.start();
	}
}
