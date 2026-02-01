package com.leetcode.problem;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

public class Problem139 {

    public static void main(String[] args) {
        // true
        System.out.println(new Solution().wordBreak("leetcode",
                Lists.newArrayList("leet", "code")));
        // false
        System.out.println(new Solution().wordBreak("aaaaaaa",
                Lists.newArrayList("aaaa", "aa")));
        // false
        System.out.println(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Lists.newArrayList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
        // true
        System.out.println(new Solution().wordBreak("cars",
                Lists.newArrayList("car","ca","rs")));
    }

    static class Solution {
        // 方法2， 设定dp[i]为前i个字符是否可以被单词表拼接。那么
        // dp[i] = dp[i-j] && dp[i-j]==word   其中j=word的长度
        // 当然dp[0] = true
        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() +1];
            dp[0] = true;
            Set<String> myWordDict = new HashSet<>(wordDict);
            for (int i = 1; i <= s.length(); i++) {
                // System.out.println(s.substring(0, i));
                for (String word : myWordDict) {
                    if (i-word.length() < 0) {
                        continue;
                    }
                    String substring = s.substring(i-word.length(), i);
                    // 防止true被false覆盖，有一个单词能组成即可
                    dp[i] = dp[i] || (dp[i-word.length()] && substring.equals(word));
                }
            }

            return dp[s.length()];
        }


        // 方法1，暴力穷举法，依托s，查找所有的单词。单词可以复用
        private Map<String, Boolean> cache = new HashMap<>();

        public boolean wordBreak2(String s, List<String> wordDict) {
            if (cache.containsKey(s)) {
                return cache.get(s);
            }
            for (int i = 0; i <= s.length(); i++) {
                String substring = s.substring(0, i);
                if (wordDict.contains(substring)) {
                    if (i + 1 > s.length()) {
                        cache.put(s, true);
                        return true;
                    }

                    boolean isOk = wordBreak(s.substring(i), wordDict);
                    if (isOk) {
                        return true;
                    }
                }
            }
            cache.put(s, false);
            return false;
        }
    }
}
