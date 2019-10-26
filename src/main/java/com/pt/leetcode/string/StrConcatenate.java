package com.pt.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetCode:30
 */
public class StrConcatenate {
    public List<Integer> solution(String s, String[] words) {
        if (s.length() < 1 || words.length < 1) {
            return new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        int wordLen = words[0].length();
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (isRightStart(s, i, new HashMap<>(map), words.length, wordLen)) {
                ret.add(i);
            }
        }
        return ret;
    }

    public boolean isRightStart(String str, int startIndex, Map<String, Integer> map, int size, int wordLen) {
        if (wordLen * size > (str.length() - startIndex)) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            int curStartIndex = startIndex + i * wordLen;
            String curWord = str.substring(curStartIndex, curStartIndex + wordLen);
            if (map.getOrDefault(curWord, 0) > 0) {
                map.put(curWord, map.get(curWord) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> result = new StrConcatenate()
                .solution("abcgoodwordgoodfoodokwordgoodgood",
                        new String[]{
                                "word",
                                "good",
                                "good"
                        });
        System.out.println(result);
    }
}
