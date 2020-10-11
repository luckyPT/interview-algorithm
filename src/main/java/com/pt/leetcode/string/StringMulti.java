package com.pt.leetcode.string;

import java.util.Stack;

public class StringMulti {
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if (num2.length() == 1) {
            String result = "0";
            while (!"0".equals(num2)) {//这里通过累加方式计算乘法，代码整体复杂度为m*n + n^2; 如果直接按照惩罚方式计算，可优化至m*n
                result = add(result, num1);
                num2 = subOne(num2);
            }
            return result;
        } else {//num2有多位的时候，算法优化方案
            String result = "0";
            for (int i = 0; i < num2.length(); i++) {
                String tmpResult = multiply(num1, num2.substring(i, i + 1));
                for (int j = i + 1; j < num2.length(); j++) {
                    tmpResult += "0";
                }
                result = add(result, tmpResult);
            }
            return result;
        }
    }

    public String subOne(String num) {
        String result;
        if (num.endsWith("0")) {
            result = subOne(num.substring(0, num.length() - 1)) + "9";
        } else {
            char last = (char) (num.charAt(num.length() - 1) - 1);
            result = num.substring(0, num.length() - 1) + last;
        }
        if ("0".equals(result)) {
            return result;
        }
        int i;
        for (i = 0; i < result.length(); i++) {
            if ('0' != result.charAt(i)) {
                break;
            }
        }
        return result.substring(i);
    }

    public String add(String num1, String num2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for (int i = 0; i < num1.length(); i++) {
            stack1.push(Integer.valueOf(num1.charAt(i) + ""));
        }
        for (int i = 0; i < num2.length(); i++) {
            stack2.push(Integer.valueOf(num2.charAt(i) + ""));
        }
        int remain = 0;
        Stack<Integer> result = new Stack<>();
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int a1 = stack1.isEmpty() ? 0 : stack1.pop();
            int a2 = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = a1 + a2 + remain;
            remain = sum > 9 ? 1 : 0;
            result.push(sum % 10);
        }
        if (remain == 1) {//容易漏掉
            result.push(1);
        }
        String res = "";
        while (!result.isEmpty()) {
            res = res + result.pop(); //注意，别写成result.pop() + res;
        }
        return res;
    }

    public static void main(String[] args) {
        StringMulti multi = new StringMulti();
        System.out.println(multi.multiply("123456", "987"));
        System.out.println(123456 * 987);
        System.out.println("*a**a***".replaceAll("\\*+", "*"));
    }
}
