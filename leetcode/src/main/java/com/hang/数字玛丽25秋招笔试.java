package com.hang;

import java.util.*;

/**
 * @projectName: Study
 * @package: com.hang.obj
 * @className: 数字玛丽25秋招笔试
 * @author: Calyee
 * @description: 数字玛丽25秋招笔试
 * @date: 2024/09/24 024 19:25
 * @version: 1.0
 */

public class 数字玛丽25秋招笔试 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // abcaaXY12ab12
        String aaa = sc.nextLine();
        findRepeatWord(aaa);
    }

    // 给定字符串，找出有重复的字符，并输出其位置
    public static void findRepeatWord(String str) {
        Map<Character, List<Integer>> map = new HashMap<>();
        List<Character> set = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (!set.contains(str.charAt(i)))
                set.add(str.charAt(i));
            if (map.containsKey(str.charAt(i))) {
                map.get(str.charAt(i)).add(i + 1);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i + 1);
                map.put(str.charAt(i), list);
            }
        }
        Map<Character, List<Integer>> ans = new HashMap<>();
        map.forEach((k, v) -> {
                    if (v.size() != 1) {
                        ans.put(k, v);
                    }
                }
        );
        StringBuilder sb = new StringBuilder();
        set.forEach(item -> {
            if (ans.containsKey(item)) {
                ans.get(item).forEach(i -> {
                    sb.append(item)
                            .append(", ")
                            .append(i)
                            .append("; ");
                });
            }
        });
        System.out.println(sb.toString());
    }
}
