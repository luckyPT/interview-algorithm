package com.pt.leetcode.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @leetCode 5
 * 通过反转找最长公共子串的思路有问题，比如：aacdefcaa
 */
public class MaxCommonPalindromeStr {
    /**
     * 目前这种方式时间与空间复杂度略高
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            List<Integer> tmp = map.getOrDefault(s.charAt(i), new LinkedList<>());
            tmp.add(i);
            map.put(s.charAt(i), tmp);
        }
        int maxLen = 1;
        int start = -1;
        int end = -1;
        for (int i = 0; i < s.length(); i++) {
            List<Integer> endIndexs = map.get(s.charAt(i));
            if (endIndexs.size() == 1) {
                continue;
            }
            for (Integer endI : endIndexs) {
                if (endI != i && endI > i && (endI - i + 1) > maxLen && isPalindrome(s.substring(i, endI + 1))) {
                    maxLen = endI - i + 1;
                    start = i;
                    end = endI;
                }
            }
        }

        return maxLen > 1 ? s.substring(start, end + 1) : s.substring(0, 1);
    }

    public boolean isPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }
        int stop = s.length() / 2;
        for (int i = 0; i < stop; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 最长公共子串,这种思路有问题
     *
     * @param s1
     * @param s2
     * @return
     */
    public String longestCommonStr(String s1, String s2) {
        int max = 0;
        int maxI = 0;
        int maxJ = 0;
        int[][] status = new int[s1.length()][s1.length()];
        for (int i = 0; i < s1.length(); i++) {
            if (s2.charAt(0) == s1.charAt(i)) {
                status[0][i] = 1;
                if (max == 0) {
                    max = 1;
                    maxI = 0;
                    maxJ = i;
                }
            }
        }
        for (int j = 0; j < s2.length(); j++) {
            if (s1.charAt(0) == s2.charAt(j)) {
                status[j][0] = 1;
                if (max == 0) {
                    max = 1;
                    maxI = j;
                    maxJ = 0;
                }
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    status[j][i] = status[j - 1][i - 1] + 1;
                    if (status[j][i] > max) {
                        max = status[j][i];
                        maxI = j;
                        maxJ = i;
                    }
                }
            }
        }
        int startI = maxI;
        int startJ = maxJ;
        while (max > 0 && status[startI][startJ] == max) {//max必须放到前面
            startI -= 1;
            startJ -= 1;
            max -= 1;
        }
        return s1.substring(startJ + 1, maxJ + 1);//这里是J 而不是I
    }

    public static void main(String[] args) {
        //System.out.println(new MaxCommonPalindromeStr().isPalindrome("abba"));
        System.out.println(new MaxCommonPalindromeStr().longestPalindrome("ac"));
    }

}
