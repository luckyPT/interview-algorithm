package com.pt.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * leetCode 227
 *
 * @author panTeng
 */
public class CalculateStrValue {
    private static Map<Character, Integer> opPriority = new HashMap<Character, Integer>() {{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
    }};

    private int findNumEndPosition(String s, int i) {
        if (i >= s.length()) {
            throw new RuntimeException("i > s.length");
        }
        int j = i;
        for (; j < s.length(); j++) {
            if (s.charAt(j) > '9' || s.charAt(j) < '0') {
                break;
            }
        }
        return j - 1;
    }

    private int twoNumCalculate(char op, int num1, int num2) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return result;
    }

    /**
     * 遇见数字，直接压入数字栈
     * 遇见操作符，跟操作栈中的比较，
     * 如果栈顶优先级高或者相等，则进行计算并将计算结果压入数字栈，继续循环比较；
     * 如果栈顶优先级低或者栈为空，直接入栈；
     *
     * @param s
     * @return
     */
    private int calculate(String s) {
        s = s.replace(" ", "");
        MyArrayStack numStack = new MyArrayStack(16);
        Stack<Character> operatorStack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int j = findNumEndPosition(s, i);
                numStack.push(Integer.parseInt(s.substring(i, j + 1)));
                i = j;//注意最后会执行i++
            } else {
                while (!operatorStack.empty() && opPriority.get(s.charAt(i)) <= opPriority.get(operatorStack.peek())) {
                    int num1 = numStack.pop();
                    int num2 = numStack.pop();
                    numStack.push(twoNumCalculate(operatorStack.pop(), num2, num1));//注意num2 与 num1的位置关系,并且需要将计算结果压入栈

                }
                operatorStack.push(s.charAt(i));//操作符入栈
            }
        }
        while (!operatorStack.empty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            numStack.push(twoNumCalculate(operatorStack.pop(), num2, num1));
        }
        return numStack.top();
    }

    public static void main(String[] args) {
        System.out.println(new CalculateStrValue().calculate("3+2*2+4/2"));
    }
}
