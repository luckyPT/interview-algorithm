package com.pt.tree;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    static void postOrderLike(TreeNode node, int h, int singleSpaceCount) {
        if (node == null) return;
        postOrderLike(node.right, h + 1, singleSpaceCount);
        printlnOneData(node.data, h, singleSpaceCount);
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

    public static void main(String[] args) {
        TreeNode root = BinTree.createSearchTree(new int[]{1, 6, 3, 2, 4, 7, 5, 9, 10, 8});
        TreeNode.postOrderLike(root, 0, 5);
    }
}
