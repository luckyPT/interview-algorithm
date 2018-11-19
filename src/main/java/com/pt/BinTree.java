package com.pt;

import java.util.ArrayDeque;
import java.util.Queue;

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


    public static void main(String[] args) {
        TreeNode root = createSearchTree(new int[]{1, 6, 3, 2, 4, 7, 5, 9, 10, 8});
        DoubleDirectionLinkNode linkRootNode = treeToLink(root);
        System.out.println(linkRootNode);
    }
}
