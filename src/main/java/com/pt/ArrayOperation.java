package com.pt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayOperation {
    /**
     * 找到连续，最长子数组 满足子数组和为sum
     *
     * @param array
     * @param sum
     * @return 子串开始和结束的索引
     */
    static int[] getSumSubArrat(int[] array, int sum) {
        int sumI = 0;
        int maxLen = -2;
        int start = 0;
        int end = 0;
        Map<Integer, Integer> indexSumMap = new HashMap();
        indexSumMap.put(0, -1);
        for (int i = 0; i < array.length; i++) {
            sumI += array[i];
            if (sumI == sum) {
                maxLen = i + 1;
                start = 0;
                end = i;
            }
            if (indexSumMap.containsKey(sumI - sum)) {
                int preIndex = indexSumMap.get(sumI - sum);
                if (i - preIndex > maxLen) {
                    maxLen = i - preIndex;
                    start = preIndex + 1;
                    end = i;
                }
            }
            if (!indexSumMap.containsKey(sumI)) {
                indexSumMap.put(sumI, i);
            }
        }
        if (maxLen > 0) {
            return new int[]{start, end};
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getSumSubArrat(new int[]{1, 2, 3, 4}, 6)));
    }
}
