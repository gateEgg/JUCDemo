package com.example.jucdemo.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description 8锁
 * 关于锁的八个问题
 * 3.增加一个普通方法 同一个对象 先调用哪个  结果：hello
 * @Date 2021/8/10 9:41 上午
 */
public class Test02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.hello();
        }, "B").start();
    }
}

class Phone2 {
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

    /**
     * 普通方法 不受同步限制
     */
    public void hello() {
        System.out.println("hello");
    }
}
