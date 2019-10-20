package com.pt.leetcode.bin.search;

/**
 * 求一个数的平方根，精确到小数后6位
 *
 * @author IT技术百货
 */
public class Sqrt {
    public static double sqrt(double a) {
        if (a < 0) {
            throw new RuntimeException("参数非法：a < 0");
        }
        double min, max;
        if (a >= 1) {
            min = 1;
            max = a;
        } else {
            min = 0;
            max = 1;
        }
        while (max - min > 0.0000001) {
            double mid = (min + max) / 2;
            double pow = mid * mid;
            if (pow > a) {
                max = mid;
            } else if (pow < a) {
                min = mid;
            } else {
                return mid;
            }
        }
        return min;
    }

    /**
     * 需要时刻保证max^2 > x && min^2 < x；并且循环体中 max>min
     * leetcode 69
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int min = 1;
        int max = x > Math.sqrt(Integer.MAX_VALUE) ?
                (int) Math.ceil(Math.sqrt(Integer.MAX_VALUE)) : x;
        while (max - min > 1) {
            int mid = (min + max) / 2;
            int square = mid * mid;
            if (square > x) {
                max = mid - 1;
                if (max * max < x) {
                    return max;
                }
            } else if (square < x) {
                min = mid + 1;
                if (min * min > x) {
                    return mid;
                }
            } else {
                return mid;
            }
        }
        //注意不可以用max * max < x 来判断
        if (x / max >= max) {
            return max;
        } else {
            return min;
        }
    }

    public static void main(String[] args) {
        System.out.println(sqrt(0));
        System.out.println(Math.sqrt(0));
        System.out.println(mySqrt(2147395600));
        System.out.println(Math.sqrt(2147395600));
    }
}
