package com.pt;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BinTree {
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }
    }

    static class DoubleDirectionLinkNode {
        int data;
        DoubleDirectionLinkNode pre;
        DoubleDirectionLinkNode next;

        public DoubleDirectionLinkNode(int data) {
            this.data = data;
        }
    }

    static TreeNode createCompleteTree(TreeNode[] treeNodes) {
        for (int i = 0; i < treeNodes.length / 2; i++) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
            if (leftIndex < treeNodes.length) {
                treeNodes[i].left = treeNodes[leftIndex];
            }
            if (rightIndex < treeNodes.length) {
                treeNodes[i].right = treeNodes[rightIndex];
            }
        }
        return treeNodes[0];
    }

    /**
     * 基于数组构造二叉搜索树
     *
     * @param array
     * @return
     */
    static TreeNode createSearchTree(int[] array) {
        TreeNode root = null;
        for (int i = 0; i < array.length; i++) {
            root = insertToSearchTree(root, array[i]);
        }
        return root;
    }

    /**
     * 二叉搜索树插入元素，递归实现
     *
     * @param root
     * @param data
     * @return
     */
    static TreeNode insertToSearchTree(TreeNode root, int data) {
        if (root == null) return new TreeNode(data);
        if (root.data > data) {
            root.left = insertToSearchTree(root.left, data);
        } else {
            root.right = insertToSearchTree(root.right, data);
        }
        return root;
    }

    /**
     * 二叉搜索树转有序双向链表
     * 借助中序遍历实现
     *
     * @param root
     * @return
     */
    static DoubleDirectionLinkNode treeToLink(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        addQueue(root, queue);
        DoubleDirectionLinkNode linkRootNode = null;
        DoubleDirectionLinkNode pre = null;
        while (queue.size() > 0) {
            if (pre == null) {
                pre = new DoubleDirectionLinkNode(queue.poll().data);
                linkRootNode = pre;
            } else {
                DoubleDirectionLinkNode tmp = new DoubleDirectionLinkNode(queue.poll().data);
                pre.next = tmp;
                tmp.pre = pre;
                pre = tmp;
            }
        }
        return linkRootNode;
    }

    static void addQueue(TreeNode treeNode, Queue<TreeNode> queue) {
        if (treeNode == null) return;
        addQueue(treeNode.left, queue);
        queue.add(treeNode);
        addQueue(treeNode.right, queue);
    }

    /**
     * 按照逆时针输出边界结点
     * 边界结点：每一层第一个和最后一个出现的结点以及叶子结点
     * 要求：时间复杂度n，空间复杂度h （n为树节点个数，h为树的高度）
     * 思路：分别遍历左边界，最后一层的叶子结点，右边界;注意最后一层的
     * 叶结点的第一个和最后一个，不要重复遍历
     *
     * @param node
     */
    static void printEdgeAnticlockwise1(TreeNode node) {
        int height = getMaxHeight(node);
        TreeNode[][] leftRight = new TreeNode[height][2];
        getLeftRightEdge(node, 1, leftRight);
        //打印左边界
        for (int i = 0; i < leftRight.length - 1; i++) {
            System.out.println(leftRight[i][0].data);
        }
        //打印叶节点
        lastHLeaf(node, height, 1);
        //打印右边界
        for (int i = leftRight.length - 2; i > 0; i--) {
            if (leftRight[i][0] != leftRight[i][1]) {
                System.out.println(leftRight[i][1].data);
            }
        }
    }

    /**
     * 获取左右边界
     * 思路：按照广度遍历的思路，遍历到第n层的某个节点时，
     * 如果leftRightNode[n-1][0]为null
     * - 将这个结点赋予leftRightNode[n-1][0] ；
     * 否则不做处理；
     * 将这个结点赋值给leftRightNode[n-1][1]
     * <p>
     * 需要注意，当某层只有一个结点时，leftRightNode[n-1][0]和leftRightNode[n-1][1]会是同一个结点
     *
     * @param node
     * @param h
     * @param leftRightNode
     */
    static void getLeftRightEdge(TreeNode node, int h, TreeNode[][] leftRightNode) {
        if (node == null) return;
        if (leftRightNode[h - 1][0] == null) {
            leftRightNode[h - 1][0] = node;
        }
        leftRightNode[h - 1][1] = node;
        getLeftRightEdge(node.left, h + 1, leftRightNode);
        getLeftRightEdge(node.right, h + 1, leftRightNode);
    }

    static void lastHLeaf(TreeNode node, int height, int curH) {
        if (node == null) return;
        lastHLeaf(node.left, height, curH + 1);
        lastHLeaf(node.right, height, curH + 1);
        if (curH == height) {
            System.out.println(node.data);
        }
    }

    static int getMaxHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHight = getMaxHeight(node.left);
        int rightHight = getMaxHeight(node.right);
        return Math.max(leftHight, rightHight) + 1;
    }

    /**
     * 根算作第一层，奇数层从左向右打印，偶数层从右向左打印
     *
     * @param root
     */
    static void zigZagPrint(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.empty() || !stack2.empty()) {
            while (!stack1.empty()) {
                TreeNode node = stack1.pop();
                System.out.print(node.data + ",");
                if (node.left != null) {
                    stack2.push(node.left);
                }
                if (node.right != null) {
                    stack2.push(node.right);
                }
            }
            System.out.println();
            while (!stack2.empty()) {
                TreeNode node = stack2.pop();
                System.out.print(node.data + ",");
                if (node.right != null) {
                    stack1.push(node.right);
                }
                if (node.left != null) {
                    stack1.push(node.left);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeNode root = createSearchTree(new int[]{1, 6, 3, 2, 4, 7, 5, 9, 10, 8});
        DoubleDirectionLinkNode linkRootNode = treeToLink(root);
        System.out.println(linkRootNode);

        TreeNode[] nodes = {new TreeNode(1), new TreeNode(2), new TreeNode(3), new TreeNode(4),
                new TreeNode(5), new TreeNode(6), new TreeNode(7), new TreeNode(8), new TreeNode(9),
                new TreeNode(10), new TreeNode(11), new TreeNode(12), new TreeNode(13), new TreeNode(14),
                new TreeNode(15), new TreeNode(16)};
        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];
        nodes[1].right = nodes[3];
        nodes[2].left = nodes[4];
        nodes[2].right = nodes[5];
        nodes[3].left = nodes[6];
        nodes[3].right = nodes[7];
        nodes[4].left = nodes[8];
        nodes[4].right = nodes[9];
        nodes[7].right = nodes[10];
        nodes[8].left = nodes[11];
        nodes[10].left = nodes[12];
        nodes[10].right = nodes[13];
        nodes[11].left = nodes[14];
        nodes[11].right = nodes[15];

        printEdgeAnticlockwise1(nodes[0]);

        zigZagPrint(nodes[0]);
    }
}
