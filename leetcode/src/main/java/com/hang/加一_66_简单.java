package com.hang;

import java.util.Arrays;

/**
 * @ClassName question_66
 * @Description 加一
 * @Link https://leetcode.cn/problems/plus-one/description/
 */

public class 加一_66_简单 {

    public static void main(String[] args) {
        int[] a = new int[]{
                1, 2, 3
        };
        // 结果应该是 1, 2, 4  : 4-> 3+1
        int[] ints = plusOne(a);
        System.out.println("res = " + Arrays.toString(ints));
    }

    public static int[] plusOne(int[] digits) {
        // TODO 居然不是直接加1, 还要判定是不是9进位
        int len = digits.length - 1;
        if(digits[len] == 9){

        }
        digits[len] += 1;
        return digits;
    }
}
