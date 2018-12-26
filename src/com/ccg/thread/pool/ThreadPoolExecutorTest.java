package com.ccg.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞队列ArrayBlockingQueue创建线程池ThreadPoolExecutor。
 * 一般需要根据任务的类型来配置线程池大小：
 * 		如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1;
 * 		如果是IO密集型任务，参考值可以设置为2*NCPU
 * @author Administrator
 *
 */
public class ThreadPoolExecutorTest {
	
    public static void main(String[] args) {   
    	
    	/**
    	 * 构造方法：ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, 
    	 * 				TimeUnit unit, BlockingQueue<Runnable> workQueue)
    	 * corePoolSize：	核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进任务缓存队列）
    	 * maximumPoolSize：	线程池最大能容忍的线程数
    	 * keepAliveTime：	线程存活时间 
    	 * unit：			keepAliveTime的时间单位，线程存活时间的单位：
    	 * 						TimeUnit.MILLISECONDS 	毫秒
    	 * 						TimeUnit.NANOSECONDS	纳秒
    	 * workQueue：		任务缓存队列，用来存放等待执行的任务
    	 */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(40, 100, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
         
        for(int i=0;i<100;i++){
        	MyThreadTask myTask = new MyThreadTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+
            		"，队列中等待执行的任务数目："+executor.getQueue().size()+
            		"，已执行完成的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}