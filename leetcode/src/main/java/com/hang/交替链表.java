package com.hang;

import com.hang.obj.ListNode;

import java.util.List;

/**
 * @projectName: Study
 * @package: com.hang
 * @className: 交替链表
 * @author: Calyee
 * @date: 2024/09/16 016 22:03
 * @version: 1.0
 */


public class 交替链表 {
    /*
    题目描述
    给定一个单链表 L：L0→L1→…→Ln-1→Ln ，将其重新排列后变为：L0→Ln→L1→Ln-1→L2→Ln-2→…
    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    示例1:
    给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
    示例2：
    给定链表 1->2->3->4, 重新排列为 1->4->2->3.
    */

    public static ListNode reorderList(ListNode head) {

        // 1、使用快慢指针寻找出链表的中点来
        ListNode mid = middleNode(head);

        // 2、基于 mid 这个中点，将链表划分为两个区域

        // 左边的区域开头节点是 head
        ListNode leftHead = head;

        // 右边的区域开头节点是 mid.next
        ListNode rightHead = mid.next;

        // 将链表断开，就形成了两个链表了
        mid.next = null;

        // 3、将右边的链表进行反转
        rightHead = reverseList(rightHead);

        // 4、将这两个链表进行合并操作，即进行【交错拼接】
        return mergeTwoLists(leftHead, rightHead, true, false);

    }

    /**
     * LeetCode 21 : 合并两个有序链表
     *
     * @param isAlternateMerge    是否为交替合并
     * @param isCompareValueMerge 是否为根据值大小合并合并
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2, boolean isAlternateMerge, boolean isCompareValueMerge) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        boolean flag1 = true;
        while (l1 != null && l2 != null) {
            if (isAlternateMerge) {
                if (flag1) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
                flag1 = !flag1;
            } else if (isCompareValueMerge) {
                if (l1.val < l2.val) {
                    node.next = l1;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    l2 = l2.next;
                }
            }
            node = node.next;
        }
        if (l1 == null) {
            node.next = l2;
        } else {
            node.next = l1;
        }
        return dummy.next;
    }

    /**
     * LeetCode 876 : 链表的中间节点
     */
    public static ListNode middleNode(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * LeetCode 206 : 反转链表
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }


    // 辅助方法：打印链表
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {
        // 构建链表 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        System.out.println("原始链表:");
        printList(head);

        ListNode listNode = reorderList(head);

        System.out.println("重新排列后的链表:");
        printList(listNode);
    }
}
