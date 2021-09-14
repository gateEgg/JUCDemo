package com.example.jucdemo.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jiesi
 * @Description TODO
 * @Date 2021/9/13 4:04 下午
 */
public class Test01 {

    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 有返回值 抛出异常
     * 写入 add
     * 取出 remove
     */
    public static void test1() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add(2));
        System.out.println(blockingQueue.add(3));
        // 队列满了之后抛出 java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add(4));

        // 检测队首元素
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // 无元素之后抛出 java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
    }

    /**
     * 有返回值 不抛出异常
     * 写入 offer
     * 取出 poll
     */
    public static void test2() {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        // 队列满了之后 返回FALSE
//        System.out.println(blockingQueue.offer(4));

        // 检测队首元素
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 无元素之后返回null
//        System.out.println(blockingQueue.poll());
    }

    /**
     * 阻塞等待
     * 写入 offer
     * 取出 poll
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        // 队列满了之后 一直等待
//        blockingQueue.put(4);

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // 无元素之后一直等待
//        System.out.println(blockingQueue.take());
    }

    /**
     * 超时等待
     * 写入 offer
     * 取出 poll
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(3));
        // 等待超过2s结束
//        System.out.println(blockingQueue.offer(4, 2, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        // 等待超过2s结束
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }
}
