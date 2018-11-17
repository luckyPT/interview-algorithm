package com.pt;

import java.util.*;

public class BigNumber {
    /**
     * 规定 a>=b且a, b > 0。
     * 关键点：从最后一位开始，按位相减，将结果放入栈中；当b已经到最高位之后，再高的位按0算。注意借位即可。
     *
     * @param line "1231231237812739878951331231231237812739878951331231231237812739878951331230000000000000000000000001-331231231237812739878951331231231"
     * @return "1231231237812739878951331231231237812739878951331231231237812739878620099998762187260121048668768770"
     */
    public static String sub(String line) {
        String[] nums = line.split("-");
        String[] num1 = nums[0].split("");
        String[] num2 = nums[1].split("");
        Stack<Integer> results = new Stack<>();
        boolean lend = false;

        for (int i = 0; i < num1.length; i++) {
            int tmpResult;
            if (i < num2.length) {
                tmpResult = Integer.parseInt(num1[num1.length - 1 - i]) - Integer.parseInt(num2[num2.length - 1 - i]);
            } else {
                tmpResult = Integer.parseInt(num1[num1.length - 1 - i]);
            }
            if (lend) {
                tmpResult -= 1;
            }
            if (tmpResult < 0) {
                lend = true;
                tmpResult += 10;
                results.push(tmpResult);
            } else {
                results.push(tmpResult);
                lend = false;
            }
        }

        StringBuilder result = new StringBuilder();
        while (!results.empty()) {
            Integer t = results.pop();
            if (result.length() == 0 && t == 0) {
                continue;
            }
            result.append(t);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(sub("1231231237812739878951331231231237812739878951331231231237812739878951331230000000000000000000000001-331231231237812739878951331231231"));
    }
}
