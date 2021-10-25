package com.example.jucdemo.single;

/**
 * @author jiesi
 * @Description 饿汉式 单例(首先构造私有)
 * 类加载时调用，线程安全
 * 因为提前加载，可能会浪费空间
 * @Date 2021/9/28 2:40 下午
 */
public class Hungry {

    // 可能浪费空间
    private static byte[] BYTES = new byte[1024 * 1024];
    private static byte[] BYTES2 = new byte[1024 * 1024];

    public static final Hungry HUNGRY = new Hungry();

    private Hungry() {

    }

    public static Hungry getInstance() {
        return HUNGRY;
    }

}
