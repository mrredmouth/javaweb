package com.ccg.thread.collaboration.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用阻塞队列实现的生产者-消费者模式：
 * ArrayBlockingQueue，
 * 使用阻塞队列代码要简单得多，不需要再单独考虑同步和线程间通信的问题。
 * 在并发编程中，一般推荐使用阻塞队列，这样实现可以尽量地避免程序出现意外的错误。
 * @author Administrator
 *
 */
public class BlockingQueue {
    private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);
     
    class Consumer extends Thread{
         
        @Override
        public void run() {
            consume();
        }
         
        private void consume() {
            while(true){
                try {
                    queue.take();
                    System.out.println("取出一个元素，队列剩余"+queue.size()+"个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
                try {
                    queue.put(1);
                    System.out.println("插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    

    public static void main(String[] args) { 
    	BlockingQueue test = new BlockingQueue();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
         
        producer.start();
        consumer.start();
    }
}
