package com.example.jucdemo.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jiesi
 * @Description TODO
 * @Date 2021/9/29 2:23 下午
 */
public enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingle instance = EnumSingle.INSTANCE;
        EnumSingle instance2 = EnumSingle.INSTANCE;

//        Constructor<?>[] declaredConstructors = EnumSingle.class.getDeclaredConstructors();
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance3 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance3);
    }
}
