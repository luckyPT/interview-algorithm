package com.pt.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class NumSeq {
    /**
     * 输入一个乱序的连续数列，输出其中最长连续数列长度，要求算法复杂度为  O(n)  。
     * 关键点：借助SET，从任一元素开始，上下移除元素，并记录移除数量；最终找出最大移除数量
     *
     * @param line 54,55,300,12,56
     * @return 3  最长连续数列为：54,55,56
     */
    public static int maxContinueCount(String line) {
        int[] num = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        HashSet<Integer> set = new HashSet<>();
        for (int k : num) {
            set.add(k);
        }
        int max = 1;
        for (int k : num) {
            if (set.remove(k)) {
                int count = 1;
                int val_small = k - 1;
                int val_big = k + 1;
                while (set.remove(val_small)) {
                    count++;
                    val_small--;
                }
                while (set.remove(val_big)) {
                    count++;
                    val_big++;
                }
                max = Math.max(max, count);
            }

        }
        return max;
    }

    /**
     * 给出一个有序数列(假定是升序)随机旋转之后的数列，如原有序数列为：0,1,2,4,5,6,7 ，旋转之后为4,5,6,7,0,1,2
     * 假定数列中无重复元素，且数列长度为奇数。
     * 求出旋转数列的中间值。如数列4,5,6,7,0,1,2的中间值为4
     * 关键点：
     *
     * @param line
     * @return
     */
    static int findMiddle(String line) {
        int[] nums = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
        int midIndex = nums.length / 2;
        int i = 1;
        for (; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                break;
            }
        }
        if (i >= midIndex) {
            return nums[i - midIndex - 1];
        } else {
            return nums[i + midIndex];
        }
    }

    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * @param nums   [2, 7, 11, 15]
     * @param target 9
     * @return [0, 1]
     */
    static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    //功能同上，但时间复杂度小很多
    static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * @param nums   数组 [-1, 0, 1, 2, -1, -4]
     * @param target 找到所有可能的三个元素，使得数组求和等于target；返回元素索引
     * @return [[0, 1, 2], [0, 3, 4], [1, 2, 4]]
     */
    static List<List<Integer>> threeSum(int[] nums, int target) {
        ArrayList ret = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            HashMap map = new HashMap();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(remain - nums[j])) {
                    ArrayList tmpList = new ArrayList();
                    tmpList.add(i);
                    tmpList.add(map.get(remain - nums[j]));
                    tmpList.add(j);
                    ret.add(tmpList);
                } else {
                    map.put(nums[j], j);
                }
            }
        }
        return ret;

    }

    /**
     * 要求返回的是元素值，而不是索引值；并且要求不能重复
     * 参考上题的思路，只需要事先排序，在寻找的时候，对于已经最外层i++ 需要保证元素值与i不重复即可
     *
     * @param nums   数组 [-1, 0, 1, 2, -1, -4]
     * @param target 找到所有可能的三个元素，使得数组求和等于target,并且不能重复；返回的是元素值
     * @return [ [-1, 0, 1],[-1, -1, 2]]
     */
    static List<List<Integer>> threeSum2(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList ret = new ArrayList();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int remain = target - nums[i];
            HashMap map = new HashMap();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(remain - nums[j])) {
                    ArrayList tmpList = new ArrayList();
                    tmpList.add(nums[i]);
                    tmpList.add(nums[(Integer) map.get(remain - nums[j])]);
                    tmpList.add(nums[j]);
                    ret.add(tmpList);
                } else {
                    map.put(nums[j], j);
                }
            }
        }

        return ret;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 假设数组是非空的，并且给定的数组总是存在众数
     * 采用Map计数，时间和空间复杂度为O(n)
     *
     * @param nums [3,2,3]; [2,2,1,1,1,2,2]
     * @return 3;2
     */
    static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int cur = map.getOrDefault(nums[i], 0);
            if (cur + 1 > nums.length / 2) {
                return nums[i];
            } else {
                map.put(nums[i], cur + 1);
            }
        }
        throw new RuntimeException("--");
    }

    /**
     * 功能同上，考虑基于原始数据，同时删除两个不同的数；那么数组的众数不变。
     * 这样空间复杂度可以降到O(1)
     *
     * @param nums
     * @return
     */
    static int majorityElement2(int[] nums) {
        int cur = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cur == nums[i]) {
                count++;
            } else {
                count--;
                if (count <= 0 && i + 1 < nums.length) {
                    cur = nums[i + 1];
                    count = 1;
                    i++;
                }
            }
            if (count > nums.length / 2) {
                return cur;
            }
        }

        return cur;
    }

    /**
     * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子数组（该数组至少包含一个数）。
     * <p>
     * 时间复杂度O(n^3)
     *
     * @param nums [2,3,-2,4]；[-2,0,-1]
     * @return 6；0
     */
    private static int maxProduct(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int v = 1;
                for (int k = i; k <= j; k++) {
                    v *= nums[k];
                }
                if (v > max) {
                    max = v;
                }
            }
        }

        return max;
    }

    private static int maxProduct2(int[] nums) {
        int maxProduct = nums[0];
        int max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmpMax = max, tmpMin = min;
            max = Math.max(Math.max(nums[i], nums[i] * tmpMax), nums[i] * tmpMin);
            min = Math.min(Math.min(nums[i], nums[i] * tmpMin), nums[i] * tmpMax);
            maxProduct = Math.max(max, maxProduct);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        //System.out.println(maxContinueCount("54,55,300,12,56"));
        //System.out.println(maxContinueCount("100,4,200,1,3,2"));
        //System.out.println(maxContinueCount("5,4,3,2,1"));
        System.out.println(findMiddle("1"));
        System.out.println(findMiddle("1,2,3"));
        System.out.println(findMiddle("4,5,6,7,0,1,2"));
        System.out.println(findMiddle("12,13,14,5,6,7,8,9,10"));
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ret = threeSum2(nums, 0);
        System.out.println(ret);

        int[] nums2 = new int[]{-2, 0, -1};
        System.out.println(maxProduct(nums2));
    }
}
