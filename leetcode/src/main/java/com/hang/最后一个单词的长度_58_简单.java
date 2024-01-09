package com.hang;

/**
 * @ClassName question_58
 * @Description 最后一个单词的长度
 * @Link https://leetcode.cn/problems/length-of-last-word/description
 */

public class 最后一个单词的长度_58_简单 {

    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        String s2 = "a";
        int i = lengthOfLastWord(s2);
        System.out.println("i = " + i);
    }

    public static int lengthOfLastWord(String s) {
        s = s.trim();
        char[] ch = s.toCharArray();
        int len = s.length();
        int sum = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (ch[i] == ' ') {
                return sum;
            }
            sum++;
        }
        return sum;
    }
}
