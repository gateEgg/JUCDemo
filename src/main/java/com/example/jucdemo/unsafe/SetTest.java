package com.example.jucdemo.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author jiesi
 * @Description Set测试
 * HashSet线程不安全 会产生ConcurrentModificationException异常
 * @Date 2021/8/10 11:17 上午
 */
public class SetTest {
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
        // 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
        // 2
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
