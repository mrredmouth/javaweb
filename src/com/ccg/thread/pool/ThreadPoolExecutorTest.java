package com.ccg.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池ThreadPoolExecutor，使用阻塞队列ArrayBlockingQueue创建。
 * 一般需要根据任务的类型来配置线程池大小：
 * 		如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1;
 * 		如果是IO密集型任务，参考值可以设置为2*NCPU
 * 其他线程池：
 * FixedThreadPool	无界线程池，使用无界队列创建，可能会造成内存溢出
 * SingleThreadExecutor	单个线程池，等同于new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>())
 * CachedThreadPool	缓存线程池，等同于new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>())
 * @author Administrator
 */
public class ThreadPoolExecutorTest {
	
    public static void main(String[] args) {   
    	
    	/**
    	 * 构造方法：ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, 
    	 * 				TimeUnit unit, BlockingQueue<Runnable> workQueue)
    	 * corePoolSize：	核心池的大小（即线程池中的线程数目大于这个参数时，提交的任务会被放进
    	 * 					任务缓存队列(阻塞队列 ，线程安全的队列)）
    	 * maximumPoolSize：	线程池最大能容忍的线程数
    	 * keepAliveTime：	线程存活时间 
    	 * unit：			keepAliveTime的时间单位，线程存活时间的单位：
    	 * 					如：TimeUnit.MILLISECONDS 	毫秒
    	 * 						TimeUnit.NANOSECONDS	纳秒
    	 * workQueue：		阻塞队列，任务缓存队列，用来存放等待执行的任务
    	 * ThreadFactory	线程工厂，缺省的线程命名规则：pool-数字+thread-数字
    	 * RejectedExecutionHandler 	饱和策略的接口：（我们可以自己实现，然后创建线程池）
    	 * 						 实现类：1）AbortPolicy:直接抛出异常（默认）
    	 * 								2）CallerRunsPolicy：用调用者所在的线程来执行任务
    	 * 								3）DiscardOldestPolicy：丢弃阻塞队列中最老的任务，执行最新的
    	 * 								4）DiscardPolicy：直接丢弃当前提交的任务
    	 */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(40, 100, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
         
        for(int i=0;i<100;i++){
        	MyThreadTask myTask = new MyThreadTask(i);
        	/**
        	 * ThreadPoolExecutor对象执行线程常用的两个方法：
        	 * 	execute(Runnable command)
        	 *  上级类中的 submit(Runnable task)
        	 */
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+
            		"，队列中等待执行的任务数目："+executor.getQueue().size()+
            		"，已执行完成的任务数目："+executor.getCompletedTaskCount());
        }
        //关闭线程池
        executor.shutdown();
    }
}