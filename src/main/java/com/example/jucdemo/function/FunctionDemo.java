package com.example.jucdemo.function;

import java.util.function.Function;

/**
 * @author jiesi
 * @Description Function 一个入参T 一个返回值R
 * @Date 2021/9/14 10:03 上午
 */
public class FunctionDemo {
    public static void main(String[] args) {
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                return s;
//            }
//        };

        Function<String, String> function = s -> s;
        System.out.println(function.apply("sssss"));
    }
}
