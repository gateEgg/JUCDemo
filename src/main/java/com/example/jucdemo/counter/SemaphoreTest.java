package com.example.jucdemo.counter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description Semaphore 信号量
 * 作用：多个共享资源互斥的使用；并发限流，控制最大线程数
 * @Date 2021/8/10 4:31 下午
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 获取
                    // 如果资源已经满了 等待 等待资源释放为止
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    // 当前信号量+1 并唤醒等待的线程
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
