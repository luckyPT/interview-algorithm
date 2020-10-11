package com.pt.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegexMatch44 {
    public Map<String, Boolean> resultCatch = new HashMap<>();

    public boolean isMatch(String s, String p) {
        if (resultCatch.containsKey(s + "=" + p)) {
            return resultCatch.get(s + "=" + p);
        }
        System.out.println(s.length() + "\t" + p.length());
        p = p.replaceAll("\\*+", "*");
        if ("".equals(p)) {
            boolean tmp = p.equals(s);//s="",p=""的case;
            resultCatch.put(s + "=" + p, tmp);
            return tmp;
        }
        if ("".equals(s)) {
            for (int i = 0; i < p.length(); i++) {
                if ('*' != p.charAt(i)) {
                    resultCatch.put(s + "=" + p, false);
                    return false;
                }
            }
            resultCatch.put(s + "=" + p, true);
            return true;
        }
        if (!(p.contains("*") || p.contains("?"))) {//可以与第一个if合并
            boolean tmp = p.equals(s);
            resultCatch.put(s + "=" + p, tmp);
            return tmp;
        }
        String pre = prefix(p);
        if (pre.length() > 0) {
            if (s.length() < pre.length()) {
                resultCatch.put(s + "=" + p, false);
                return false;
            }
            if (!pre.equals(s.substring(0, pre.length()))) {
                resultCatch.put(s + "=" + p, false);
                return false;
            } else {
                boolean tmp = isMatch(s.substring(pre.length()), p.substring(pre.length()));
                resultCatch.put(s + "=" + p, tmp);
                return tmp;
            }
        }
        String post = postfix(p);
        if (post.length() > 0) {
            if (s.length() < post.length()) {
                resultCatch.put(s + "=" + p, false);
                return false;
            } else {
                if (!post.equals(s.substring(s.length() - post.length()))) {
                    resultCatch.put(s + "=" + p, false);
                    return false;
                } else {
                    boolean tmp = isMatch(s.substring(0, s.length() - post.length()), p.substring(0, p.length() - post.length()));
                    resultCatch.put(s + "=" + p, tmp);
                    return tmp;
                }
            }
        }
        if (p.length() == 1) {
            if ("?".equals(p)) {
                boolean tmp = s.length() == 1;
                resultCatch.put(s + "=" + p, tmp);
                return tmp;
            } else if ("*".equals(p)) {
                resultCatch.put(s + "=" + p, true);
                return true;
            } else {
                boolean tmp = p.equals(s);
                resultCatch.put(s + "=" + p, tmp);
                return tmp;
            }
        } else {
            boolean result = false;
            String curP = "";
            for (int i = 0; i < p.length(); i++) {
                if ('?' == p.charAt(i) || '*' == p.charAt(i)) {
                    if ("".equals(curP)) {
                        curP = "" + p.charAt(i);
                    }
                    break;
                } else {
                    curP += p.charAt(i);
                }
            }
            for (int i = 0; i < s.length() + 1; i++) {
                if (isMatch(s.substring(0, i), curP)) {
                    result = isMatch(s.substring(i), p.substring(curP.length()));
                    if (result) {
                        resultCatch.put(s + "=" + p, true);
                        return true;
                    } else {
                        if (curP.length() == p.length()) {
                            resultCatch.put(s + "=" + p, false);
                            return false;
                        }
                    }
                } else {
                    //没有这部分代码，逻辑没有问题，但可能会增加耗时
                    if (i > 0) {// s=""的时候，可能匹配失败，但后续可能匹配上；s!=""时，如果匹配失败，后续不可能匹配上
                        System.out.println("==============");
                        break;
                    }
                }
            }
            resultCatch.put(s + "=" + p, result);
            return result;
        }
    }

    public String prefix(String p) {
        String result = "";
        for (int i = 0; i < p.length(); i++) {
            if (!('?' == p.charAt(i) || '*' == p.charAt(i))) {
                result += p.charAt(i);
            } else {
                break;
            }
        }
        return result;
    }

    public String postfix(String p) {
        String result = "";
        for (int i = p.length() - 1; i >= 0; i--) {
            if (!('?' == p.charAt(i) || '*' == p.charAt(i))) {
                result = p.charAt(i) + result;
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("------------start--------------");
        String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        long sTime = System.currentTimeMillis();
        System.out.println(new RegexMatch44().isMatch(s, p));
        System.out.println(System.currentTimeMillis() - sTime + " ms");
    }
}
