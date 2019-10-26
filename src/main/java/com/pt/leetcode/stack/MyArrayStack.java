package com.pt.leetcode.stack;

/**
 * 基于数组实现栈
 *
 * @author panTeng
 */
public class MyArrayStack {
    int capacity;
    int count;
    int[] array;

    public MyArrayStack(int capacity) {
        this.capacity = capacity;
        array = new int[this.capacity];
    }

    public boolean push(int x) {
        if (count >= capacity) {
            this.capacity = capacity * 2;
            int[] newArray = new int[this.capacity];
            System.arraycopy(array, 0, newArray, 0, this.capacity);
            array = newArray;
        }
        array[count] = x;
        count++;
        return true;
    }

    public int pop() {
        int r = array[count - 1];
        count--;
        return r;
    }

    public int top() {
        return array[count - 1];
    }

    public boolean empty() {
        return count == 0;
    }
}
