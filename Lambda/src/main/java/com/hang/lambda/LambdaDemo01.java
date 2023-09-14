package com.hang.lambda;

import org.junit.jupiter.api.Test;

/**
 * @ClassName Main
 * @Description 案例一:通过new线程引入lambda表达式
 * 表达式初体验
 * @Author QiuLiHang
 * @DATE 2023/9/14 014 19:06
 * @Version 1.0
 */

public class LambdaDemo01 {

    @Test
    public void Thread() {
        // 开启一个新的线程
        // 新写法(省去了定义方法 Lambda表达式)
        new Thread(() ->
                System.out.println("(新写法)新线程中执行的代码:" + Thread.currentThread().getName()))
                .start();
        // 旧写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("(旧写法)新线程中执行的代码:" + Thread.currentThread().getName());
            }
        }).start();
        System.out.println("这是主线程中的代码:" + Thread.currentThread().getName());
    }

}
