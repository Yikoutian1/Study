package com.hang.service;

/**
 * 标志注解接口,函数式接口
 */
@FunctionalInterface // 被该注解修饰的接口 只能定义一个方法
public interface UserService {
    // 无参数 无返回值
    void show();

    // void error(); // 会报错 已经被@FunctionalInterface修饰了
}
