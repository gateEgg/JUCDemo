package com.example.jucdemo.function;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author jiesi
 * @Description Consumer 消费性接口：只有输入 没有输出
 * @Date 2021/9/14 10:31 上午
 */
public class ConsumerDemo {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer = System.out::println;
        consumer.accept("sout");
    }
}
