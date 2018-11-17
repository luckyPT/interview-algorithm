package com.pt;

import java.util.Arrays;

public class FindSingle {
    /**
     * 给出N个数字。其中仅有一个数字出现过一次，其他数字均出现过两次，找出这个出现且只出现过一次的数字。
     * 要求时间和空间复杂度最小。
     * 关键点：相同的两个数异或结果为0,不同的数异或结果一定不为0；并且满足交换律
     *
     * @param line 10 10 11 12 12 11 16
     * @return 16
     */
    private static String solution(String line) {
        String[] nums = line.split(" ");
        Integer v = Integer.parseInt(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            v ^= Integer.parseInt(nums[i]);
        }
        return "" + v;
    }

    /**
     * 给出N个数字。其中仅有两个数字出现过一次，其他数字均出现过两次，找出这个出现且只出现过一次的数字。
     * 要求时间和空间复杂度最小。
     * 关键点：先进行异或操作，找到最终结果为1的那一位，这一位必是两个单独出现数字异或的结果。其中一位为1,另一位为0
     * 之后利用这一位对所有数分组，相同的数一定出现在同一组，两个单个的数必定在不同组，再利用找仅有的一个出现一次的数的方法去找即可
     *
     * @param line 10 10 11 12 12 11 16 26
     * @return 16 26
     */
    private static String solution2(String line) {
        int[] result = new int[2];  //要返回的结果
        String[] nums = line.split(" ");
        Integer v = Integer.parseInt(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            v ^= Integer.parseInt(nums[i]);
        }

        int bitIndex = 0;
        for (int i = 0; i < 32; i++) {  //找出亦或结果为1的位。
            if ((v >> i & 1) == 1) {
                bitIndex = i;
                break;
            }
        }
        for (String num : nums) { //根据bitIndex为1，将元素分为两组
            if ((Integer.parseInt(num) >> bitIndex & 1) == 1) {
                result[0] ^= Integer.parseInt(num);   //对应位为1，亦或得到的结果
            } else {
                result[1] ^= Integer.parseInt(num);   //对应位为0，亦或得到的结果
            }
        }
        return Arrays.toString(result);
    }

    public static void main(String[] args) {
        System.out.println(solution("10 10 11 12 12 11 16"));
        System.out.println(solution2("10 10 11 12 12 11 16 26"));
    }
}
