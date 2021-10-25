package com.example.jucdemo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description 8锁
 * 关于锁的八个问题
 * 1.标准情况下，两个线程先打印什么？ 1发短信 2打电话  结果：1
 * 2.sendSms休息4s 两个线程先打印什么？ 1发短信 2打电话  结果：1
 * @Date 2021/8/10 9:41 上午
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    /**
     * 锁的对象是方法的调用着
     * 两个方法是同一个锁，谁先拿到锁，谁就先执行
     */
    public synchronized void call() {
        System.out.println("打电话");
    }

    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
}
