package com.leetcode.problem;

/**
 *
 */
public class Problem1768 {

    public static void main(String[] args) {
        // 1,2,3,1\2,1\3,2\3,1\2\3
        System.out.println(new Solution().mergeAlternately("abc", "pqr"));
    }

    static class Solution {
        public String mergeAlternately(String word1, String word2) {
            StringBuilder mergeStr = new StringBuilder();
            int minLength = Math.min(word1.length(), word2.length());
            for (int i = 0; i < minLength; i++) {
                mergeStr.append(word1.charAt(i)).append(word2.charAt(i));
            }
            mergeStr.append(word1.length() > minLength ? word1.substring(minLength) : word2.substring(minLength));
            return mergeStr.toString();
        }
    }
}
