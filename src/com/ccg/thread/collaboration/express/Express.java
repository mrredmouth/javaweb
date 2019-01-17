package com.ccg.thread.collaboration.express;

public class Express {

	public final static String CITY = "ShangHai";
	/*快递传输里程数*/
	private int km;
	/*快递到达终点*/
	private String site;
	public Express(){}
	public Express(int km,String site){
		this.km = km;
		this.site = site;
	}
	/*变化公里数，然后通知并唤醒处于wait状态并需要处理公里数的线程*/
	public synchronized void changeKm(){
		this.km = 101;
		notifyAll();
	}
	/*变化地点，然后通知并唤醒处于wait状态并需要处理地点的线程*/
	public synchronized void changeSite(){
		this.site = "BeiJing";
		notifyAll();
	}
	/*当快递里程数大于100时，更新数据库*/
	public synchronized void waitKm(){
		while(this.km<100){
			try {
				wait();
				System.out.println(Thread.currentThread().getName() + " is waitKmThread waked up");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("km changed:" + this.km);
		}
	}
	/*当快递到达目的地时通知用户*/
	public synchronized void waitSite(){
		while(this.site.equals(CITY)){
			try {
				wait();
				System.out.println(Thread.currentThread().getName() + " waitSiteThread is waked up");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("site changed:" + this.site);
	}
}
