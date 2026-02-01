package com.leetcode.problem;

import java.util.HashSet;
import java.util.Set;

public class Problem3 {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution().lengthOfLongestSubstring("bbbbb"));
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int length = s.length();
            int start = 0;
            int end = 0;
            int maxLength = 0;
            while (start < length && end <= length) {
                String substring = s.substring(start, end);
                if(noRepeat(substring)) {
                    if(substring.length() > maxLength) {
                        maxLength = substring.length();
                    }
                    end++;
                } else {
                    start++;
                }
            }
            return maxLength;
        }

        private boolean noRepeat(String s) {
            Set<Character> set = new HashSet<>();
            for(char c : s.toCharArray()) {
                set.add(c);
            }
            return set.size() == s.length();
        }
    }
}
