package com.pt.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * leetCode 239
 */
public class WindowMaxValues {
    //队列中保留的是索引，而不是值；因为对于数组来说，通过索引可以很快找到对应的值，而通过值则不能确定索引
    ArrayDeque<Integer> deque = new ArrayDeque<>();

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums.length < k || k == 0) {
            return new int[]{0};
        }
        int[] results = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.add(i);
        }
        results[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.add(i);
            results[i - k + 1] = nums[deque.peekFirst()];
        }
        return results;
    }

    public static void main(String[] args) {
        int[] ret = new WindowMaxValues().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(ret));
    }
}
