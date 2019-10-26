package com.pt.leetcode.tree;

/**
 * leetCode 104.
 *
 * @author panTeng
 */
public class MaxDepth {
    public int maxDepth(TNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
