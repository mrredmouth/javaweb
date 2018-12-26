package com.ccg.thread.collaboration;

/**
 * 线程间协作的两种方式：
 * 	1、利用Object对象的wait、notify、notifyAll：
 * 		调用某个对象的wait()方法能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）
 * 		调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，如果有多个线程都在等待
 * 			这个对象的monitor，则只能唤醒其中一个线程；
 * 		调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程；
 * 	2、使用Condition
 * @author Administrator
 */
public class ThreadCollaborationObj {
    public static Object object = new Object();
     
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                	//当前线程堵塞,等待对象的monitor；当对象object的monitor被解锁了，才被唤醒。
                    object.wait();
                } catch (InterruptedException e) {
                }
                System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
            }
        }
    }
     
    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
            	//对象object的monitor解锁，则等待monitor的其他线程有一个被唤醒
                object.notify();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了object.notify()");
            }
            System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
        }
    }
    

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
         
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
    
}
