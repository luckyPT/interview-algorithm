package com.pt.leetcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * leetCode 46
 */
public class AllArrange {
    public List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        all(nums, 0, new ArrayList<Integer>());
        return result;
    }

    public void all(int[] nums, int index, List<Integer> list) {
        if (index >= nums.length) {
            result.add(list);
            return;
        }
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < list.size() + 1; i++) {
            int j = 0;
            for (; j < i; j++) {
                tmp.add(list.get(j));
            }
            tmp.add(nums[index]);
            for (; j < list.size(); j++) {
                tmp.add(list.get(j));
            }
            all(nums, index + 1, tmp);
            tmp = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> ret = new AllArrange().permute(new int[]{1, 2, 3});
        System.out.println(ret);
    }
}
