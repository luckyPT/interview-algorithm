package com.pt;

import java.util.*;

public class BinSearchTree {
    static boolean isSearchTree(BinTree.TreeNode root) {

        return false;
    }

    /**
     * @param root
     * @param results 0-size  1-maxValue 2-minValue
     * @return
     */
    static BinTree.TreeNode getMaxSearchTree2(BinTree.TreeNode root, int[] results) {
        if (root == null) {
            results[0] = 0;
            results[1] = Integer.MIN_VALUE;//特别注意此处的赋值
            results[2] = Integer.MAX_VALUE;
            return null;
        }
        BinTree.TreeNode left = getMaxSearchTree2(root.left, results);
        int lsize = results[0];
        int lmaxValue = results[1];
        int lminValue = results[2];
        BinTree.TreeNode right = getMaxSearchTree2(root.right, results);
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
    static BinTree.TreeNode getMaxSearchTree(BinTree.TreeNode root) {
        if (root == null) return null;
        Stack<BinTree.TreeNode> stack = new Stack<>();
        BinTree.TreeNode curNode = root;
        int min = Integer.MAX_VALUE;
        List<BinTree.TreeNode> lastQueue = new LinkedList<>();
        List<BinTree.TreeNode> curQueue = new LinkedList<>();
        while (!stack.empty() || curNode != null) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                BinTree.TreeNode node = stack.pop();
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
        BinTree.TreeNode subRoot = null;
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
        BinTree.TreeNode[] nodes = new BinTree.TreeNode[]{new BinTree.TreeNode(6),
                new BinTree.TreeNode(1), new BinTree.TreeNode(12), new BinTree.TreeNode(0),
                new BinTree.TreeNode(3), new BinTree.TreeNode(10), new BinTree.TreeNode(13)};
        BinTree.TreeNode[] nodes1 = new BinTree.TreeNode[]{nodes[5], new BinTree.TreeNode(4),
                new BinTree.TreeNode(14), new BinTree.TreeNode(2), new BinTree.TreeNode(5),
                new BinTree.TreeNode(11), new BinTree.TreeNode(15)};
        BinTree.TreeNode[] nodes2 = new BinTree.TreeNode[]{nodes[6], new BinTree.TreeNode(20),
                new BinTree.TreeNode(16)};

        BinTree.createCompleteTree(nodes);
        BinTree.createCompleteTree(nodes1);
        BinTree.createCompleteTree(nodes2);

        System.out.println(getMaxSearchTree(nodes[0]).data);
        System.out.println(getMaxSearchTree2(nodes[0], new int[3]).data);
    }
}
