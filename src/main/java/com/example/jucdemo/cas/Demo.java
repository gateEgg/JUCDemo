package com.example.jucdemo.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiesi
 * @Description CAS Demo
 * compare and swap 比较并替换
 * @Date 2021/9/29 3:28 下午
 */
public class Demo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println(atomicInteger.compareAndSet(1, 2));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(1, 3));
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
