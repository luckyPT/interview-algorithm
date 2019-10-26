package com.pt.leetcode.sort;

import java.util.Arrays;

public class ArraySort {
    public int[] sortArray(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        //return selectSort(nums);
        //return quickSort(nums, 0, nums.length - 1);
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int[] quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return nums;
        }
        int key = nums[start];
        int i = start + 1;
        int j = end;
        while (i < j) {
            while (nums[i] <= key && i < j) {
                i++;
            }
            while (nums[j] > key && i < j) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        if (nums[i] > key) {
            swap(nums, start, i - 1);
            quickSort(nums, start, i - 2);//注意这里第i-1个元素已经确定，所以后面迭代的时候不需要再进行排序，否则可能会造成死循环
            quickSort(nums, i, end);
        } else {
            swap(nums, start, i);
            quickSort(nums, start, i - 1);//注意这里第i个元素已经确定，所以后面迭代的时候不需要再进行排序，否则可能会造成死循环
            quickSort(nums, i + 1, end);
        }
        return nums;
    }

    /**
     * 时间复杂度O(n^2)
     **/
    public int[] selectSort(int[] nums) {
        for (int j = 0; j < nums.length; j++) {
            int maxIndex = 0;
            for (int i = 1; i < nums.length - j; i++) {
                if (nums[maxIndex] < nums[i]) {
                    maxIndex = i;
                }
            }
            swap(nums, maxIndex, nums.length - j - 1);
        }
        return nums;
    }

    public int[] mergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return new int[]{nums[start]};
        }
        int mid = (start + end) / 2;
        int[] s1 = mergeSort(nums, start, mid);
        int[] s2 = mergeSort(nums, mid + 1, end);
        //下面是二路归并排序，可以抽象出一个方法
        int i = 0, j = 0;
        int[] ret = new int[s1.length + s2.length];
        int k = 0;
        while (i < s1.length && j < s2.length) {
            if (s1[i] <= s2[j]) {
                ret[k++] = s1[i++];
            } else {
                ret[k++] = s2[j++];
            }
        }
        while (i < s1.length) {
            ret[k++] = s1[i++];
        }
        while (j < s2.length) {
            ret[k++] = s2[j++];
        }
        return ret;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 6, 1, 76, 9, 2, 4};
        System.out.println(Arrays.toString(new ArraySort().sortArray(nums)));
    }
}
