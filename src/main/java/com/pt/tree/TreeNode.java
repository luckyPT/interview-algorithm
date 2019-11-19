package com.pt.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.val = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static void postOrderLike(TreeNode node, int h, int singleSpaceCount) {
        if (node == null) return;
        postOrderLike(node.right, h + 1, singleSpaceCount);
        printlnOneData(node.val, h, singleSpaceCount);
        postOrderLike(node.left, h + 1, singleSpaceCount);

    }

    static void printlnOneData(int data, int preSpaceCount, int singleSpaceCount) {
        for (int i = 0; i < preSpaceCount; i++) {
            for (int j = 0; j < singleSpaceCount; j++) {
                System.out.print(" ");
            }
        }
        int length = ("" + data).length();
        System.out.print(data);
        for (int i = 0; i < singleSpaceCount - length; i++) {
            System.out.print(' ');
        }
        System.out.println();
    }

    /**
     * 自底向上层次遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root==null){
            return ret;
        }
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        Stack<Pair> stack = new Stack<>();
        queue.addLast(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.removeFirst();
            stack.push(pair);
            if (pair.node.right != null)
                queue.addLast(new Pair(pair.node.right, pair.level + 1));
            if (pair.node.left != null)
                queue.addLast(new Pair(pair.node.left, pair.level + 1));
        }
        int level = -1;
        List<Integer> oneLevel = new ArrayList<>();
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            if (level == -1) {
                level = pair.level;
                oneLevel.add(pair.node.val);
            } else {
                if (level == pair.level) {
                    oneLevel.add(pair.node.val);
                } else {
                    ret.add(oneLevel);
                    level = pair.level;
                    oneLevel = new ArrayList<>();
                    oneLevel.add(pair.node.val);
                }
            }
        }
        ret.add(oneLevel);
        return ret;
    }

    static class Pair {
        public TreeNode node;
        public int level;

        public Pair(TreeNode n, int l) {
            this.node = n;
            this.level = l;
        }
    }

    public static void main(String[] args) {
        TreeNode root = BinTree.createSearchTree(new int[]{1, 6, 3, 2, 4, 7, 5, 9, 10, 8});
        //TreeNode.postOrderLike(root, 0, 5);
        levelOrderBottom(root);
    }
}
