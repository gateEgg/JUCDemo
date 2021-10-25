package com.example.jucdemo.single;

/**
 * @author jiesi
 * @Description 静态内部类实现单例
 * @Date 2021/9/29 11:54 上午
 */
public class Holder {

    private Holder() {

    }

    private static Holder getInstance() {
        return innerHolder.HOLDER;
    }

    private static class innerHolder {
        private static final Holder HOLDER = new Holder();
    }
}
