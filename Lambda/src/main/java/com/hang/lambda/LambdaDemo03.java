package com.hang.lambda;

import com.hang.domain.Person;
import com.hang.service.PersonService;
import com.hang.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName Main
 * @Description 案例二  Collections sort排序
 * @Author QiuLiHang
 * @DATE 2023/9/14 014 19:06
 * @Version 1.0
 */

public class LambdaDemo03 {
    public static void main(String[] args) {
        goPerson((String msg, String name)
                -> msg + name);
    }

    public static void goPerson(PersonService personService) {
        personService.show("信息", "小仇");
    }

}
