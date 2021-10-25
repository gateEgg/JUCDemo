package com.example.jucdemo.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jiesi
 * @Description 懒汉式
 * 使用时加载
 * @Date 2021/9/28 2:45 下午
 */
public class Lazy {

    /**
     * volatile 防止指令重排
     */
    private static volatile Lazy LAZY;

    private static boolean flag = false;

    private Lazy() {
        synchronized(Lazy.class) {
            if (flag == false) {
                flag = true;
            } else {
                throw new RuntimeException("不要使用反射破坏单例");
            }
        }
//        System.out.println(Thread.currentThread().getName() + " ok");
    }

    /**
     * 双重检测 DCL
     * @return
     */
    public static Lazy getInstance() {
        if (LAZY == null) {
            synchronized (Lazy.class) {
                if (LAZY == null) {
                    // 非原子操作
                    LAZY = new Lazy();
                    /*
                    1、分配内存空间
                    2、执行构造方法，初始化对象
                    3、把这个对象指向空间
                     */
                }
            }
        }
        return LAZY;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        for (int i = 0; i < 10; i++) {
//            new Thread(Lazy::getInstance).start();
//        }
        Field flag = Lazy.class.getDeclaredField("flag");
        flag.setAccessible(true);

//        Lazy instance = Lazy.getInstance();
        Constructor<Lazy> declaredConstructors = Lazy.class.getDeclaredConstructor(null);
        Lazy instance2 = declaredConstructors.newInstance();
        flag.set(instance2, false);
        Lazy instance = declaredConstructors.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }

}
