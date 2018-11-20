package com.pt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StringOperation {
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
        return ret.toArray(new String[ret.size()]);
    }

    public static void main(String[] args) {
        String[] parts = splitMoreParts("ababcbacadefegdehijhklij");
        System.out.println(Arrays.toString(parts));
    }
}
