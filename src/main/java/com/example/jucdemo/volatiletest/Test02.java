package com.example.jucdemo.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiesi
 * @Description volatile原子性
 * @Date 2021/9/22 6:10 下午
 */
public class Test02 {

//    private static int i = 0;
//
//    // 使用synchronized关键字也可以实现，但是效率低
//    public synchronized static void add() {
//        i++;
//    }
    /**
     * 原子性的int
     */
    private volatile static AtomicInteger i = new AtomicInteger();

    public static void add() {
        i.incrementAndGet();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        // 默认main线程和gc线程开启
        if (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(i);
    }
}
