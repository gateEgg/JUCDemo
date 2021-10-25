package com.example.jucdemo.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description SynchronousQueue 同步队列 不存储元素 put了一个元素之后 必须先take出来 否则不能再继续put
 * @Date 2021/9/13 4:54 下午
 */
public class SynchronizeQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "->put 1");
                blockingQueue.put(1);
                System.out.println(Thread.currentThread().getName() + "->put 2");
                blockingQueue.put(2);
                System.out.println(Thread.currentThread().getName() + "->put 3");
                blockingQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->1 " + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->2 " + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "->3 " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }

}
