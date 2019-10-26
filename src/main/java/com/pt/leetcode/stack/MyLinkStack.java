package com.pt.leetcode.stack;

public class MyLinkStack {
    //int capacity;
    private int count;
    private Node top;

    public void push(int v) {
        Node newTop = new Node(v);
        newTop.next = top;
        this.top = newTop;
        count++;
    }

    public int pop() {
        if (top == null) {
            throw new RuntimeException("stack is empty");
        }
        Node ret = top;
        top = top.next;
        count--;
        return ret.val;
    }

    public int peek() {
        if (top == null) {
            throw new RuntimeException("stack is empty");
        }
        return top.val;
    }

    public int size() {
        return count;
    }

    static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        MyLinkStack stack = new MyLinkStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.pop());//-3
        System.out.println(stack.peek());//0
        System.out.println(stack.size());//2
        System.out.println(stack.pop());//0
        System.out.println(stack.pop());//-2
        System.out.println(stack.pop());//Exception
    }
}
