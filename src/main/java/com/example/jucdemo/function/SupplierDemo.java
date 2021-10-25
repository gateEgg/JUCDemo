package com.example.jucdemo.function;

import java.util.function.Supplier;

/**
 * @author jiesi
 * @Description Supplier 供给型接口：只有返回 没有入参
 * @Date 2021/9/14 10:34 上午
 */
public class SupplierDemo {
    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 1024;
//            }
//        };

        Supplier<Integer> supplier = () -> 1024;

        System.out.println(supplier.get());

    }
}
