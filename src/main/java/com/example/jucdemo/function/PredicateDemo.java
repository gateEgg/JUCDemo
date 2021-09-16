package com.example.jucdemo.function;

import java.util.function.Predicate;

/**
 * @author jiesi
 * @Description Predicate 断定型接口 一个入参 返回值只能为boolean
 * @Date 2021/9/14 10:07 上午
 */
public class PredicateDemo {
    public static void main(String[] args) {
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.isEmpty();
//            }
//        };
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test("131"));
    }
}
