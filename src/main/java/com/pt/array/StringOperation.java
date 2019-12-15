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
            if (char2Index.containsKey(chars[i]) && char2Index.get(chars[i]) >= min) {
                min = char2Index.get(chars[i]) + 1;
            }
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
     * @param a - M个字符的
     * @param b - N个字符
     * @return 公共子序列
     */
    static String getMaxLengthCommonStr(String a, String b) {
        int[][] isCommon = new int[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    isCommon[i][j] = 1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int indexI = -1, indexJ = -1;
        for (int i = 1; i < a.length(); i++) {
            for (int j = 1; j < b.length(); j++) {
                if (isCommon[i][j] == 1) {
                    isCommon[i][j] += isCommon[i - 1][j - 1];
                    if (max < isCommon[i][j]) {
                        max = isCommon[i][j];
                        indexI = i;
                        indexJ = j;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (indexI >= 0 && indexJ >= 0) {
            if (isCommon[indexI][indexJ] > 0) {
                sb.append(b.charAt(indexJ));
                indexI--;
                indexJ--;
            } else {
                return sb.reverse().toString();
            }
        }
        return sb.reverse().toString();
    }

    /**
     * 判断字符串是否是回文
     *
     * @param s A man, a plan, a canal: Panama;race a car
     * @return true;false
     */
    static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int p = 0;
        int l = s.length() - 1;
        while (p < l) {
            char pChar = s.charAt(p);
            while (p < l && !((pChar >= '0' && pChar <= '9') || (pChar >= 'a' && pChar <= 'z'))) {
                p++;
                if (p < l) {
                    pChar = s.charAt(p);
                } else {
                    return true;
                }
            }

            char lChar = s.charAt(l);
            while (p < l && !((lChar >= '0' && lChar <= '9') || (lChar >= 'a' && lChar <= 'z'))) {
                l--;
                if (p < l) {
                    lChar = s.charAt(l);
                } else {
                    return true;
                }
            }

            if (pChar != lChar) {
                return false;
            } else {
                p++;
                l--;
            }
        }
        return true;
    }

    /**
     * 将一个字符串分割成回文串list，返回所有可能的方案
     * 回溯算法：
     * 使用递归进行深度搜索（类似于穷举），一个关键点是回溯，可通过双端队列实现。
     * 另外可以通过条件限制，避免一些不必要的搜索
     * 类似的八皇后问题，也是用回溯算法求解
     *
     * @param s -
     * @return
     */
    private static List<List<String>> partitionPalindrome(String s) {
        List<List<String>> ret = new ArrayList<>();
        partitionPalindromeHelper(s, 0, new LinkedList<>(), ret);
        return ret;
    }

    private static void partitionPalindromeHelper(String s, int start, Deque<String> tmp, List<List<String>> result) {
        if (start >= s.length()) {
            List<String> tmpList = new LinkedList<>(tmp);
            result.add(tmpList);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s.substring(start, i + 1))) {
                tmp.addLast(s.substring(start, i + 1));
                partitionPalindromeHelper(s, i + 1, tmp, result);
                tmp.pollLast();
            }
        }
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * <p>
     * 说明：
     * <p>
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     *
     * @param s        str
     * @param wordDict list
     * @return bool
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakRecursionHelper(s, 0, wordDict);
    }

    /**
     * 回溯算法
     * 倒着搜索相对更节省一点时间，考虑s=aaa
     * wordDict = [a,aa,aaa]
     * 递归实现相对耗时较多，改用循环实现
     *
     * @param s        -
     * @param start    -
     * @param wordDict -
     * @return
     */
    private static boolean wordBreakRecursionHelper(String s, int start, List<String> wordDict) {
        if (start >= s.length()) {
            return true;
        }
        for (int i = s.length(); i > start; i--) {
            String t = s.substring(start, i);
            if (wordDict.contains(t)) {
                if (wordBreakRecursionHelper(s, i, wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 动态规划，比回溯算法要快很多
     *
     * @param s
     * @param wordDict
     * @return
     */
    private static boolean wordBreakLoop(String s, List<String> wordDict) {
        boolean[] canSeg = new boolean[s.length() + 1];
        canSeg[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (canSeg[j] && wordDict.contains(s.substring(j, i))) {
                    canSeg[i] = true;
                    break;
                }
            }
        }
        return canSeg[s.length()];
    }

    /**
     * 时间复杂度与空间复杂度很高
     * <p>
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     * <p>
     * 您可以假定该字符串只包含小写字母。
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                if (!s.substring(1).contains("" + s.charAt(0))) {
                    return 0;
                } else {
                    continue;
                }
            }
            if (i == s.length() - 1) {
                if (!s.substring(0, s.length() - 1).contains("" + s.charAt(s.length() - 1))) {
                    return s.length() - 1;
                } else {
                    return -1;
                }
            }

            if (!s.substring(0, i).contains("" + s.charAt(i)) && !s.substring(i + 1).contains("" + s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 时间复杂度与空间复杂度略低
     *
     * @param s -
     * @return
     */
    public static int firstUniqChar2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.getOrDefault(s.charAt(i), -1) == i) {
                return i;
            } else {
                map.remove(s.charAt(i));
            }
        }
        return -1;
    }

    /**
     * 利用“您可以假定该字符串只包含小写字母。”
     * 将时间复杂度降到1
     *
     * @param s
     * @return
     */
    public static int firstUniqChar3(String s) {
        int res = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = s.indexOf(ch);
            if (index != -1 && index == s.lastIndexOf(ch)) {
                res = (res == -1 || res > index) ? index : res;
            }
        }
        return res;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     * 可以只考虑小写字母。
     * 优化方式：由于只考虑小写字母（可以将字母视为整数），可以使用数组代替map，map的key用数组下标表示，值记录在数组中；
     * 在时间复杂度和空间复杂度上都可以得到一定的优化。
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if ("".equals(s)) {
            return "".equals(t);
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (map.containsKey(i) && map.get(i) != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
     * <p>
     * 回溯算法实现，但时间复杂度很高
     *
     * @param s        -
     * @param wordDict -
     * @return 返回所有这些可能的句子。
     */
    public static List<String> sentencesBreak(String s, List<String> wordDict) {
        return sentencesBreakHelper(s, 0, wordDict, new LinkedList<>(), new ArrayList<>());
    }

    public static List<String> sentencesBreakHelper(String s, int start, List<String> wordDict,
                                                    Deque<String> tmp, List<String> ret) {
        if (start >= s.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s1 : tmp) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(s1);
            }
            ret.add(stringBuilder.toString());
            return ret;
        }

        for (int i = start + 1; i < s.length() + 1; i++) {//注意 i<s.length()+1
            String s1 = s.substring(start, i);
            if (wordDict.contains(s1)) {
                tmp.addLast(s1);
                sentencesBreakHelper(s, i, wordDict, tmp, ret);
                tmp.pollLast();
            }
        }
        return ret;
    }

    public static List<String> sentencesBreakLoop(String s, List<String> wordDict) {
        List<String> ret = new LinkedList<>();

        return ret;
    }

    public static void main(String[] args) {
        String[] parts = splitMoreParts("ababcbacadefegdehijhklij");
        System.out.println(Arrays.toString(parts));
        System.out.println(getMaxUniqueSubStr("aabcdead"));
        System.out.println(getMaxLengthCommonSeq("2345efd", "1ab2345cd"));

        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));

        List<List<String>> ret = partitionPalindrome("cdd");
        ret.forEach(t -> {
            t.forEach(t1 -> System.out.print(t1 + ","));
            System.out.println();
        });

        List<String> wordList = new ArrayList<String>() {{
            add("leet");
            add("code");
        }};

        System.out.println(wordBreakLoop("leetcode", wordList));
        System.out.println(isAnagram("aacc", "ccac"));
        List<String> list = new LinkedList<String>() {{
            add("cat");
            add("cats");
            add("and");
            add("sand");
            add("dog");
        }};
        sentencesBreak("catsanddog", list)
                .forEach(s -> {
                    System.out.print(s + ",");
                    System.out.println();
                });
        sentencesBreakLoop("catsanddog", list)
                .forEach(s -> {
                    System.out.print(s + ",");
                    System.out.println();
                });

        System.out.println(getMaxLengthCommonStr("abcdefghijk", "abdefgijk"));
    }
}
