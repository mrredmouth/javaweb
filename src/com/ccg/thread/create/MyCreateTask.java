package com.ccg.thread.create;

import java.util.concurrent.Callable;

/**
 * 创建线程，实现Callable，结合Future或FutureTask使用
 * @author Administrator
 */
public class MyCreateTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}