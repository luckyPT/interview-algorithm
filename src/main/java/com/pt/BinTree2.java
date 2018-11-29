package com.pt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinTree2 {
    /**
     * 判断t1 是不是包含t2的全部拓扑结构
     *
     * @param t1
     * @param t2
     * @return
     */
    static boolean contains(BinTree.TreeNode t1, BinTree.TreeNode t2) {
        //先对t1进行遍历，找到与t2相等的结点
        List<BinTree.TreeNode> equalsNodes = new LinkedList<>();
        Stack<BinTree.TreeNode> stack = new Stack<>();
        stack.push(t1);
        while (!stack.empty()) {
            BinTree.TreeNode node = stack.pop();
            if (node.data == t2.data) {
                equalsNodes.add(node);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        //判断是否包含拓扑结构
        for (int i = 0; i < equalsNodes.size(); i++) {
            //对t2进行遍历，验证t1是否包含t2
            stack.clear();
            Stack<BinTree.TreeNode> stack1 = new Stack<>();
            stack.push(t2);
            stack1.push(equalsNodes.get(i));
            while (!stack.empty()) {
                if (stack1.empty()) {
                    break;
                }
                BinTree.TreeNode t2Node = stack.pop();
                BinTree.TreeNode t1Node = stack1.pop();
                if (t2Node.data != t1Node.data) {
                    break;
                }
                if (t2Node.right != null) {
                    if (t1Node.right != null) {
                        stack.push(t2Node.right);
                        stack1.push(t1Node.right);
                    } else {
                        break;
                    }
                }
                if (t2Node.left != null) {
                    if (t1Node.left != null) {
                        stack.push(t2Node.left);
                        stack1.push(t1Node.left);
                    } else {
                        break;
                    }
                }
            }
            if (stack.empty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这种递归在空间上有所浪费，如果递归中只关注指定索引区间内的元素，便可以不用创建多余数组
     *
     * @param preOrder
     * @param middleOrder
     * @return
     */
    static BinTree.TreeNode buildTree(int[] preOrder, int[] middleOrder, int start, int end) {
        System.out.println(start + "--" + end);
        if (start > end) return null;
        if (start == end) return new BinTree.TreeNode(middleOrder[start]);
        int rootIndex = getFirst(preOrder, middleOrder, start, end);
        BinTree.TreeNode left = buildTree(preOrder, middleOrder, start, rootIndex - 1);
        BinTree.TreeNode right = buildTree(preOrder, middleOrder, rootIndex + 1, end);
        BinTree.TreeNode rootNode = new BinTree.TreeNode(middleOrder[rootIndex]);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }

    /**
     * 在a中找到第一个出现在b[start]和b[end]之间的元素
     *
     * @param a
     * @param b
     * @return
     */
    static Integer getFirst(int[] a, int[] b, int start, int end) {
        for (int i = 0; i < a.length; i++) {
            for (int j = start; j <= end; j++) {
                if (a[i] == b[j]) {
                    return j;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        BinTree.TreeNode[] t1 = new BinTree.TreeNode[]{new BinTree.TreeNode(1),
                new BinTree.TreeNode(2), new BinTree.TreeNode(3), new BinTree.TreeNode(4),
                new BinTree.TreeNode(5), new BinTree.TreeNode(6), new BinTree.TreeNode(7),
                new BinTree.TreeNode(8), new BinTree.TreeNode(9), new BinTree.TreeNode(10)};
        BinTree.TreeNode[] t2 = new BinTree.TreeNode[]{new BinTree.TreeNode(2), new BinTree.TreeNode(4),
                new BinTree.TreeNode(5), new BinTree.TreeNode(8)};
        BinTree.createCompleteTree(t1);
        BinTree.createCompleteTree(t2);
        System.out.println(contains(t1[0], t2[0]));

        BinTree.TreeNode root = buildTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6}, 0, 7);
        System.out.println(root.data);
    }
}
