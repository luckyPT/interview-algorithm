package com.pt.leetcode.recursion;

import com.pt.list.SNode;

/**
 * 借助递归实现链表反转
 *
 * @author panTeng
 */
public class ListReverse {
    public SNode<Integer> reverse(SNode<Integer> head, SNode<Integer> pre) {
        if (head.next == null) {
            head.next = pre;
            return head;
        }
        SNode<Integer> next = head.next;
        head.next = pre;
        return reverse(next, head);
    }

    public static void main(String[] args) {
        SNode<Integer> node1 = new SNode<Integer>(1);
        SNode<Integer> node2 = new SNode<Integer>(2);
        SNode<Integer> node3 = new SNode<Integer>(3);
        SNode<Integer> node4 = new SNode<Integer>(4);
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        SNode<Integer> newHead = new ListReverse().reverse(node1, null);
        System.out.println(newHead);
    }
}
