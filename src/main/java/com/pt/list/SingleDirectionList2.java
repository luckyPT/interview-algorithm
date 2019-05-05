package com.pt.list;

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
     * 链表翻转，每K个元素进行翻转，最后不足K个不翻转
     * 如：1,2,3,4,5,6,7,8 k=3 翻转之后3,2,1,6,5,4,7,8
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

    /**
     * 递归对链表进行反转
     *
     * @param pre
     * @param cur
     * @return
     */
    public static Node reverseListByRecursion(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }
        Node tmpNext = cur.next;
        cur.next = pre;
        return reverseListByRecursion(cur, tmpNext);
    }

    /**
     * 对单链表进行选择排序(借助栈或者队列或许思路更清晰一些)
     * 思路：
     * 0.找到最小结点
     * 1.删除
     * 2.将上一最小结点指向此时的最小结点
     * 3.将最小结点添加到链表头部
     * 如果原先头部仍然有下一个元素，就从0开始循环
     * <p>
     * 需要注意的是，有时候头部结点就是最小结点，这时候删除元素之后，链表的头部结点将发生改变
     *
     * @param root
     * @return
     */
    static Node selectSort(Node root) {
        Node tmpRoot = root;
        Node tail = null;
        Node retHead = null;
        while (tmpRoot.next != null) {
            Node leastNode = tmpRoot;
            Node preLeastNode = null;
            Node pre = tmpRoot;
            Node tmp = pre.next;
            while (tmp != null) {
                if (leastNode.data > tmp.data) {
                    leastNode = tmp;
                    preLeastNode = pre;
                }
                pre = tmp;
                tmp = tmp.next;
            }
            tmpRoot = removeNextNode(tmpRoot, preLeastNode, leastNode);
            if (tail == null) {
                tail = addToHead(tmpRoot, leastNode);
                retHead = tail;
            } else {
                tail.next = leastNode;
                tail = addToHead(tmpRoot, leastNode);
            }
        }
        return retHead;
    }

    /**
     * @param head    链表头部结点
     * @param preNode 要删除的结点的前一个结点
     * @param node    要删除的结点
     * @return 返回删除后链表的头结点
     */
    static Node removeNextNode(Node head, Node preNode, Node node) {
        if (node == null) return head;
        if (preNode == null) return node.next;
        preNode.next = preNode.next.next;
        return head;
    }

    /**
     * 将head加入到链表头
     *
     * @param node
     * @param head
     * @return
     */
    static Node addToHead(Node node, Node head) {
        head.next = node;
        return head;
    }

    /**
     * 交换node1 和 node2的下一个结点
     *
     * @param node1
     * @param node2
     */
    static void swapNextNode(Node node1, Node node2) {

    }

    public static void main(String[] args) {
        Node[] nodes = {new Node(11), new Node(6), new Node(3), new Node(4),
                new Node(9), new Node(2), new Node(7), new Node(8),
                new Node(4), new Node(10)};
        Node.createList(nodes);
        //reverseKByRecursive(nodes[0], 2, null);
        //reverseKByStack(nodes[0], 5);
        //Node head = selectSort(nodes[0]);
        //System.out.println(nodes[0].data);

        Node last = reverseListByRecursion(null, nodes[0]);
        System.out.println(last.data);
    }
}
