package com.example.jucdemo.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiesi
 * @Description A B C依次唤醒执行
 * @Date 2021/8/9 5:01 下午
 */
public class Test01 {
//    public static void main(String[] args) {
//        // 获取cpu核数
//        System.out.println(Runtime.getRuntime().availableProcessors());
//    }
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }


}

class Data {
    private int num = 0;
    private final Lock lock = new ReentrantLock();

    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void printA () {
        lock.lock();
        try {
            while (num != 0) {
                conditionA.await();
            }
            num = 1;
            System.out.println(Thread.currentThread().getName() + "=>printA");
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB () {
        lock.lock();
        try {
            while (num != 1) {
                conditionB.await();
            }
            num = 2;
            System.out.println(Thread.currentThread().getName() + "=>printB");
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC () {
        lock.lock();
        try {
            while (num != 2) {
                conditionC.await();
            }
            num = 0;
            System.out.println(Thread.currentThread().getName() + "=>printC");
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
