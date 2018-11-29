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
    static int[] getSumSubArray(int[] array, int sum) {
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

    /**
     * 找出和最大子串
     * <p>
     * 注意边界条件，比如第一个元素最大并且是负数
     *
     * @param array
     * @return 子串开始和结束的索引
     */
    static int[] getMaxSumSubStr(int[] array) {
        int sum = 0;
        int min = 0;
        int minIndex = -1;
        int[] ret = new int[2];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            int curMax = sum - min;
            if (curMax >= max) {//必须有等号
                max = curMax;
                ret = new int[]{minIndex + 1, i};
            }
            if (curMax < min) {
                min = curMax;
                minIndex = i;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getSumSubArray(new int[]{1, 2, 3, 4}, 6)));

        System.out.println(Arrays.toString(getMaxSumSubStr(new int[]{3, 9, -8, 7, 6, 0, -5, 4, -3})));
    }
}
