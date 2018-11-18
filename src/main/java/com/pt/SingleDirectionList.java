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
     * 借助快慢指针，找到入口点，然后从头遍历并且计数，直到第二次走到入口点；
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

    /**
     * 在endNode1和endNode2之前是否相交（不包含endNode1和endNode2）
     *
     * @param node1
     * @param node2
     * @param endNode1
     * @param endNode2
     * @return
     */
    static Node nonLoopIntersection(Node node1, Node node2, Node endNode1, Node endNode2) {
        Node node1End = node1;
        Node node2End = node2;
        int node1Size = 1;
        int node2Size = 1;
        while (node1End.next != endNode1) {
            node1End = node1End.next;
            node1Size++;
        }
        while (node2End.next != endNode2) {
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

    /**
     * 两个无环链表如果相交，交点之后的元素一定是共享的（两个链表最后一个元素一定相等，否则无交点）
     * 确定有交点之后，根据长度，对于较长的链表先走一些距离，当剩下距离与短链表相同时，
     * 两个同时走，知道碰见相同的元素
     *
     * @param node1
     * @param node2
     * @return
     */
    static Node nonLoopIntersection(Node node1, Node node2) {
        return nonLoopIntersection(node1, node2, null, null);
    }

    /**
     * 关键点：先判断入环之前是否相交，在判断在入环出是否相交，最后判断环内是否
     * 相交，顺序不可乱；（如果在入环前相交，那么后面每个都是交点，所以后面判断
     * 逻辑得出的相等点不是第一个交点）
     * @param node1
     * @param node2
     * @return
     */
    static Node loopIntersection(Node node1, Node node2) {
        Node inNode1 = hasLoop(node1);
        Node inNode2 = hasLoop(node2);
        Node preIntersectionNode = nonLoopIntersection(node1, node2, inNode1, inNode2);
        if (preIntersectionNode != null) {
            return preIntersectionNode;
        }
        if (inNode1.equals(inNode2)) {//是否在入口点相交
            return inNode1;
        }
        Node node1Start = node1.next;
        while (!node1Start.equals(node1)) {
            if (node1Start.equals(inNode2)) {
                return node1Start;
            }
            node1Start = node1Start.next;
        }
        return null;
    }

    /**
     * 判断两个链表是否相交，两个链表可能有环，可能无环
     * 分两种情况：两个都有环和两个都无环；
     * 一个有环一个无环的链表不可能相交
     * @param node1
     * @param node2
     * @return
     */
    static Node isIntersection(Node node1, Node node2) {
        if (node1 == null || node2 == null) return null;
        Node node1LoopStart = hasLoop(node1);
        Node node2LoopStart = hasLoop(node2);
        if (node1LoopStart == null && node2LoopStart == null) {
            return nonLoopIntersection(node1, node2);
        }
        if (node1LoopStart != null && node2LoopStart != null) {
            return loopIntersection(node1, node2);
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

        //有环相交
        Node[] nodes3 = {new Node(1), new Node(2), new Node(3), new Node(4),
                new Node(5), new Node(6)};
        Node[] nodes4 = {new Node(1), new Node(2), new Node(3), new Node(4),
                new Node(5), new Node(6)};
        createList(nodes3);
        createList(nodes4);
        nodes3[nodes3.length - 1].next = nodes3[3];
        nodes4[1].next = nodes3[4];
        Node intersectionNode = isIntersection(nodes3[0], nodes4[0]);
        System.out.println("loopIntersection:" + (intersectionNode == null ? "null" : intersectionNode.data));
    }

    static void createList(Node[] nodes) {
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
    }
}
