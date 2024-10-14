package com.hang.demo;

import java.util.Objects;

/**
 * @Author: CALYEE
 * @CreateTime: 2024-10-12
 * @Description: 单例模式（饿汉式/懒汉式）
 * @Version: 1.0
 */
public class 单例模式 {
    Hungry hungry = Hungry.getInstance();
    Lazy lazy = Lazy.getInstance();
}

/**
 * 懒汉式（带双重检验锁的）
 */
class Hungry {
    private volatile static Hungry hungry = null;

    public static Hungry getInstance() {
        if (hungry == null) {
            synchronized (Hungry.class) {
                if (hungry == null) {
                    hungry = new Hungry();
                }
                return hungry;
            }
        }
        return hungry;
    }
}

/**
 * 饿汉式
 */
class Lazy {
    private static Lazy lazy = new Lazy();

    private Lazy() {
        //1.在本类中维护一个私有构造方法
        if (Objects.nonNull(lazy))
            throw new RuntimeException(this.getClass().getName() + "已存在全局唯一实例!");
    }

    public static Lazy getInstance() {
        return lazy;
    }
}
