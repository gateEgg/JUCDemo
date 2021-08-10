package com.example.jucdemo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description 8锁
 * 5.增加两个静态同步方法 一个对象 先打印谁  结果：发短信
 * 6.两个对象 两个静态同步方法 先打印谁  结果：发短信
 * @Date 2021/8/10 10:24 上午
 */
public class Test04 {
    public static void main(String[] args) {
        // 两个对象的Class类模板 只有一份
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
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

// Phone4是一个唯一的Class对象
class Phone4 {
    /**
     * 增加了static之后，两个方法在类加载的时候就存在了，synchronized锁的是Class
     * Phone4拥有一个唯一的Class
     */
    public static synchronized void call() {
        System.out.println("打电话");
    }

    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
}
