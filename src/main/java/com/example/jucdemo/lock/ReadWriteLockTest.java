package com.example.jucdemo.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jiesi
 * @Description ReadWriteLock
 * 读-读 可以共存
 * 读-写 不能共存
 * 写-写 不能共存
 * 独占锁（写锁）一次只能被一个线程占用
 * 共享锁（读锁）多个线程可以同时占有
 * @Date 2021/8/11 10:01 上午
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCacheLock<Integer, Object> map = new MyCacheLock<>();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                map.set(temp, temp);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                map.get(temp);
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 不加锁的缓存
 * @param <K> key
 * @param <V> value
 */
class MyCache<K,V> {
    private volatile Map<K,V> map = new HashMap<>();

    public void get(K k) {
        System.out.println(Thread.currentThread().getName() + "读取");
        map.get(k);
        System.out.println(Thread.currentThread().getName() + "读取完毕");
    }

    public void set(K k, V v) {
        System.out.println(Thread.currentThread().getName() + "写入");
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "写入完毕");
    }
}

/**
 * 增加 的缓存
 * @param <K> key
 * @param <V> value
 */
class MyCacheLock<K,V> {
    private volatile Map<K,V> map = new HashMap<>();

    /**
     * 读写锁 可以更加细粒度的控制
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 读 所有线程都可以读
     * @param k
     */
    public void get(K k) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取");
            map.get(k);
            System.out.println(Thread.currentThread().getName() + "读取完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 写入的时候 只希望有一个线程写
     * @param k
     * @param v
     */
    public void set(K k, V v) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入");
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "写入完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}