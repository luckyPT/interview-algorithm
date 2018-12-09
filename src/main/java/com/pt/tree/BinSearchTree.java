package com.pt.tree;

import com.pt.tree.BinTree;

import java.util.*;

public class BinSearchTree {
    static boolean isSearchTree(TreeNode root) {

        return false;
    }

    /**
     * @param root
     * @param results 0-size  1-maxValue 2-minValue
     * @return
     */
    static TreeNode getMaxSearchTree2(TreeNode root, int[] results) {
        if (root == null) {
            results[0] = 0;
            results[1] = Integer.MIN_VALUE;//特别注意此处的赋值
            results[2] = Integer.MAX_VALUE;
            return null;
        }
        TreeNode left = getMaxSearchTree2(root.left, results);
        int lsize = results[0];
        int lmaxValue = results[1];
        int lminValue = results[2];
        TreeNode right = getMaxSearchTree2(root.right, results);
        int rsize = results[0];
        int rmaxValue = results[1];
        int rminValue = results[2];

        if (root.data > lmaxValue && root.data < rminValue) {
            results[0] = lsize + rsize + 1;
            if (right != null) {
                results[1] = Math.max(rmaxValue, root.data);
            } else {
                results[1] = Math.max(root.data, lmaxValue);
            }

            if (left != null) {
                results[2] = Math.min(root.data, lminValue);
            } else {
                results[2] = Math.min(root.data, rminValue);
            }

            return root;
        } else {
            results[1] = Math.max(Math.max(rmaxValue, lmaxValue), root.data);
            results[2] = Math.min(Math.min(rminValue, lminValue), root.data);

            if (rsize > lsize) {
                results[0] = rsize;
                return right;
            } else {
                results[0] = lsize;
                return left;
            }
        }
    }

    /**
     * 找到一棵树中最大搜索二叉子树的根节点（最大二叉搜索子树指的是节点最多的子树）
     * 目前下面的方法有个问题：当同时存在两个时，仅返回了一个
     *
     * @param root
     * @return
     */
    static TreeNode getMaxSearchTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        int min = Integer.MAX_VALUE;
        List<TreeNode> lastQueue = new LinkedList<>();
        List<TreeNode> curQueue = new LinkedList<>();
        while (!stack.empty() || curNode != null) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                TreeNode node = stack.pop();
                if (min > node.data) {
                    if (curQueue.size() > lastQueue.size()) {
                        lastQueue = curQueue;
                        curQueue.clear();
                    }
                } else {
                    curQueue.add(node);
                }
                min = node.data;
                curNode = node.right;
            }
        }
        if (curQueue.size() > lastQueue.size()) {
            lastQueue = curQueue;
        }
        TreeNode subRoot = null;
        for (int i = 1; i < lastQueue.size() - 1; i++) {
            if (lastQueue.get(i - 1).right == lastQueue.get(i) || lastQueue.get(i + 1).left == lastQueue.get(i)) {
                continue;
            } else {
                subRoot = lastQueue.get(i);
                break;
            }
        }

        return subRoot;
    }

    public static void main(String[] args) {
        TreeNode[] nodes = new TreeNode[]{new TreeNode(6),
                new TreeNode(1), new TreeNode(12), new TreeNode(0),
                new TreeNode(3), new TreeNode(10), new TreeNode(13)};
        TreeNode[] nodes1 = new TreeNode[]{nodes[5], new TreeNode(4),
                new TreeNode(14), new TreeNode(2), new TreeNode(5),
                new TreeNode(11), new TreeNode(15)};
        TreeNode[] nodes2 = new TreeNode[]{nodes[6], new TreeNode(20),
                new TreeNode(16)};

        BinTree.createCompleteTree(nodes);
        BinTree.createCompleteTree(nodes1);
        BinTree.createCompleteTree(nodes2);

        System.out.println(getMaxSearchTree(nodes[0]).data);
        System.out.println(getMaxSearchTree2(nodes[0], new int[3]).data);
    }
}
