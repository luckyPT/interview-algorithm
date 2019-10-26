package com.pt.leetcode.stack;

import java.util.*;

/**
 * leetCode：1106
 */
public class ParseBoolExpr {
    public boolean parseBoolExpr(String expression) {
        Set<Character> opSet = new HashSet<Character>() {{
            add('|');
            add('&');
            add('!');
        }};
        Stack<Character> opStack = new Stack<>();
        Stack<Character> numsStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (opSet.contains(c)) {
                opStack.push(c);
                continue;//必须
            }
            if (c == ')') {
                char op = opStack.pop();
                List<Character> nums = new ArrayList<>();
                while (numsStack.peek() != '(') {
                    char tmp = numsStack.pop();
                    if (tmp != ',') {
                        nums.add(tmp);
                    }
                }
                numsStack.pop();
                char[] numsArray = new char[nums.size()];
                for (int j = 0; j < nums.size(); j++) {
                    numsArray[j] = nums.get(j);
                }
                numsStack.push(baseOp(op, numsArray));
            } else {
                numsStack.push(c);
            }
        }
        return numsStack.pop() == 't';
    }

    char baseOp(char op, char[] nums) {
        if ('!' == op) {
            if (nums.length != 1) {
                throw new RuntimeException("params count error");
            } else {
                return nums[0] != 't' ? 't' : 'f';
            }
        }

        if ('|' == op) {
            for (char c : nums) {
                if ('t' == c) {
                    return 't';
                }
            }
            return 'f';
        }

        if ('&' == op) {
            for (char c : nums) {
                if ('f' == c) {
                    return 'f';
                }
            }
            return 't';
        }

        throw new RuntimeException("op values error");
    }

    public static void main(String[] args) {
        System.out.println(new ParseBoolExpr().parseBoolExpr("!(&(!(t),&(f),|(f)))"));
    }
}
