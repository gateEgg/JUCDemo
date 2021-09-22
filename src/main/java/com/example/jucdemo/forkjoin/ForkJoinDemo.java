package com.example.jucdemo.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author jiesi
 * @Description ForkJoin
 *
 * 如何使用ForkJoin
 * 1、通过ForkJoinPool执行
 * 2、计算任务 forkJoinPool.excute(ForkJoinTask task)
 * 3、计算类要继承 RecursiveTask/ForkJoinTask
 *
 * @Date 2021/9/14 11:50 上午
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    private long start;
    private long end;

    private final long temp = 100000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) <= temp) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 注意任务的均分，不然可能会报错
            long middle = (end + start) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            // 拆分任务 把任务压入队列
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            // fork时又会进入compute()方法，判断是否需要将子任务拆分为孙任务，以此类推
            return task1.join() + task2.join();
        }
    }
}
