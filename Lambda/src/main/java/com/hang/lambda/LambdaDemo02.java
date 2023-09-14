package com.hang.lambda;

import com.hang.domain.Person;
import com.hang.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @ClassName Main
 * @Description 案例二  Collections sort排序
 * @Author QiuLiHang
 * @DATE 2023/9/14 014 19:06
 * @Version 1.0
 */

public class LambdaDemo02 {
    /**
     * ( 参数类型 参数名 )-> {
     * ------代码体------
     * }
     */

    @Test
    // 1. 无参数 无返回值
    public void noArgsAndReturn() {
        // 这个情况仅限于只有一个抽象方法
        goShow(() -> System.out.println("show方法执行了"));
    }


    @Test
    // 2. 有参数 有返回值
    public void haveArgsNoReturn() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("周杰伦", 19, 178));
        list.add(new Person("林俊杰", 20, 175));
        list.add(new Person("薛之谦", 18, 175));
        System.out.println("========No Lambda========");
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        list.forEach(System.out::println);
        System.out.println("==========Lambda==========");
        Collections.sort(list, (Person o1, Person o2) -> {
            return o1.getAge() - o2.getAge();
        });
        list.forEach(System.out::println);
    }


    public static void goShow(UserService userService) {
        // 无参数
        userService.show();
    }

}
