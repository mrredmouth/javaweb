package com.ccg.thread.collaboration.threadcollaboration;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间协作方式二：（使用Condition）
 *  调用Condition的await()和signal()方法，都必须在lock保护之内，即必须在lock.lock()和lock.unlock之间才可以使用
 *  cond.await()			对应object.wait()		阻塞当前线程，加锁
 *  cond.signal()			对应object.notify()		唤醒一个阻塞的线程，解锁
 *  cond.signalAll()		对应object.notifyAll()	唤醒所有阻塞的线程，解锁
 */
public class ThreadCollaborationCond {

    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
     
    class Thread1 extends Thread{
        @Override
        public void run() {
            try {
                lock.lock();
                //对应obj的wait()
            	cond.await();
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
         
        /**
         * thread1线程启动之后,cond.await()使得线程阻塞。
         * 等待thread2线程的cond.signal()释放锁后，thread1才被唤醒。
         */
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //InterruptedException线程中断的异常catch之后，需要手动停止线程
            Thread.currentThread().interrupt();
        }
        thread2.start();
    }
    
}
