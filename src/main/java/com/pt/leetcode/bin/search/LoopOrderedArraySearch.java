package com.pt.leetcode.bin.search;

/**
 * 循环有序数组查找
 *
 * @author panTeng
 */
public class LoopOrderedArraySearch {
    /**
     * 考虑没有循环、只有两个元素并且循环的情况
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int split = (nums.length - 1) / 2;
        if (checkLoop(nums)) {//检测有没有循环
            while (nums[split] <= nums[split + 1]) {//找到循环点
                if (nums[split] >= nums[start]) {//注意这里必须有等号，当start+1=end，并且start不满足要求时，查看end是否满足要求
                    start = split + 1;
                } else {
                    end = split - 1;
                }
                split = (end + start) / 2;
            }
            //判断被查找元素在循环之前还是之后
            if (nums[0] <= target && nums[split] >= target) {
                start = 0;
                end = split;
            } else if (nums[split + 1] <= target && nums[nums.length - 1] >= target) {
                start = split + 1;
                end = nums.length - 1;
            } else {
                return -1;
            }
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static boolean checkLoop(int[] array) {
        if (array.length > 2) {
            int mid = (array.length - 1) / 2;
            return (array[mid] - array[0]) * (array[array.length - 1] - array[mid]) < 0;
        } else if (array.length == 2) {
            return array[0] > array[1];
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 5, 1, 2, 3};
        assert search(a, 1) == 2 : search(a, 1);
    }
}
