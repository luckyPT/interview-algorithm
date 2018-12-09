package com.pt.array;

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

    /**
     * 找到最长递增序列(可以不连续)
     * 关键点：数组countLocWithMinNum 第i个元素的值表示
     * 出现过i+1个的递增子序列的最后一个元素的最小值。
     * 比如数组countLocWithMinNum[3]表示当前4个元素构成的序列
     * 最后一个元素可能的最小值。时间复杂度n*logn
     *
     * 方法二：依次遍历，记录每一个元素对应的最长子序列，遍历下一个元素时，
     * 需要跟之前的每个元素做比较才能得到这个元素的最长子序列。时间复杂度为n^2
     * @param array -
     * @return 最长序列
     */
    static int[] getMaxIncresingAarray(int[] array) {
        int[] countLocWithMinNum = new int[array.length];
        int right = 0;//right 以及right之后的元素表示无效
        for (int i = 0; i < array.length; i++) {
            int j;
            for (j = 0; j < right; j++) {
                if (countLocWithMinNum[j] > array[i]) {
                    countLocWithMinNum[j] = array[i];
                }
            }
            if (right == 0 || countLocWithMinNum[right - 1] < array[i]) {
                countLocWithMinNum[right] = array[i];
                right++;
            }
        }

        return countLocWithMinNum;
    }

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(getSumSubArray(new int[]{1, 2, 3, 4}, 6)));
        //System.out.println(Arrays.toString(getMaxSumSubStr(new int[]{3, 9, -8, 7, 6, 0, -5, 4, -3})));

        System.out.println(Arrays.toString(getMaxIncresingAarray(new int[]{2, 1, 5, 3, 4, 6, 8, 7, 9})));
    }
}
