package com.pt.leetcode.sort;

/**
 * 获取第K大元素
 *
 * @author panTeng
 */
public class KthLargest {
    public static int getKthLargestNumByQuickSort(int[] a, int start, int end, int k) {
        int i = partition(a, start, end);
        if (i == k - 1) {
            return a[i];
        } else if (i > k - 1) {
            return getKthLargestNumByQuickSort(a, start, i - 1, k);
        } else {
            return getKthLargestNumByQuickSort(a, i + 1, end, k);
        }
    }

    public static int partition(int[] a, int start, int end) {
        int i = start, j = start;
        int key = a[end];
        while (j <= end) {
            if (a[j] < key) {
                swap(a, i, j);
                i++;
            }
            j++;
        }
        swap(a, i, end);
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        if (i >= nums.length || j >= nums.length) {
            throw new RuntimeException("越界");
        }
        if (i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(getKthLargestNumByQuickSort(a, 0, a.length - 1, 6));
    }
}
