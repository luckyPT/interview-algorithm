package com.pt;

import java.util.Stack;

public class SingleDirectionList2 {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }

        static void createList(Node[] nodes) {
            for (int i = 0; i < nodes.length - 1; i++) {
                nodes[i].next = nodes[i + 1];
            }
        }
    }

    /**
     * 涉及到逆序可考虑栈结构
     *
     * @param node
     * @param k
     */
    static void reverseKByStack(Node node, int k) {
        Stack<Node> nodeStack = new Stack<>();
        Node last = null;
        while (node != null) {
            while (nodeStack.size() < k && node != null) {
                nodeStack.push(node);
                node = node.next;
            }
            if (nodeStack.size() >= k) {
                Node tmp = nodeStack.pop();
                if (last != null) {
                    last.next = tmp;
                }
                while (nodeStack.size() != 0) {
                    Node next = nodeStack.pop();
                    tmp.next = next;
                    tmp = next;
                }
                last = tmp;
                last.next = null;//必须注意，否则会循环引用
            } else {
                Node head = nodeStack.pop();
                while (nodeStack.size() != 0) {
                    head = nodeStack.pop();
                }
                if (last != null) {
                    last.next = head;
                }
            }
        }
    }

    /**
     * 链表翻转，每K个元素进行翻转，最后不足K个不翻转
     * 如：1,2,3,4,5,6,7,8 k=3 翻转之后3,2,1,6,5,4,7,8
     *
     * @param node
     * @param k
     */
    static void reverseKByRecursive(Node node, int k, Node lastEnd) {
        if (node == null) return;
        Node countNode = node;
        int remain = 1;
        while (countNode.next != null && remain < k) {
            countNode = countNode.next;
            remain++;
        }
        if (remain < k) {
            if (lastEnd != null) {
                lastEnd.next = node;
            }
            return;
        } else {
            remain = 1;
            Node pre = node;
            Node cur = node.next;
            Node next = null;
            while (remain < k) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                remain++;
            }
            node.next = null;//必须注意，否则会循环引用
            if (lastEnd != null) {
                lastEnd.next = pre;
            }
            reverseKByRecursive(next, k, node);
        }
    }

    public static void main(String[] args) {
        Node[] nodes = {new Node(1), new Node(2), new Node(3), new Node(4),
                new Node(5), new Node(6), new Node(7), new Node(8),
                new Node(9), new Node(10)};
        Node.createList(nodes);
        //reverseKByRecursive(nodes[0], 2, null);
        reverseKByStack(nodes[0], 5);
        System.out.println(nodes[0].data);
    }
}
