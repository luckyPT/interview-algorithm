package com.pt.leetcode.list;

import com.pt.list.SNode;

/**
 * leetCode 142 检测环形链表
 * 证明：https://www.cnblogs.com/ranranblog/p/5587079.html
 *
 * @author panTeng
 */
public class DetectCycle {
    public SNode<Integer> detectCycle(SNode<Integer> head) {
        if (head == null || head.next == null) {
            return null;
        }
        SNode<Integer> slow = head;
        SNode<Integer> fast = head;
        SNode<Integer> meetNode = null;
        //检测相遇点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                meetNode = slow;
                break;
            }
        }
        if (meetNode == null) {//没有环
            return null;
        }
        //slow回到起点，相遇点与slow同时1步1步前进，直到相遇即为入环点
        slow = head;
        while (true) {
            if (slow == meetNode) {
                return slow;
            }
            slow = slow.next;
            meetNode = meetNode.next;
        }
    }

    public static void main(String[] args) {

    }
}
