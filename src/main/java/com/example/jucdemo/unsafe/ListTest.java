package com.example.jucdemo.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jiesi
 * @Description List测试
 * ArrayList线程不安全 会产生ConcurrentModificationException异常
 * @Date 2021/8/10 11:17 上午
 */
public class ListTest {
    public static void main(String[] args) {
        /*
        ArrayList在多线程下不安全，解决方法
        1.List<String> list = new Vector<>();
        2.List<String> list = Collections.synchronizedList(new ArrayList<>());
        3.List<String> list = new CopyOnWriteArrayList<>(); juc下的
        CopyOnWriteArrayList使用的锁是lock，在添加元素时，先将之前的数据复制，然后在复制之后的元素追加，将结果再返回至原list
         */
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
