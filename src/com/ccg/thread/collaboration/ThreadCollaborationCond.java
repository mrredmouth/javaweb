package com.ccg.thread.collaboration;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间协作的两种方式：
 * 	1、利用Object对象的wait、notify、notifyAll：
 *  2、使用Condition:
 *  调用Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 *  Conditon中的await()			对应Object的wait()；
　　Condition中的signal()		对应Object的notify()；
　　Condition中的signalAll()		对应Object的notifyAll()。
 * @author Administrator
 *
 */
public class ThreadCollaborationCond {

    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
     
    class Thread1 extends Thread{
        @Override
        public void run() {
            try {
            	//对应obj的wait()
                lock.lock();
            	cond.await();;
            } catch (InterruptedException e) {
            	e.printStackTrace();
            } finally{
                lock.unlock();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁cond.await()");
        }
    }
     
    class Thread2 extends Thread{
        @Override
        public void run() {
        	try {
                lock.lock();
            	//对象object的monitor解锁，则等待monitor的其他线程有一个被唤醒
            	cond.signal();
                System.out.println("线程"+Thread.currentThread().getName()+"调用了cond.signal()");
        	} catch (Exception e) {
        		e.printStackTrace();
            } finally{
                lock.unlock();
            }
        	System.out.println("线程"+Thread.currentThread().getName()+"释放了锁");
        }
    }
    

    public static void main(String[] args) {

    	ThreadCollaborationCond test = new ThreadCollaborationCond();
        Thread1 thread1 = test.new Thread1();
        Thread2 thread2 = test.new Thread2();
         
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
    
}
