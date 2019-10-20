package com.pt.leetcode.recursion;

import java.util.Arrays;
import java.util.Random;

/**
 * partition函数需要注意，返回的分区点i，应该保证i位置元素值的正确性，
 * 即 i 之前的元素都小于a[i],i 之后的元素都大于等于a[i]
 *
 * @author panTeng
 */
public class QuickSort {
    public static void quickSort1(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int i = partition2(a, start, end);
        quickSort1(a, start, i - 1);
        quickSort1(a, i + 1, end);
    }

    public static int partition1(int[] a, int start, int end) {
        int randomIndex = new Random().nextInt(end - start + 1) + start;
        swap(a, randomIndex, end);
        int key = a[end];
        int i = start, j = start;
        while (j < end) {
            if (a[j] < key) {
                swap(a, i, j);
                i++;
            }
            j++;
        }
        swap(a, i, end);//保证i位置的值是正确的
        return i;
    }

    public static int partition2(int[] a, int start, int end) {
        int randomIndex = new Random().nextInt(end - start + 1) + start;
        swap(a, randomIndex, end);
        int key = a[end];
        int i = start, j = end;//这里不可以将j设置为end-1
        while (i < j) {
            while (i < j) {
                if (a[i] < key) {
                    i++;
                } else {
                    break;
                }
            }
            while (i < j) {
                if (a[j] >= key) {
                    j--;
                } else {
                    break;
                }
            }
            if (i < j) {
                swap(a, i, j);
            }
        }
        swap(a, i, end);//保证i位置的元素是正确的
        return i;
    }

    public static void swap(int[] a, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        /**
         int[] a = new int[]{8, 7, 6, 6, 3};
         quickSort1(a, 0, a.length - 1);
         System.out.println(Arrays.toString(a));**/

        int[] b = new int[]{3, 2, 1, 5, 6, 4};
        quickSort1(b, 0, b.length - 1);
        System.out.println(Arrays.toString(b));
    }
}
