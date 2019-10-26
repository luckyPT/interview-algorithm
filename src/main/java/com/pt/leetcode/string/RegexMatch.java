package com.pt.leetcode.string;

import java.util.*;

/**
 * leetCode10
 */
public class RegexMatch {
    private Map<Tuple, Boolean> map = new HashMap<>();//避免重复计算，否则会有大量的重复计算

    public boolean isMatch(String s, String p) {
        if (".*".equals(p)) {
            return true;
        }
        if ("".equals(p)) {
            return "".equals(s);
        }
        boolean r = isMatch(s, str2list(p), 0, 0);
        return r;
    }

    public void addResult(int i1, int i2, boolean b) {
        map.put(new Tuple(i1, i2), b);
    }


    public boolean isMatch(String s, List<String> strs, int index1, int index2) {
        if (map.containsKey(new Tuple(index1, index2))) {
            return map.get(new Tuple(index1, index2));
        }
        if (index1 > s.length() - 1) {
            for (int i = index2; i < strs.size(); i++) {
                if (!strs.get(i).contains("*")) {
                    addResult(index1, index2, false);
                    return false;
                }
            }
            addResult(index1, index2, true);
            return true;
        }

        if (index2 > strs.size() - 1) {
            boolean r = (index1 >= s.length());
            addResult(index1, index2, r);
            return r;
        }
        String preStr = strs.get(index2);
        if ((!".".equals(preStr)) && (!preStr.contains("*"))) {
            if (index1 + preStr.length() > s.length()) {
                addResult(index1, index2, false);
                return false;
            }
            String sSub = s.substring(index1, index1 + preStr.length());
            if (preStr.equals(sSub)) {
                boolean r = isMatch(s, strs, index1 + preStr.length(), index2 + 1);
                addResult(index1, index2, r);
                return r;
            } else {
                addResult(index1, index2, false);
                return false;
            }
        } else if (".".equals(preStr)) {
            boolean r = isMatch(s, strs, index1 + 1, index2 + 1);
            addResult(index1, index2, r);
            return r;
        } else if (preStr.contains("*")) {
            char preChar = preStr.charAt(0);
            boolean ret = isMatch(s, strs, index1, index2 + 1);
            if (ret) {
                addResult(index1, index2, true);
                return true;
            }
            for (int i = index1; i < s.length(); i++) {
                if (preChar != s.charAt(i) && preChar != '.') {
                    break;
                } else {
                    ret = isMatch(s, strs, i + 1, index2);
                    if (ret) {
                        addResult(index1, index2, true);
                        return true;
                    }
                }
            }
            addResult(index1, index2, false);
            return false;
        } else {
            throw new RuntimeException("not expected :" + preStr);
        }
    }

    public List<String> str2list(String p) {
        p += "a";//补位而已，并不处理
        List<String> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length() - 1; i++) {
            if (p.charAt(i + 1) != '*') {
                if (p.charAt(i) == '.') {
                    if (sb.toString().length() > 0) {
                        ret.add(sb.toString());
                        sb = new StringBuilder();
                    }
                    ret.add(".");
                } else {
                    sb.append(p.charAt(i));
                }
            } else {
                if (sb.toString().length() > 0) {
                    ret.add(sb.toString());
                    sb = new StringBuilder();
                }
                if (ret.size() == 0 || !ret.get(ret.size() - 1).contains("*") || ret.get(ret.size() - 1).charAt(0) != p.charAt(i)) {//解决正则不规范，比如a*a*a*a* 实际上一个a*即可
                    ret.add(p.charAt(i) + "*");
                }
                i++;
            }
        }
        if (sb.toString().length() > 0) {
            ret.add(sb.toString());
        }
        return ret;
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        System.out.println(new RegexMatch().isMatch("aaa", "a*a"));
        System.out.println(System.currentTimeMillis() - s);
    }

    class Tuple {
        int i1;
        int i2;

        public Tuple(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tuple tuple = (Tuple) o;
            return i1 == tuple.i1 &&
                    i2 == tuple.i2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i1, i2);
        }
    }
}
