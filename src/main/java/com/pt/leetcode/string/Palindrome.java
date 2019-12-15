package com.pt.leetcode.string;

import java.util.*;

/**
 * leetCode 132
 * 此解法并不是最优，可以利用动态规划
 */
public class Palindrome {
    private int minSize = Integer.MAX_VALUE;
    Set<IntegerPair> cache = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new Palindrome().minCut("fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi"));
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        minCutHelper(s, 0, -1);
        return minSize;
    }

    private void minCutHelper(String s, int start, int count) {
        IntegerPair pair = new IntegerPair(start,count);
        if(cache.contains(pair)){
            return;
        }else{
            cache.add(pair);
        }
        if (count >= this.minSize) {
            return;
        }
        if (start >= s.length()) {
            minSize = Math.min(minSize, count);
            return;
        }

        for (int i = s.length(); i > start; i--) {
            if (isPalindrome(s.substring(start, i))) {
                minCutHelper(s, i, count + 1);
            }
        }
    }

    public boolean isPalindrome(String str) {
        for (int i = 0; i <= str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    static class IntegerPair {
        final int start;
        final int count;

        public IntegerPair(int start, int count) {
            this.start = start;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntegerPair that = (IntegerPair) o;
            return start == that.start &&
                    count == that.count;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, count);
        }
    }
}
