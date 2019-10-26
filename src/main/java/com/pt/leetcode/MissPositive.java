package com.pt.leetcode;

import java.util.HashSet;

/**
 * leetCode 41 时间复杂度满足需求，但空间复杂度不满足
 *
 * @author panTeng
 */
public class MissPositive {
    public static int firstMissingPositive(int[] nums) {
        HashSet<Integer> values = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                values.add(num);
            }
        }
        nums = new int[values.size()];
        int i = 0;
        for (int v : values) {
            nums[i] = v;
            i++;
        }

        int[] minMax = getMinMax(nums, 0, nums.length - 1);
        int min = minMax[0];
        int max = minMax[1];
        if (min > 1) {
            return 1;
        }
        if (max == nums.length) {
            return max + 1;
        }
        return firstMissingPositive(nums, 0, nums.length - 1);
    }

    /**
     * 假设nums没有重复元素并且全部是正整数
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int firstMissingPositive(int[] nums, int start, int end) {
        int[] minMax = getMinMax(nums, start, end);
        int min = minMax[0];
        int max = minMax[1];
        if (max - min == end - start) {//nums为连续正整数
            return max + 1;
        }
        int partIndex = partition(nums, start, end);//partIndex之前的元素都小于nums[partIndex]，之后的元素都大于nums[partIndex]
        if (partIndex - start < nums[partIndex] - min) {//必然出现在这里面
            return firstMissingPositive(nums, start, partIndex - 1);
        } else if (nums[partIndex + 1] - nums[partIndex] > 1) {
            return nums[partIndex] + 1;
        } else if (max - nums[partIndex] > end - partIndex) {
            return firstMissingPositive(nums, partIndex + 1, end);
        } else {
            return max + 1;
        }
    }

    public static int[] getMinMax(int[] nums, int start, int end) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            int num = nums[i];
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        return new int[]{min, max};
    }

    public static int partition(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }
        int key = nums[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] <= key) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, -1, 1};
        System.out.println(firstMissingPositive(nums));
    }
}
