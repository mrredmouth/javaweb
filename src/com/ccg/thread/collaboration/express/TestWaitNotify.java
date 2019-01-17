package com.ccg.thread.collaboration.express;

import com.ccg.thread.MyThreadUtils;

/**
 * 追踪快递：
 * 当快递里程变化，或者到达目的地了，通知相应的线程做业务处理
 */
public class TestWaitNotify {
	private static Express express = new Express(0,Express.CITY);
	
	/*检查里程数变化的线程，不满足条件则一直等待*/
	private static class CheckKm extends Thread{
		@Override
		public void run(){
			express.waitKm();
		}
	}
	
	/*检查地点变化的线程，不满足条件则一直等待*/
	private static class CheckSite extends Thread{
		@Override
		public void run(){
			express.waitSite();
		}
	}
	
	public static void main(String[] args) {
		for(int i=0;i<3;i++){
			new CheckSite().start();
		}
		for(int i=0;i<3;i++){
			new CheckKm().start();
		}
		MyThreadUtils.sleep(1000);
		/*改变里程数后，notifyAll(),唤醒所有处于wai状态的线程，唤醒里程和地点的所有线程。即使wait地点的线程也会被唤醒。*/
		express.changeKm(); 
	}
}
