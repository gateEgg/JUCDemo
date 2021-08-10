package com.example.jucdemo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description 8锁
 * 4.两个对象 两个同步方法 谁先调用  结果：打电话
 * @Date 2021/8/10 10:24 上午
 */
public class Test03 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
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

class Phone3 {
    /**
     * 锁的对象是方法的调用着
     * 两个方法是同一个锁，谁先拿到锁，谁就先执行
     */
    public synchronized void call() {
        System.out.println("打电话");
    }

    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public void hello() {
        System.out.println("hello");
    }
}
