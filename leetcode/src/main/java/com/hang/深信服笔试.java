package com.hang;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: Study
 * @package: com.hang.obj
 * @className: 深信服笔试
 * @author: Calyee
 * @date: 2024/09/22 022 20:53
 * @version: 1.0
 */

public class 深信服笔试 {
    static List<List<Character>> res1 = new ArrayList<>();
    static List<List<Character>> res2 = new ArrayList<>();

    public static void main(String[] args) {
        // 测试样例
        List<String> arr = Arrays.asList("1", "2", "3", "4");
        char[] ch = "1234".toCharArray();
        dfs(ch, new ArrayList<>(), 0);
        System.out.println(res1.stream().filter(i -> !i.isEmpty()).collect(Collectors.toList()));

        dfs(ch, new ArrayList<>(), 0, 3);
        System.out.println(res2);

        System.out.println("----------------------------");
        linuxPathOperation("/../home/././calyee");

    }

    // 全排列（全部情况）
    public static void dfs(char[] arr, List<Character> track, int start) {
        res1.add(new ArrayList<>(track)); // 添加当前组合
        for (int i = start; i < arr.length; i++) {
            track.add(arr[i]);
            dfs(arr, track, i + 1);
            track.remove(track.size() - 1);
        }
    }

    // 全排列（指定情况）
    public static void dfs(char[] arr, List<Character> track, int start, int count) {
        if (track.size() == count) {
            res2.add(new ArrayList<>(track)); // 添加当前组合
            return;
        }
        for (int i = start; i < arr.length; i++) {
            if (track.contains(arr[i])) {
                continue;
            }
            track.add(arr[i]);
            dfs(arr, track, i + 1, count);
            track.remove(track.size() - 1);
        }
    }

    /**
     * linux path 模拟栈
     * 对于 ../则需要回退一个目录
     * 对于 ./ 则不需要进行操作
     * 对于 /xxx则需要跳转到该路径
     *
     * @param operation 模拟控制台操作（/../home/././calyee）
     */
    public static void linuxPathOperation(String operation) {
        String currentPath = "/home/calyee/ppaaa/project";
        Stack<String> stack = new Stack<>();
        String[] split = currentPath.split("/");
        for (String s : split) {
            stack.push(s);
        }
        String[] operations = operation.split("/");
        StringBuilder sb = new StringBuilder();
        if (split[0].equals(operations[0])) {
            for (String op : operations) {
                if (op.equals("..")) {
                    continue;
                } else if (!op.equals(".") && !op.equals("")) {
                    continue;
                }
                sb.append("/").append(op);
            }
        }else {
            for (String op : operations) {
                if (op.equals("..")) { // 需要出栈
                    stack.pop();
                } else if (!op.equals(".") && !op.equals("")) {// 不需要操作
                    continue;
                }
            }
            stack.forEach(sb::append);
        }
        System.out.println(sb.reverse().toString());
    }
}
