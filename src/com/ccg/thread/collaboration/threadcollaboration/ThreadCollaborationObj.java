package com.ccg.thread.collaboration.threadcollaboration;

/**
 * 线程间协作的方式一：（使用object）
 * 利用Object对象的wait、notify、notifyAll：
 * object.wait():		阻塞当前线程，当前线程拥有此对象的monitor。加锁。（monitor只能被一个线程拥有）
 * object.notify():		释放monitor，唤醒一个正在等待monitor的线程，若多个线程等待，则只能唤醒其中一个。解锁。
 * object.notifyAll()：	释放monitor，唤醒所有正在等待monitor的线程。解锁。
 */
public class ThreadCollaborationObj {
    public static Object object = new Object();
     
    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                try {
                	//当前线程堵塞,等待对象的monitor；等待对象object的monitor被解锁了，才被唤醒。
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
