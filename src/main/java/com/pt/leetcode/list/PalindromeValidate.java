package com.pt.leetcode.list;

import com.pt.list.SNode;

/**
 * 判断链表是否为回文 ，要求时间复杂度为O(n),空间复杂度O(1)
 * leetCode 234
 *
 * @author panTeng
 */
public class PalindromeValidate {
    /**
     * 此种方法改变了原始链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(SNode<Integer> head) {
        if (head == null || head.next == null) {//空 或者 只有一个元素，认为是回文
            return true;
        }
        SNode<Integer> fast = head;
        SNode<Integer> slow = head;
        SNode<Integer> slow_pre = null;
        SNode<Integer> slow_next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            //反转
            slow_next = slow.next;
            slow.next = slow_pre;
            slow_pre = slow;
            slow = slow_next;
        }
        //判断奇偶
        if (fast.next == null) {//奇数
            SNode<Integer> head1 = slow_pre;
            SNode<Integer> head2 = slow.next;
            while (head1 != null && head2 != null) {
                if (!head1.data.equals(head2.data)) {
                    return false;
                }
                head1 = head1.next;
                head2 = head2.next;
            }
            return true;
        } else {//偶数
            SNode<Integer> head1 = slow;
            SNode<Integer> head2 = slow.next;
            slow.next = slow_pre;//这句话放到SNode<Integer> head1 = slow; 之前就会出错
            while (head1 != null && head2 != null) {
                if (!head1.data.equals(head2.data)) {
                    return false;
                }
                head1 = head1.next;
                head2 = head2.next;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        SNode<Integer> node1 = new SNode<Integer>(1);
        SNode<Integer> node2 = new SNode<Integer>(2);
        node1.next = node2;

        System.out.println(new PalindromeValidate().isPalindrome(node1));
    }
}
