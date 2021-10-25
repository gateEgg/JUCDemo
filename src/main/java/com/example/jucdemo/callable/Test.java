package com.example.jucdemo.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jiesi
 * @Description callable test
 * @Date 2021/8/10 3:41 下午
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread(() -> {
//            // 这里只能是Runnable的，但是目前需要传入一个Callable的，所以需要将他们之间建立联系
//        }).start();
//        new Thread(() -> {Runnable});
//        new Thread(() -> {FutureTask});
//        new Thread(() -> {FutureTask(Callable)});
        MyThread thread = new MyThread();
        FutureTask task = new FutureTask(thread);
        // 有缓存
        new Thread(task, "A").start();
        new Thread(task, "B").start();

        // 这里有可能会阻塞。如果下面执行时间过久的话，一般将获取返回值放在代码最后
        // 或者使用异步通信
        String o = (String) task.get();
        System.out.println(o);
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() {
        // 耗时的操作
        System.out.println("call()");
        return "123";
    }
}
