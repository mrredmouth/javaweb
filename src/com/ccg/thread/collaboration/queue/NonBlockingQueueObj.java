package com.ccg.thread.collaboration.queue;

import java.util.PriorityQueue;

/**
 * 非堵塞队列，实现生产者-消费者模式：
 * PriorityQueue
 * 使用Object.wait()和Object.notify()、非阻塞队列实现生产者-消费者模式：
 * @author Administrator
 *
 */
public class NonBlockingQueueObj {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
     
    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }
        private void consume() {
            while(true){
                synchronized (queue) {
                    while(queue.size() == 0){
                        try {
                            System.out.println("队列空，等待数据");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();          //每次移走队首元素
                    queue.notify();
                    System.out.println("取出一个元素，队列剩余"+queue.size()+"个元素");
                }
            }
        }
    }
     
    class Producer extends Thread{
         
        @Override
        public void run() {
            produce();
        }
         
        private void produce() {
            while(true){
                synchronized (queue) {
                    while(queue.size() == queueSize){
                        try {
                            System.out.println("队列满，等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);        //每次插入一个元素
                    queue.notify();
                    System.out.println("插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                }
            }
        }
    }

    public static void main(String[] args) { 
    	NonBlockingQueueObj test = new NonBlockingQueueObj();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
         
        producer.start();
        consumer.start();
    }
    
}
