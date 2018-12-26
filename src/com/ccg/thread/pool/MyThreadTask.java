package com.ccg.thread.pool;

/**
 * 实现Runnable创建的线程任务
 * @author Administrator
 *
 */
public class MyThreadTask implements Runnable {
    private int taskNum;
     
    public MyThreadTask(int num) {
        this.taskNum = num;
    }
     
	@Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
