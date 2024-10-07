package com.hang;

/**
 * @projectName: Study
 * @package: com.hang.obj
 * @className: 五八同城笔试 2024 09 20
 * @author: Calyee
 * @description: 五八同城笔试
 * @date: 2024/09/20 020 19:13
 * @version: 1.0
 */

public class 五八同城笔试 {
    public static void main(String[] args) {
        int[][] ints = {{1, 2}, {3, 4}};
        int[][] ints1 = {{2, 3}};
//        int[][] intersection = findIntersection(ints, ints1);
        int i = StringSplit("aabbb");
        System.out.println("i = " + i);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param firstList  int整型二维数组
     * @param secondList int整型二维数组
     * @return int整型二维数组
     */
    /*
    AC 0.1538
     * 输入
     *[[0,3],[5,9],[11,13]],[[2,6],[8,10]]
     * 输出
     * [[2,3],[5,6],[8,9]]
     *
     * 输入 [[1,2],[3,4]],[[2,3]]
     * 输出 [[2,2],[3,3]]
     */
    public static int[][] findIntersection(int[][] firstList, int[][] secondList) {
        // write code here
        if (firstList.length == 0 || secondList.length == 0) {
            return firstList.length == 0 ? firstList : secondList;
        }
        int[][] ans = new int[1000][];
        // 遍历两个数组，判断是否有交集，有则加入结果数组中，没有则跳过
        for (int i = 0; i < firstList.length; i++) {// f[i] []
            int left = 0, right = 0;
            for (int j = 0; j < secondList.length; j++) {
                // 1，2    2，3
                if (left <= right) {
                    left = Math.max(firstList[i][0], secondList[i][0]);
                    right = Math.min(firstList[i][1], secondList[i][1]);
                    ans[i] = new int[]{left, right};
                }
            }
        }
        return ans;
        //List<int[]> ans = new ArrayList<>();
        //        for (int[] interval1 : firstList) {
        //            for (int[] interval2 : secondList) {
        //                int left = Math.max(interval1[0], interval2[0]);
        //                int right = Math.min(interval1[1], interval2[1]);
        //                if (left <= right) {
        //                    ans.add(new int[]{left, right});
        //                }
        //            }
        //        }
        //        return ans.toArray(new int[0][]);
    }

    /**
     * 给你一个由若干 a和b 组成的字符串 s，请你计算并返回将该字符串分割
     * 成两个非空子字符串（即左子字符串和右子字符串）所能获得的最大
     * 得分。
     * 「分割字符串的得分」为左子字符串中a的数量加上右子字符串中b的数
     * 量。
     *
     * @param str string字符串
     * @return int整型
     */
    /*
    AC 0.6
    "abbbab"
    5
将字符串 s 划分为两个非空子字符串的可行方案有：
左子字符串 = "a" 且 右子字符串 = "bbbab"，得分 = 1 + 4 = 5
左子字符串 = "ab" 且 右子字符串 = "bbab"，得分 = 1 + 3 = 4
左子字符串 = "abb" 且 右子字符串 = "bab"，得分 = 1 + 2 = 3
左子字符串 = "abbb" 且 右子字符串 = "ab"，得分 = 1 + 1 = 2
左子字符串 = "abbba" 且 右子字符串 = "b"，得分 = 2 + 1 = 3
     */
    public static int StringSplit(String str) {
        // write code here
        int ans = 0;
        if ("".equals(str)) return 0;
        for (int i = 1; i < str.length(); i++) {
            String left = str.substring(0, i + 1);
            String right = str.substring(i + 1);
            int leftC = 0, rightC = 0;
            // 左边
            for (int j = 0; j < left.length(); j++) {
                if (left.charAt(j) == 'a') {
                    leftC++;
                }
            }
            // 右边
            for (int k = 0; k < right.length(); k++) {
                if (right.charAt(k) == 'b') {
                    rightC++;
                }
            }
            ans = Math.max(ans, leftC + rightC);
        }
        return ans;
    }

    /**
     * 最初，你站在无限数轴上位置 startPos 处。
     * 给你两个正整数startPos和endPos。
     * 在一次移动中，你可以向左或者向右移动一个位置。
     * 给你一个正整数k，返回从 startPos 出发、恰好移动k 步并到达 endPos
     * 的不同方法数目。
     * 由于答案可能会很大，返回对10^9+7取余的结果。
     * 如果所执行移动的顺序不完全相同，则认为两种方法不同。
     * 注意：数轴包含负整数。
     *
     * @param startPos int整型
     * @param endPos   int整型
     * @param k        int整型 < 1000
     * @return int整型
     */
    /*
    AC 50
    输入 1,2,3
    输出 3
    存在 3 种从 1 到 2 且恰好移动 3 步的方法：
    - 1 -> 2 -> 3 -> 2.
    - 1 -> 2 -> 1 -> 2.
    - 1 -> 0 -> 1 -> 2.
    可以证明不存在其他方法，所以返回 3
    ===
    输入 2,5,10
    输出 0
    不存在从 2 到 5 且恰好移动 10 步的方法
     */
    public int numberOfWays(int startPos, int endPos, int k) {
        // write code here
        return 0; // AC 50
    }
}
