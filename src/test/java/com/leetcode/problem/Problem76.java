package com.leetcode.problem;

import java.util.HashMap;
import java.util.Map;

public class Problem76 {

    public static void main(String[] args) {
        // BANC
        System.out.println(new Problem76.Solution().minWindow("ADOBECODEBANC", "ABC"));
    }

    static class Solution {
        public String minWindow(String s, String t) {
            return slidingWindow2(s, t);
        }

        /**
         * 对时间进行优化
         * @param s
         * @param t
         * @return
         */
        private String slidingWindow2(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                if (map.containsKey(t.charAt(i))) {
                    map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
                } else {
                    map.put(t.charAt(i), 1);
                }
            }
            // 设置s[i][j] 为滑动窗口，先扩大j直到字符串满足标准
            // 然后滑动i，直到不满足标准。 因为答案只有一个，因此[i-1][j]就是答案。如果有多个答案，j还要继续右移，不断重复。
            int i = 0, j = 1;
            String minString = "";
            // 一上来就管理好map，减少重复计算
            Map<Character, Integer> smap = new HashMap<>();
            smap.put(s.charAt(0), 1);
            while (i <= j && i < s.length() && j <= s.length()) {
                if (!overMap(smap, map)) {
                    do {
                        if (j<s.length()) {
                            if (smap.containsKey(s.charAt(j))) {
                                smap.put(s.charAt(j), smap.get(s.charAt(j)) + 1);
                            } else {
                                smap.put(s.charAt(j), 1);
                            }
                        }
                        j++;
                        // 不符合的时候，结尾字符只有是t里的字符字符，才可能符合
                    } while(j<=s.length() && !map.containsKey(s.charAt(j-1)));
                } else {
                    if (j - i < minString.length() || minString.equals("")) {
                        minString = s.substring(i, j);
                    }
                    if (smap.containsKey(s.charAt(i))) {
                        smap.put(s.charAt(i), smap.get(s.charAt(i)) - 1);
                    }
                    i++;
                }
            }
            return minString;
        }

        private String slidingWindow(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                if (map.containsKey(t.charAt(i))) {
                    map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
                } else {
                    map.put(t.charAt(i), 0);
                }
            }
            // 设置s[i][j] 为滑动窗口，先扩大j直到字符串满足标准
            // 然后滑动i，直到不满足标准。 因为答案只有一个，因此[i-1][j]就是答案。如果有多个答案，j还要继续右移，不断重复。
            int i = 0, j = 0;
            String minString = "";
            while (i <= j && i < s.length() && j <= s.length()) {
                if (!overString(s.substring(i, j), map)) {
                    j++;
                } else {
                    if (j - i < minString.length() || minString.equals("")) {
                        minString = s.substring(i, j);
                    }
                    i++;
                }
            }
            return minString;
        }

        private boolean overString(String s, Map<Character, Integer> tmap) {
            // hash统计t和s的字符数量
            // 保障s的字符数量不少于t
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                if (map.containsKey(s.charAt(i))) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                } else {
                    map.put(s.charAt(i), 0);
                }
            }

            for (char c : tmap.keySet()) {
                if (!map.containsKey(c) || map.get(c) < tmap.get(c)) {
                    return false;
                }
            }

            return true;
        }

        private boolean overMap(Map<Character, Integer> smap, Map<Character, Integer> tmap) {
            // hash统计t和s的字符数量
            // 保障s的字符数量不少于t
            for (char c : tmap.keySet()) {
                if (!smap.containsKey(c) || smap.get(c) < tmap.get(c)) {
                    return false;
                }
            }

            return true;
        }
    }
}
