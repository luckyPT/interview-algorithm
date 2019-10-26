package com.pt.leetcode;

/**
 * leetCode 171
 */
public class ExcelColNum2ColTitle {
    public static int colTitle2Num(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += Math.pow(26, (s.length() - 1 - i)) * (s.charAt(i) - 'A' + 1);
        }
        return sum;
    }

    /**
     * 递归实现
     *
     * @param s
     * @param i
     * @param sum
     * @return
     */
    public static int colTitle2NumByRecursion(String s, int i, int sum) {
        if (i >= s.length()) {
            return sum;
        }
        sum += (s.charAt(i) - 'A' + 1) * Math.pow(26, s.length() - 1 - i);
        return colTitle2NumByRecursion(s, i + 1, sum);
    }

    public static String colNum2Title(int num) {
        StringBuilder sb = new StringBuilder();
        int k, exp;
        int remain = num;
        int numCopy;
        while (remain > 26) {
            numCopy = remain;
            exp = 0;
            while (numCopy > 26) {
                numCopy = numCopy / 26;
                exp += 1;
            }
            k = remain / (int) Math.pow(26, exp);
            sb.append((char) (k - 1 + 'A'));
            remain = remain % (int) Math.pow(26, exp);
        }
        sb.append((char) (remain - 1 + 'A'));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(colNum2Title(703));

        System.out.println(colTitle2NumByRecursion("ZY", 0, 0));
        System.out.println(colTitle2Num("ZY"));
    }
}
