package com.leetcode.problem;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 */
public class Problem2414 {

    public static void main(String[] args) {
        System.out.println(new Solution().longestContinuousSubstring("abacaba"));
        System.out.println(new Solution().longestContinuousSubstring("abcde"));
    }

    static class Solution {
        String wordStr = "abcdefghijklmnopqrstuvwxyz";
        public int longestContinuousSubstring(String s) {
            int length = s.length();
            int start = 0;
            int end = 0;
            int maxLength = 0;
            while (start < length && end <= length) {
                String substring = s.substring(start, end);
                if(wordStr.contains(substring)) {
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
    }
}
