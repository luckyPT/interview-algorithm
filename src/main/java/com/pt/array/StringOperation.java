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

    public static void main(String[] args) {
        String[] parts = splitMoreParts("ababcbacadefegdehijhklij");
        System.out.println(Arrays.toString(parts));
        System.out.println(getMaxUniqueSubStr("abcdadefrderhuhuasdfghjk"));
        Map<Character, Integer> s = new HashMap<>();
        s.put('x', 1);
        s.put('x', 2);
        System.out.println(s.get('x'));
    }
}
