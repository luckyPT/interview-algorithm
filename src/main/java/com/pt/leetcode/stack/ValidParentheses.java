package com.pt.leetcode.stack;

import java.util.Stack;

/**
 * leetCode 32
 *
 * @author panTeng
 */
public class ValidParentheses {
    /**
     * 借助判断是否合法的手段进行遍历;
     * 时间复杂度为n^2,可以通过一些限制条件，避免一些无效遍历，提前break，比如：s.length() - max;
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length() - max; i++) {
            if (s.charAt(i) == ')') {
                continue;
            }
            stack.clear();
            stack.push(s.charAt(i));

            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    stack.push(s.charAt(j));
                } else {
                    if (stack.size() == 0 || stack.pop() == ')') {
                        break;
                    }
                    if (stack.size() == 0) {
                        if (max < (j - i) + 1) {
                            max = j - i + 1;
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     * 思路：动态规划，如果最长串可以以第i个位置结尾，则数组存放这个位置的最大长度，如果不可能，则存放0
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int[] maxLength = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            char t = s.charAt(i);
            if (t == '(') {
                maxLength[i] = 0;//最长串不可能以‘(’结尾
            } else {
                char pre = s.charAt(i - 1);
                if (pre == '(') {//()情况
                    if (i == 1) {
                        maxLength[i] = 2;
                    } else {
                        maxLength[i] = maxLength[i - 2] + 2;//无论i-2是‘)’ 或者 ‘(’都成立
                    }
                } else {// )) 的情况
                    int start = i - maxLength[i - 1] - 1;
                    if (start >= 0 && s.charAt(start) == '(') {
                        if (start == 0) {
                            maxLength[i] = maxLength[i - 1] + 2;
                        } else {//需要加maxLength[start-1] 是考虑到可能与第start-1及之前的元素可结合为一个更长的串
                            maxLength[i] = maxLength[i - 1] + 2 + maxLength[start - 1];
                        }
                    } else {
                        maxLength[i] = 0;//此时不可能以i结尾
                    }
                }
            }
        }
        int max = 0;
        for (int x : maxLength) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().longestValidParentheses("()()"));
        System.out.println(new ValidParentheses().longestValidParentheses2("())"));
    }
}
