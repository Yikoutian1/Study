package com.hang;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: CALYEE
 * @CreateTime: 2024-10-07
 * @Description: 并查集测试 https://bbs.codeaha.com/forum.php?mod=viewthread&tid=11223
 * @Version: 1.0
 */
public class 并查集 {
    /*
    测试用例
11 10
1 2
3 4
5 2
4 6
2 6
7 11
8 7
9 7
9 11
1 6
运行结果是：
3
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String oneLine = sc.nextLine();
        n = Integer.parseInt(oneLine.split(" ")[0]);
        m = Integer.parseInt(oneLine.split(" ")[1]);
        init();  //初始化是必须的

        StringBuilder sb = new StringBuilder();
        sb.append("Origin Arrays\n").append(Arrays.toString(arrays));

        for (int i = 1; i <= m; i++) {
            String line = sc.nextLine();
            int x = Integer.parseInt(line.split(" ")[0]);
            int y = Integer.parseInt(line.split(" ")[1]);
            //开始合并犯罪团伙
            merge(x, y);
        }

        //最后扫描有多少个独立的犯罪团伙
        for (int i = 1; i <= n; i++) {
            if (arrays[i] == i)
                sum++;
        }

        sb.append("\nThere are ").append(sum).append(" criminal groups.\n")
                .append("Processed Arrays\n")
                .append(Arrays.toString(arrays));
        System.out.println(sb.toString());

    }

    private static int[] arrays;
    /**
     * 可以输入以下数据进行验证。第一行n m，n表示强盗的人数，m表示警方搜集到的m条线索。接下来的m行每一行有两个数a和b，表示强盗a和强盗b是同伙。
     */
    private static int n, m, sum = 0;

    //这里是初始化，非常地重要，数组里面存的是自己数组下标的编号就好了。
    private static void init() {
        // 我们初始化是从1开始的，0用不上
        arrays = new int[n + 1];
        for (int i = 1; i <= n; i++)
            arrays[i] = i;
    }

    //这是找爹的递归函数，不停地去找爹，直到找到祖宗为止，其实就是去找犯罪团伙的最高领导人，
    //“擒贼先擒王”原则。
    private static int getParent(int v) {
        // 如果是自己的话 直接返回了
        if (arrays[v] == v)
            return v;
        else {
            //这里是路径压缩，每次在函数返回的时候，顺带把路上遇到的人的“BOSS”改为最后找
            //到的祖宗编号，也就是犯罪团伙的最高领导人编号。这样可以提高今后找到犯罪团伙的
            //最高领导人（其实就是树的祖先）的速度。
            arrays[v] = getParent(arrays[v]); //这里进行了路径压缩
            return arrays[v];
        }
    }

    //这里是合并两子集合的函数
    private static void merge(int v, int u) {
        //t1、t2分别为v和u的大BOSS（首领），每次双方的会谈都必须是各自最高领导人才行
        int t1 = getParent(v);
        int t2 = getParent(u);
        //判断两个结点是否在同一个集合中，即是否为同一个祖先。
        if (t1 != t2) {
            arrays[t2] = t1;//“靠左”原则，左边变成右边的BOSS。即把右边的集合，作为左边集合的子集合。
        }
    }
}
