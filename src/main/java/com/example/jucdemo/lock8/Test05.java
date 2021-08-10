package com.example.jucdemo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description 8锁
 * 7.一个对象 一个同步方法 一个静态同步方法 先打印谁  结果：打电话
 * 8.两个对象 一个同步方法 一个静态同步方法 先打印谁  结果：打电话
 * @Date 2021/8/10 10:24 上午
 */
public class Test05 {
    public static void main(String[] args) {
        Phone5 phone1 = new Phone5();
        Phone5 phone2 = new Phone5();
        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

class Phone5 {
    /**
     * 普通同步方法 锁的是对象
     */
    public synchronized void call() {
        System.out.println("打电话");
    }

    /**
     * 静态同步方法 锁的是Class
     */
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
}
