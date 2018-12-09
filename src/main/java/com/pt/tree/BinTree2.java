package com.pt.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinTree2 {
    /**
     * 判断t1 是不是包含t2的全部拓扑结构
     *
     * @param t1 树的根结点
     * @param t2 树的根结点
     * @return true-包含  false-不包含
     */
    private static boolean contains(TreeNode t1, TreeNode t2) {
        //先对t1进行遍历，找到与t2相等的结点
        List<TreeNode> equalsNodes = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(t1);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
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
        for (TreeNode equalsNode : equalsNodes) {
            //对t2进行遍历，验证t1是否包含t2
            stack.clear();
            Stack<TreeNode> stack1 = new Stack<>();
            stack.push(t2);
            stack1.push(equalsNode);
            while (!stack.empty()) {
                if (stack1.empty()) {
                    break;
                }
                TreeNode t2Node = stack.pop();
                TreeNode t1Node = stack1.pop();
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
     * 根据前序遍历和中序遍历构造二叉树
     *
     * @param preOrder    前序遍历结果
     * @param middleOrder 中序遍历结果
     * @return 二叉树的根结点
     */
    private static TreeNode buildTree(int[] preOrder, int[] middleOrder, int start, int end) {
        System.out.println(start + "--" + end);
        if (start > end) return null;
        if (start == end) return new TreeNode(middleOrder[start]);
        int rootIndex = getFirst(preOrder, middleOrder, start, end);
        TreeNode left = buildTree(preOrder, middleOrder, start, rootIndex - 1);
        TreeNode right = buildTree(preOrder, middleOrder, rootIndex + 1, end);
        TreeNode rootNode = new TreeNode(middleOrder[rootIndex]);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }

    /**
     * 在a中找到第一个出现在b[start]和b[end]之间的元素
     *
     * @param a -
     * @param b -
     * @return 该元素在数组b中的索引
     */
    private static Integer getFirst(int[] a, int[] b, int start, int end) {
        for (int anA : a) {
            for (int j = start; j <= end; j++) {
                if (anA == b[j]) {
                    return j;
                }
            }
        }
        return -1;
    }

    /**
     * 思路：倒序遍历，左子树返回其找到的节点，右子树返回其找到的节点。
     * 如果左右都找到了，那么这个点就是公共跟结点，都没找到返回null，找到一个则返回其找到的结点
     *
     * @param root  树的根节点
     * @param node1 查询结点1
     * @param node2 查询结点2
     * @return 结点1和2的最近公共根节点
     */
    private static TreeNode findCommonRoot(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        TreeNode left = findCommonRoot(root.left, node1, node2);
        TreeNode right = findCommonRoot(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }


    public static void main(String[] args) {
        TreeNode[] t1 = new TreeNode[]{new TreeNode(1),
                new TreeNode(2), new TreeNode(3), new TreeNode(4),
                new TreeNode(5), new TreeNode(6), new TreeNode(7),
                new TreeNode(8), new TreeNode(9), new TreeNode(10)};
        TreeNode[] t2 = new TreeNode[]{new TreeNode(2), new TreeNode(4),
                new TreeNode(5), new TreeNode(8)};
        BinTree.createCompleteTree(t1);
        BinTree.createCompleteTree(t2);
        System.out.println(contains(t1[0], t2[0]));

        TreeNode root = buildTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6}, 0, 7);
        assert root != null;
        System.out.println(root.data);

        TreeNode commonRoot = findCommonRoot(t1[0], t1[4], t1[6]);
        System.out.println(commonRoot.data);
    }
}
