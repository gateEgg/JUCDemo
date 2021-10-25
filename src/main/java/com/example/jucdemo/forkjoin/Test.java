package com.example.jucdemo.forkjoin;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author jiesi
 * @Description  测试ForkJoin
 * 因为使用的为四核的mac，测试结果不够明显，可使用其他配置电脑尝试
 * @Date 2021/9/15 4:16 下午
 */
public class Test {

    /**
     * 指定线程数使用ForkJoinPool执行parallelStream
     * 花费时间242ms
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void psForkJoinPool()
            throws InterruptedException, ExecutionException {

        long firstNum = 1;
        long lastNum = 1_000_000;

        long time1 = System.currentTimeMillis();

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        long actualTotal = customThreadPool.submit(
                () -> aList.parallelStream().reduce(0L, Long::sum)).get();

        long time2 = System.currentTimeMillis();
        System.out.println("结果为:" + actualTotal + "时间：" + (time2 - time1));

    }

    /**
     * 150ms
     */
    public static void longStreamTest() {

        long firstNum = 1;
        long lastNum = 1_000_000;

        long time1 = System.currentTimeMillis();

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());

        Long actualTotal = aList.parallelStream().reduce(0L, Long::sum);

        long time2 = System.currentTimeMillis();
        System.out.println("结果为:" + actualTotal + "时间：" + (time2 - time1));

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
    }

    /**
     * 普通方法 599ms
     */
    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 1; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "；时间消耗为："+ (end - start));
    }

    /**
     * ForkJoin计算 881ms
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(1, 10_0000_0000);
        ForkJoinTask<Long> submit = pool.submit(task);
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + ";时间消耗为："+ (end - start));
    }

    /**
     * 并行流 464ms
     */
    public static void test3() {
        long start = System.currentTimeMillis();

        long sum = LongStream.rangeClosed(1, 10_0000_0000).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "时间消耗为："+ (end - start));
    }
}
