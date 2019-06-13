package com.pt;

public class Number {
    /**
     * 注意x为0和y为0或负值的边界条件，以及求倒数的时候，分母为0
     * 注意1/0 会抛异常，1/0.0结果为Infinity 不会抛异常
     *
     * @param x 底
     * @param y 幂
     * @return x^y
     */
    public static double pow(double x, int y) throws Exception {
        if (numEquals(x, 0)) {
            if (y <= 0) {
                //没有意义
                throw new Exception("无意义计算,x=0,y=" + y);
            } else {
                return 0;
            }
        }
        if (y == 0) {
            return 1;
        }
        if (y == 1) {
            return x;
        }
        int absY = y > 0 ? y : 0 - y;
        int half = absY / 2;
        double dot1 = pow(x, half);
        double dot2 = pow(x, absY - half);

        if (numEquals(dot1 * dot2, 0)) throw new Exception("/ by zero");
        return y > 0 ? dot1 * dot2 : 1 / (dot1 * dot2);
    }

    public static boolean numEquals(double a, double b) {
        double diff = a - b;
        return diff < 0.00000000001 && diff > -0.00000000001;
    }

    /**
     * 从1开始，打印至最大的n位数，比如：n=3，打印1，2，3...，998，999
     * 考虑大数情况
     *
     * @param n
     */
    public static void printNum(int n) {
        char[] chars = new char[n];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '0';
        }
        while (true) {
            //判断是否结束
            boolean isEnd = true;
            int i = chars.length - 1;
            for (; i >= 0; i--) {
                if (chars[i] != '9') {
                    isEnd = false;
                    break;
                }
            }
            if (isEnd) {
                break;
            }
            chars[i++] += 1;
            for (; i < chars.length; i++) {
                chars[i] = '0';
            }
            int firstIndex = 0;
            for (; firstIndex < chars.length; firstIndex++) {
                if (chars[firstIndex] != '0') {
                    break;
                }
            }
            for (; firstIndex < chars.length; firstIndex++) {
                System.out.print(chars[firstIndex]);
            }
            System.out.print(", ");
        }
    }

    public static void main(String[] args) throws Exception {
        double x = 3;
        int y = 3;
        System.out.println(pow(x, y));
        System.out.println(Math.pow(x, y));
        printNum(1);
    }
}
