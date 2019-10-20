package com.pt.leetcode.bin.search;

/**
 * 难度指数4.5星
 * @author IT技术百货
 * leetCode：4
 */
public class GetMedianInSortedArrays {
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int numsCount = nums1.length + nums2.length;
        int targetIndex = numsCount / 2;
        if (nums1.length == 0) {
            if (numsCount % 2 == 1) {
                return nums2[targetIndex];
            } else {
                return (nums2[targetIndex] + nums2[targetIndex - 1]) / 2.0;
            }
        }
        if (nums2.length == 0) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int start = 0;
        int end = nums1.length - 1;
        int[] numIndexInNums2 = new int[0];
        while (start <= end) {
            int mid = (start + end) / 2;
            numIndexInNums2 = new int[]{getMinIndex(nums1[mid], nums2),
                    getMaxIndex(nums1[mid], nums2)};
            if (mid + numIndexInNums2[0] <= targetIndex && mid + numIndexInNums2[1] >= targetIndex) {
                break;
            } else if (mid + numIndexInNums2[1] < targetIndex) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (start <= end) {
            int num1Index = (start + end) / 2;
            if (numsCount % 2 == 1) {
                return nums1[num1Index];
            } else {
                int a = nums1[num1Index];
                int b = Integer.MIN_VALUE;
                if (targetIndex - num1Index - 1 >= 0) {
                    b = Math.max(b, nums2[targetIndex - num1Index - 1]);
                }
                if (num1Index > 0) {
                    b = Math.max(b, nums1[num1Index - 1]);
                }

                return (a + b) / 2.0;
            }
        } else {
            return findMedianSortedArrays(nums2, nums1);
        }
    }

    private int getMinIndex(int num, int[] nums) {
        if (num <= nums[0]) {
            return 0;
        }
        if (num > nums[nums.length - 1]) {
            return nums.length;
        }
        return getMinIndex(num, nums, 0, nums.length - 1);
    }

    private int getMinIndex(int num, int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        if (nums[mid] < num && nums[mid + 1] >= num) {
            return mid + 1;
        }
        if (nums[mid] >= num) {
            return getMinIndex(num, nums, start, mid);
        }
        if (nums[mid + 1] < num) {
            return getMinIndex(num, nums, mid + 1, end);
        }
        throw new RuntimeException("");
    }

    private int getMaxIndex(int num, int[] nums) {
        if (nums[0] > num) {
            return 0;
        }
        if (nums[nums.length - 1] <= num) {
            return nums.length;
        }
        return getMaxIndex(num, nums, 0, nums.length - 1);
    }

    private int getMaxIndex(int num, int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        if (nums[mid] <= num && nums[mid + 1] > num) {
            return mid + 1;
        }
        if (nums[mid] > num) {
            return getMaxIndex(num, nums, start, mid);
        }
        if (nums[mid + 1] <= num) {
            return getMaxIndex(num, nums, mid + 1, end);
        }
        throw new RuntimeException("error");
    }

    public static void main(String[] args) {
        System.out.println(new GetMedianInSortedArrays().findMedianSortedArrays(
                new int[]{4},
                new int[]{1, 2, 3}
        ));

        //System.out.println(new GetMedianInSortedArrays().getMinIndex(2, new int[]{1}, 0, 0));
        //System.out.println(new GetMedianInSortedArrays().getMaxIndex(1, new int[]{2}));
    }
}
