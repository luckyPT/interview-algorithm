package com.pt.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetCode 144,145
 */
public class Traversition {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ret.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return ret;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode cur = root;
        while (true) {
            while (cur != null) {
                rightStack.push(cur);
                if (cur.left != null) leftStack.push(cur.left);
                cur = cur.right;
            }
            if (!leftStack.isEmpty()) {
                cur = leftStack.pop();
            } else {
                break;
            }
        }
        List<Integer> ret = new ArrayList<>();
        while (!rightStack.isEmpty()) {
            ret.add(rightStack.pop().val);
        }
        return ret;
    }

    public static void main(String[] args) {

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
