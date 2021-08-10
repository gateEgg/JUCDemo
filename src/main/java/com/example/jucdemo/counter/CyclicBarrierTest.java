package com.example.jucdemo.counter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jiesi
 * @Description CyclicBarrier 加法计数器
 * 达到一定阈值后继续执行
 * @Date 2021/8/10 4:10 下午
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(7, () -> {
            System.out.println("神龍を召喚された");
        });
        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "集齐了" + temp +"颗龙珠");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
