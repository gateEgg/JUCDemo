package com.example.jucdemo.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description volatile 可见性
 * @Date 2021/9/22 6:06 下午
 */
public class Test01 {

    private volatile static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (i == 0) {

            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        i = 1;
        System.out.println(i);
    }
}
