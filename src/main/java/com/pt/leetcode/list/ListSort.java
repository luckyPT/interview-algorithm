package com.pt.leetcode.list;

public class ListSort {
    public ListNode sortList(ListNode head) {
        ListNode end = head;
        while (end.next != null) {
            end = end.next;
        }
        return mergeSort(head, end);
    }

    public ListNode mergeSort(ListNode start, ListNode end) {
        if (start == end) {
            return start;
        }
        ListNode midNode = getMid(start);
        ListNode rightStart = midNode.next;
        midNode.next = null;
        ListNode left = mergeSort(start, midNode);
        ListNode right = mergeSort(rightStart, end);
        return binMerge(left, right);
    }

    public ListNode getMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {//注意返回第(n+1)/2 - 1个元素（从0开始计数）
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static ListNode binMerge(ListNode leftNode, ListNode rightNode) {
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        ListNode tmpNode;
        if (leftNode.val <= rightNode.val) {
            tmpNode = leftNode;
            leftNode = leftNode.next;
        } else {
            tmpNode = rightNode;
            rightNode = rightNode.next;
        }
        tmpNode.next = null;
        ListNode ret = tmpNode;

        while (true) {
            if (leftNode == null) {
                tmpNode.next = rightNode;
                break;
            }
            if (rightNode == null) {
                tmpNode.next = leftNode;
                break;
            }
            if (leftNode.val <= rightNode.val) {
                tmpNode.next = leftNode;
                leftNode = leftNode.next;
                tmpNode = tmpNode.next;
                tmpNode.next = null;
            } else {
                tmpNode.next = rightNode;
                rightNode = rightNode.next;
                tmpNode = tmpNode.next;
                tmpNode.next = null;
            }
        }

        return ret;
    }

    public static ListNode selectSort(ListNode head) {
        ListNode ret = null;
        ListNode pre = null;
        while (head != null) {
            ListNode smallestPre = getSmallPre(head);
            if (ret == null) {
                ret = smallestPre == null ? head : smallestPre.next;
            }

            if (smallestPre == null) {
                pre = head;
                head = head.next;
            } else {
                ListNode smallest = smallestPre.next;//必须在此记录最小值，swap之后smallestPre.next就不再是最小值
                swapNode(head, pre, smallestPre.next, smallestPre);
                pre = smallest;
                head = pre.next;
            }

        }
        return ret;
    }

    public static ListNode getSmallPre(ListNode head) {
        if (head == null || head.next == null) {
            return null;//不可以返回head
        }
        ListNode pre = null;
        ListNode smallest = head;
        while (head.next != null) {
            if (smallest.val > head.next.val) {
                smallest = head.next;
                pre = head;
            }

            head = head.next;
        }

        return pre;
    }

    public static void swapNode(ListNode node1, ListNode preNode1, ListNode node2, ListNode preNode2) {
        if (node1.next == node2) {
            if (preNode1 != null) {
                preNode1.next = node2;
            }
            node1.next = node2.next;
            node2.next = node1;
        } else if (node2.next == node1) {
            if (preNode2 != null) {
                preNode2.next = node1;
            }
            node2.next = node1.next;
            node1.next = node2;
        } else {
            ListNode node1Next = node1.next;
            ListNode node2Next = node2.next;
            if (preNode1 != null) {
                preNode1.next = node2;
            }
            if (preNode2 != null) {
                preNode2.next = node1;
            }
            node2.next = node1Next;
            node1.next = node2Next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(3);
        ListNode node = new ListSort().sortList(node1);
        System.out.println(node);

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
