package com.example.jucdemo.counter;

import java.util.concurrent.CountDownLatch;

/**
 * @author jiesi
 * @Description CountDownLatch 减法计数器
 * 一般用于前段任务必须执行完 然后执行接下来任务时
 * @Date 2021/8/10 4:00 下午
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                // 数量-1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 等待计数器归零，然后再向下执行
        countDownLatch.await();
        System.out.println("结束！");
    }
}
