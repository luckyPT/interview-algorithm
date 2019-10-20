package com.pt.leetcode.recursion;

import java.util.Arrays;

/**
 * @author panTeng
 */
public class MergeSort {
    public static void mergeSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(a, start, mid);
        mergeSort(a, mid + 1, end);
        merge(a, start, end, mid);
    }

    public static void merge(int[] a, int start, int end, int mid) {
        int i = start, j = mid + 1;
        int[] sortA = new int[end - start + 1];
        int index = 0;
        while (i <= mid && j <= end) {
            if (a[i] > a[j]) {
                sortA[index++] = a[j++];
            } else {
                sortA[index++] = a[i++];
            }
        }
        while (i <= mid) {
            sortA[index++] = a[i++];
        }
        while (j <= end) {
            sortA[index++] = a[j++];
        }
        for (int k = start; k <= end; k++) {//关键
            a[k] = sortA[k - start];
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5, 9, 8, 8, 7, 10};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
