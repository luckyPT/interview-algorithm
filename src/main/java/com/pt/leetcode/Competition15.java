package com.pt.leetcode;

import java.util.*;

/**
 * leetCode:第 15 场双周赛
 */
public class Competition15 {
    public static void main(String[] args) {

        CombinationIterator iterator = new CombinationIterator("fiklnuy", 3);
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.hasNext());
    }

    public int findSpecialInteger(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        int result = arr[0];
        for (int n : map.keySet()) {
            if (map.get(n) > map.get(result)) {
                result = n;
            }
        }
        return result;
    }

    public int removeCoveredIntervals(int[][] intervals) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            boolean cover = false;
            for (int j = 0; j < intervals.length; j++) {
                if (j != i && intervals[j][0] <= intervals[i][0] && intervals[j][1] >= intervals[i][1]) {
                    cover = true;
                    break;
                }
            }
            if (!cover) {
                result.add(i);
            }
        }
        return result.size();
    }

    static class CombinationIterator {
        private String str;
        private int combinationLength;
        private int[] index;

        public CombinationIterator(String characters, int combinationLength) {
            this.str = characters;
            this.combinationLength = combinationLength;
        }

        public String next() {
            if (index == null) {
                index = new int[combinationLength];
                for (int i = 0; i < combinationLength; i++) {
                    index[i] = i;
                }
                return createStrByIndex();
            }
            if (index[index.length - 1] < str.length() - 1) {
                index[index.length - 1] += 1;
                return createStrByIndex();
            }

            for (int i = index.length - 2; i >= 0; i--) {
                if (index[i] < index[i + 1] - 1) {
                    index[i] += 1;
                    for (int j = i + 1; j < index.length; j++) {
                        index[j] = index[j - 1] + 1;
                    }
                    return createStrByIndex();
                }
            }

            return null;
        }

        public boolean hasNext() {
            if (index == null) {
                return true;
            }
            if (index[0] == str.length() - index.length) {
                return false;
            } else {
                return true;
            }
        }

        private String createStrByIndex() {
            StringBuilder sb = new StringBuilder();
            for (int i : index) {
                sb.append(str.charAt(i));
            }
            return sb.toString();
        }
    }
}
