package com.pt.leetcode.queue;

import com.pt.list.SNode;

/**
 * leetCode 622
 *
 * @author panTeng
 */
public class MyLinkCiycularQueue {
    SNode<Integer> head;
    SNode<Integer> tail;
    int capacity;
    int count;

    public MyLinkCiycularQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {
        if (count < capacity) {
            SNode<Integer> newNode = new SNode<>(value);
            if (count == 0) {
                head = newNode;
                tail = newNode;
                count++;
            } else {
                tail.next = newNode;
                tail = newNode;
                count++;
            }
            return true;
        }
        return false;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (count > 0) {
            head = head.next;
            count--;
            return true;
        }
        return false;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        return count > 0 ? head.data : -1;
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        return count > 0 ? tail.data : -1;
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {
        return count == this.capacity;
    }

    public static void main(String[] args) {
        MyLinkCiycularQueue queue = new MyLinkCiycularQueue(3);
        System.out.println(queue.enQueue(1));
    }
}
