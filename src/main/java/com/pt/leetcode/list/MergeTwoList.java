package com.pt.leetcode.list;

import com.pt.list.SNode;

/**
 * leetCode 21
 *
 * @author panTeng
 */
public class MergeTwoList {
    public SNode<Integer> mergeTwoLists(SNode<Integer> l1, SNode<Integer> l2) {
        if (l2 == null) return l1;
        if (l1 == null) return l2;
        SNode<Integer> p1 = l1;
        SNode<Integer> p2 = l2;
        SNode<Integer> newHead = null;
        SNode<Integer> newP = null;
        while (p1 != null && p2 != null) {
            if (p1.data > p2.data) {
                if (newHead == null) {
                    newHead = p2;
                    newP = newHead;
                } else {
                    newP.next = p2;
                    newP = p2;
                }
                p2 = p2.next;
            } else {
                if (newHead == null) {
                    newHead = p1;
                    newP = newHead;
                } else {
                    newP.next = p1;
                    newP = p1;
                }
                p1 = p1.next;
            }
        }
        SNode<Integer> remainP = p1 == null ? p2 : p1;
        while (remainP != null) {
            newP.next = remainP;
            newP = remainP;
            remainP = remainP.next;
        }
        return newHead;
    }


    public static void main(String[] args) {

    }
}
