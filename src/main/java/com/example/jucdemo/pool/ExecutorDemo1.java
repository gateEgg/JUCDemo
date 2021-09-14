package com.example.jucdemo.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jiesi
 * @Description 三大方法
 * @Date 2021/9/13 5:19 下午
 */
public class ExecutorDemo1 {
    public static void main(String[] args) {
        // 创建单个线程
//        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 创建固定个数线程
//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        // 可伸缩线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                executor.execute(() -> System.out.println(Thread.currentThread().getName() + "ok!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
