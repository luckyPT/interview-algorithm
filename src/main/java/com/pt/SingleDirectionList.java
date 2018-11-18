package com.pt;

import java.util.HashSet;
import java.util.Set;

public class SingleDirectionList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断链表是否有环，有则返回第一个入环节点，没有则返回null
     * 关键点：快指针第一次遇见null，无环。快慢指针第一次相遇，则有环，之后快指针
     * 回到起点，快慢指针同时走，每次均走一步，第一次相遇点就是入环点
     *
     * @param node
     * @return
     */
    static Node hasLoop(Node node) {
        if (node == null || node.next == null) return null;
        Node fast = node.next.next;
        Node slow = node.next;
        while (fast != null && fast.next != null) {
            if (slow.equals(fast)) {//先判断是否有环，在继续走
                break;
            } else {
                fast = fast.next.next;
                slow = slow.next;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        } else {
            fast = node;
            while (!fast.equals(slow)) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }

    /**
     * 使用Set实现，需要注意"!nodes.contains(node)"条件,否则在有环的情况下会出现死循环
     * 使用Set实现时间和空间复杂度是O(n)
     *
     * @param node
     * @return
     */
    static int lenBySet(Node node) {
        Set<Node> nodes = new HashSet<>();
        while (node != null && !nodes.contains(node)) {
            nodes.add(node);
            node = node.next;
        }
        return nodes.size();
    }

    /**
     * 借助快慢指针，第一次相遇之后
     *
     * @param node
     * @return
     */
    static int lenByPointer(Node node) {
        if (node == null) return 0;
        if (node.next == null) return 1;
        int len = 1;
        Node slow = node.next;
        Node fast = node.next.next;
        while (fast != null && fast.next != null) {
            if (fast.equals(slow)) {//先判断是否有环 再继续走
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
            len++;
        }
        if (fast == null) {
            return len * 2;
        } else if (fast.next == null) {
            return len * 2 + 1;
        } else {//有环
            fast = node;
            while (!fast.equals(slow)) {//找到入口点
                fast = fast.next;
                slow = slow.next;
            }
            fast = node;
            len = 1;
            int times = 0;
            while (true) {//第二次到达入口点的时候就是走了一遍多1个
                if (fast.equals(slow)) {
                    times++;
                    if (times >= 2) {
                        break;
                    }
                }
                fast = fast.next;
                len++;
            }
            return len - 1;
        }

    }

    static Node nonLoopIntersection(Node node1, Node node2) {
        Node node1End = node1;
        Node node2End = node2;
        int node1Size = 1;
        int node2Size = 1;
        while (node1End.next != null) {
            node1End = node1End.next;
            node1Size++;
        }
        while (node2End.next != null) {
            node2End = node2End.next;
            node2Size++;
        }

        if (node1End.equals(node2End)) {//有交点
            int diff = node1Size - node2Size;
            Node node1Start = node1;
            Node node2Start = node2;
            if (diff > 0) {
                while (diff != 0) {
                    node1Start = node1Start.next;
                    diff--;
                }
            }
            if (diff < 0) {
                while (diff != 0) {
                    node2Start = node2Start.next;
                    diff++;
                }
            }
            while (!node1Start.equals(node2Start)) {
                node1Start = node1Start.next;
                node2Start = node2Start.next;
            }
            return node1Start;
        } else {//无交点
            return null;
        }
    }

    static Node isIntersection(Node node1, Node node2) {
        if (node1 == null || node2 == null) return null;
        Node node1LoopStart = hasLoop(node1);
        Node node2LoopStart = hasLoop(node2);
        if (node1LoopStart == null && node2LoopStart == null) {//两个无环列表如果相交，交点之后的元素一定是共享的（两个列表最后一个元素一定相等）
            return nonLoopIntersection(node1, node2);
        }
        return null;
    }

    public static void main(String[] args) {
        Node[] nodes1 = {new Node(1), new Node(2), new Node(3), new Node(4)};
        Node[] nodes2 = {new Node(1), new Node(2), new Node(3), new Node(4)};
        createList(nodes1);
        //nodes1[nodes1.length - 1].next = nodes1[1];
        System.out.println("len:" + lenBySet(nodes1[0]) + ":" + lenByPointer(nodes1[0]));
        System.out.println("loopStart:" + (null == hasLoop(nodes1[0]) ? "null" : hasLoop(nodes1[0]).data));

        createList(nodes2);
        System.out.println("isInterSection:" + (isIntersection(nodes1[0], nodes2[0]) != null ? isIntersection(nodes1[0], nodes2[0]).data : "null"));
        nodes2[3].next = nodes1[2];
        Node intersection = isIntersection(nodes1[0], nodes2[0]);
        System.out.println("isIntersection:" + (intersection != null ? intersection.data : "null"));

    }

    static void createList(Node[] nodes) {
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
    }
}
