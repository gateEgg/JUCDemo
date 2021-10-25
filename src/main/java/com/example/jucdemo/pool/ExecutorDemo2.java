package com.example.jucdemo.pool;

import java.util.concurrent.*;

/**
 * @author jiesi
 * @Description 七大参数
 *
 * ThreadPoolExecutor.AbortPolicy() 拒绝并抛出异常
 * ThreadPoolExecutor.CallerRunsPolicy() 哪儿来回哪儿 丢回之前的线程执行
 * ThreadPoolExecutor.DiscardPolicy() 丢掉任务 不抛出异常
 * ThreadPoolExecutor.DiscardOldestPolicy() 尝试和最早的竞争 不会抛出异常
 *
 * @Date 2021/9/13 5:47 下午
 */
public class ExecutorDemo2 {
    public static void main(String[] args) {
        // 获取cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 最大线程数如何设置
        // 1.cpu密集型 系统cpu为几核，最大线程数就为几 这样cpu效率最高
        // 2.io密集型  判断程序中十分耗费io的线程数，eg：数量为10，那么最大线程数为10*2

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,// 核心线程数
                3,// 最大线程数
                2,// 过期时间
                TimeUnit.SECONDS,// 过期时间单位
                new LinkedBlockingQueue<>(3),// 阻塞队列
                Executors.defaultThreadFactory(),// 创建线程工厂，一般使用默认的 不修改
                new ThreadPoolExecutor.DiscardPolicy());// 拒绝策略，自带四种拒绝策略 可自定义

        try {
            for (int i = 0; i < 10; i++) {
                executor.execute(() -> System.out.println(Thread.currentThread().getName() + "ok!"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}
