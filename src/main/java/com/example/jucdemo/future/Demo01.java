package com.example.jucdemo.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description 异步调用 CompletableFuture
 * 异步执行、成功回调、失败回调
 * @Date 2021/9/22 4:56 下午
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 无返回值 runAsync 异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "runAsync->Void");
//        });
//
//        System.out.println("11111");
//        // 获取阻塞执行结果
//        completableFuture.get();
        // 有返回值 supplyAsync 异步回调
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync->Integer");
            int i = 1 / 0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            // 正常的返回结果
            System.out.println("t->" + t);
            // 错误信息
            System.out.println("u->" + u);
        }).exceptionally((e) -> {
            e.getMessage();
            return -1;
        }).get());
    }
}
