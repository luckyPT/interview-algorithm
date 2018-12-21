package com.pt.array;

import java.util.*;

public class StringOperation {
    /**
     * 找出最长的不含重复字符的子串
     *
     * @param s
     * @return
     */
    static String getMaxUniqueSubStr(String s) {
        System.out.println("原串：" + s);
        char[] chars = s.toCharArray();
        int min = 0;
        int[] startEnd = new int[]{0, 0};
        Map<Character, Integer> char2Index = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            min = Math.max(min, char2Index.getOrDefault(chars[i], -1));
            if (i - min > startEnd[1] - startEnd[0]) {
                startEnd[0] = min;
                startEnd[1] = i;
            }
            char2Index.put(chars[i], i);
        }

        return s.substring(startEnd[0], startEnd[1] + 1);
    }

    /**
     * 将str分割成不同的子串，并满足如下条件：
     * 1. 每个元素仅出现在一个子串中
     * 2. 选择的分割方式得到的子串数量最多
     *
     * @param str
     * @return
     */
    static String[] splitMoreParts(String str) {
        HashMap<String, Integer> strMaxIndex = new HashMap<>();
        String[] chars = str.split("");
        for (int i = 0; i < chars.length; i++) {
            strMaxIndex.put(chars[i], i);
        }
        int lastIndex = 0;
        int maxIndex = 0;
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            int curMaxIndex = strMaxIndex.get(chars[i]);
            if (maxIndex < curMaxIndex) {
                maxIndex = curMaxIndex;
            }
            if (maxIndex == i) {
                ret.add(str.substring(lastIndex, maxIndex + 1));
                lastIndex = maxIndex + 1;
            }

        }
        return ret.toArray(new String[0]);
    }

    /**
     * 寻找str1和str2的最长公共子序列（子序列可以不连续）
     * 关键点：构造M*N的矩阵a，a[m][n]表示str1的前m个元素和str2的前n个元素
     * 的最长公共子序列。
     * 先确定第一行和第一列的值，之后对于a[m][n+1]
     * str1[m]和str2[n+1]
     * 不相等的情况，说明两个字符至少有一个不能作为最长公共子序列
     * 的最后一个值，因此取max(a[m][n],a[m-1][n+1])
     * <p>
     * 相等的情况，作为公共子序列的最后一个值，公共子序列长度为a[m-1][n] + 1
     * 与max(a[m][n],a[m-1][n+1])比较，取最大值
     * a[m][n+1] = str1[m].equals(str2[n+1]) ? max(a[m-1][n] + 1,a[m][n],a[m-1][n+1]) : max(a[m][n],a[m-1][n+1])
     *
     * @param str1 - M个字符的
     * @param str2 - N个字符
     * @return 公共子序列
     */
    static String getMaxLengthCommonSeq(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[][] a = new int[chars1.length][chars2.length];
        int[] index = new int[2];
        int max = 0;
        //处理第一行
        for (int i = 0; i < chars2.length; i++) {
            if (chars1[0] == chars2[i]) {
                a[0][i] = 1;
                max = 1;
                index[0] = 0;
                index[1] = i;
                for (i = i + 1; i < chars2.length; i++) {
                    a[0][i] = 1;
                }
                break;
            }
        }
        //处理第一列
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[0]) {
                a[i][0] = 1;
                max = 1;
                index[0] = i;
                index[1] = 0;
                for (i = i + 1; i < chars1.length; i++) {
                    a[i][0] = 1;
                }
                break;
            }
        }

        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i] == chars2[j]) {
                    a[i][j] = Math.max(a[i - 1][j - 1] + 1, Math.max(a[i - 1][j], a[i][j - 1]));
                } else {
                    a[i][j] = Math.max(a[i - 1][j], a[i][j - 1]);
                }
                if (a[i][j] > max) {
                    max = a[i][j];
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        int pre = 0;
        StringBuilder ret = new StringBuilder();
        for (int j = 0; j < chars2.length; j++) {
            if (a[index[0]][j] - pre > 0) {
                ret.append(chars2[j]);
                pre = a[index[0]][j];
            }
        }
        return ret.toString();
    }

    /**
     * 寻找str1和str2的最长公共子串（子串必须连续）
     * <p>
     * 方法1：构造M*N矩阵a，a[m][n] = str1[m].equals(str2[n]) ? 1 : 0
     * 按照对角线遍历，找到连续出现1的最长个数
     *
     * @param str1 - M个字符的
     * @param str2 - N个字符
     * @return 公共子序列
     */
    static String getMaxLengthCommonStr(String str1, String str2) {
        return null;
    }

    public static void main(String[] args) {
        String[] parts = splitMoreParts("ababcbacadefegdehijhklij");
        System.out.println(Arrays.toString(parts));
        System.out.println(getMaxUniqueSubStr("abcdadefrderhuhuasdfghjk"));
        System.out.println(getMaxLengthCommonSeq("2345efd", "1ab2345cd"));
    }
}
