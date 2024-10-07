package com.hang;

import com.hang.obj.ListNode;
import com.hang.obj.TreeNode;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class 力扣 {
    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        System.out.println(res);
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merge = merge(intervals);
        System.out.println("merge = " + Arrays.deepToString(merge));
    }

    /*
    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出：            [[1,6],[8,10],[15,18]]
    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     */
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        // 添加第1个元素（初始化第一个对照标准）
        ans.add(intervals[0]);

        for (int[] interval : intervals) {
            int curLeft = interval[0];
            int curRight = interval[1];
            int curIndex = ans.size() - 1;
            // 如果当前元素左侧点 大于 结果数组的右侧节点 => 新的区间则添加
            if (curLeft > ans.get(curIndex)[1]) {
                ans.add(new int[]{curLeft, curRight});
            } else { // 否则就是有交集 => 则更新右侧边界
                ans.get(curIndex)[1] = Math.max(ans.get(curIndex)[1], curRight);
            }
        }
        return ans.toArray(new int[ans.size()][]);

    }

    /**
     * 全排列优化版本
     * res为全局结果集 List<List<Integer>> res = new ArrayList<>();
     *
     * @param arr   需要进行全排列的数组
     * @param track 参与递归存储每一种排列的数组
     * @param count 排列的数级（比如我需要排列三种结果的[1,2,3],[1,2,4]）
     * @param index 从哪一个下标开始进行（增加此项可以避免重复计算）
     */
    public static void dfs(List<Integer> arr, List<Integer> track, int count, int index) {
        if (track.size() == count) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            // 前置条件 剪枝处
            if (track.contains(arr.get(i))) {
                continue;
            }
            // 1. 做选择
            track.add(arr.get(i));
            // 2. 递归
            dfs(arr, track, count, index + 1);
            // 3. 撤销选择
            track.remove(track.size() - 1);
        }
    }

    /**
     * 层序遍历二叉树
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            ans.add(level);
        }
        return ans;
    }

    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] split = path.split("/");
        // 遍历路径中的每个部分
        for (String part : split) {
            // 如果部分为空或为当前目录符号 "."
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            // 如果部分为父目录符号 ".."，则弹出栈顶元素
            else if (part.equals("..")) {
                stack.pop();
            }
            // 否则，将部分压入栈中
            else {
                stack.push(part);
            }

        }
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/").append(s);
        }
        if (sb.length() == 0) return "/";
        return sb.toString();
    }

    // **** //
    public static int longestConsecutive(int[] nums) {
        if (nums.length <= 1) return nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (Integer item : set) {
            int curAns = 1, curNum = item;
            if (!set.contains(item - 1)) {
                while (set.contains(curNum + 1)) {
                    curNum++;
                    curAns++;
                }
            }
            ans = Math.max(ans, curAns);
        }
        return ans;
    }


    /**
     * 合并链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 都是空
        if (list1 == null && list2 == null)
            return null;
        // 其中一个为空
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode ans = new ListNode(-1);
        ListNode margin = ans;
        while (list1 != null && list2 != null) {
            // 除了尾部情况
            if (list1.val < list2.val) {
                margin.next = list1;
                list1 = list1.next;
            } else {
                margin.next = list2;
                list2 = list2.next;
            }
            margin = margin.next;
        }
        // 排尾阶段
        if (list1 == null)
            margin.next = list2;
        if (list2 == null)
            margin.next = list1;
        return ans.next;
    }

    /**
     * 验证回文串
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        if (s.length() == 1 || s.length() == 0) {
            return true;
        }
        char leftChar, rightChar;
        while (left < right) {
            leftChar = s.charAt(left);
            rightChar = s.charAt(right);
            // 判断是否相等
            if (leftChar == rightChar) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length, middle;
        while (left < right) {
            middle = left + right >> 1;
            if (target < nums[middle]) {
                right = middle - 1;
            } else if (target > nums[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return Math.max(left, right);
    }

    /**
     * 移动0
     *
     * @param nums
     * @return
     */
    public static int[] moveZeroes(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count++] = nums[i]; // 0的数量指针
            }
        }
        for (int i = count; i < nums.length; i++)
            nums[i] = 0;
        return nums;
    }

    /**
     * 合并两个有序数组
     *
     * @param nums1 主数组 nums1.length = m + n
     * @param m     后面需要替换的数量
     * @param nums2 替换数组
     * @param n     nums2.length
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = 0;
        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[index++];
        }
        Arrays.sort(nums1);
    }

    /**
     * 移除元素
     *
     * @param nums 给定数组
     * @param val  移除value
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int num : nums) {
            if (num != val) {
                nums[count++] = num;
            }
        }
        return count;
    }

    /**
     * TODO 删除有序数组中的重复项II
     *  <a href='https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/?envType=study-plan-v2&envId=top-interview-150'>删除有序数组中的重复项II<a/>
     *
     * @param nums 有序数组 nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int appearCount = 0; // 出现次数置零
            for (int j = 0; j < nums.length - i - 1; i++) {
                if (nums[i] == nums[j]) { // 出现一次加一次
                    appearCount++;
                }
                if (appearCount > 2) { // 出现两次以上则根据快慢差交换位置
                    int shouldDel = 0;
                    for (int k = j + 1; k < nums.length; k++) { // 在判断后面到底出现几个然后截取
                        if (nums[k] == nums[j]) {
                            shouldDel++;// 出现了shouldDel次
                        }
                    }
                    break;
                }
            }
        }
        return 0;
    }

    /**
     * 多数元素
     *
     * @param nums 目标数组
     * @return
     */
    public static int majorityElement(int[] nums) {
        // 默认候选人就是第一个元素
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            // 得支持票
            if (nums[i] == candidate) {
                count++;
            } else { // 得反对票
                count--;
                if (count == 0) {
                    // 更换候选人
                    candidate = nums[i];
                    count = 1;
                }
            }
        }
        return candidate;
        // 默认候选人就是第一个元素，然后给自己投一票（count=1），如果遇到相同的票则+1，否则-1。当count为0的时候换下一个人，并且重置票数为1（count=1）。
    }
}
