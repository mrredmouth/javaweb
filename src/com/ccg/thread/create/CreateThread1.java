package com.ccg.thread.create;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用 Callable + Future 获取执行结果。
 * 创建线程三种方式：
 * 	在执行完任务之后无法获取执行结果：
 * 		1、继承Thread；
 * 		2、实现Runnable接口；
 * 	在任务执行完毕之后得到任务执行结果：
 * 		3、Callable + Future；
 * 		4、Callable + FutureTask；
 * @author Administrator
 */
public class CreateThread1 {
	
	public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyCreateTask task = new MyCreateTask();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
         
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
         
        System.out.println("主线程在执行任务");
         
        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
         
        System.out.println("所有任务执行完毕");
    }
}
