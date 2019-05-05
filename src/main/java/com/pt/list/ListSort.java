package com.pt.list;

public class ListSort {

    /**
     * 要排序的两部分应该解除连接（不能让第一部分某个元素指向第二部分的某个元素）
     * 需要注意两个相邻元素排序中可能出现死循环
     *
     * @param head
     * @return
     */
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head; //当只有一个元素的时候
        }
        ListNode midNode = getMidNode(head);
        ListNode rightNodes = midNode.next;
        midNode.next = null;
        ListNode newLeftHead = mergeSort(head);
        ListNode newRightHead = mergeSort(rightNodes);
        return binMerge(newLeftHead, newRightHead);
    }

    /**
     * 如果有n个元素，则返回第n/2 - 1个元素（从0开始计数）
     *
     * @param head
     * @return
     */
    public static ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null)//返回第n/2 - 1个元素，防止出现两个相邻元素排序，死循环情况
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

    public ListNode selectSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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
                ListNode smallest = smallestPre.next;//注意，这里必须保存smallest，否则执行完swap之后smallestPre.next就不是smallest了
                swapNode(head, pre, smallest, smallestPre);
                pre = smallest;
                head = pre.next;
            }

        }
        return ret;

    }

    public static ListNode getSmallPre(ListNode head) {
        if (head == null || head.next == null) {
            //易错点,容易写成 if(head==null || head.next==null){ return head; }，这就会导致只有一个元素的时候，pre的返回不是null
            return null;
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
        //考虑两个元素相邻的情况
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
            node1.next = node2Next;
            node2.next = node1Next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode smallest = mergeSort(node1);
        smallest.println();
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public void println() {
            ListNode t = this;
            while (t != null) {
                System.out.print(t.val);
                t = t.next;
            }
            System.out.println();
        }
    }
}
