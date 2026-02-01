package com.leetcode.problem;

import java.util.HashMap;
import java.util.Map;

public class Problem516 {

    public static void main(String[] args) {
        // "cbbd"
        // aabb
        // 5
        System.out.println(new Solution().longestPalindromeSubseq("aabaa"));
        // 4
        System.out.println(new Solution().longestPalindromeSubseq("bbbab"));
        // 21
        System.out.println(new Solution().longestPalindromeSubseq("aaaaaaaaaaaaaaaaaaaaa"));
    }

   static class Solution {
       // 方法2 dp
       // 设dp[i][j]等于是回文字串的长度，那么 dp[i][j] = (dp[i+1][j-1] + (2 && s[i]==s[j]))|| Math.max(dp[i+1][j], dp[i][j-1]);
       public int longestPalindromeSubseq(String s) {
           int[][] dp = new int[s.length()][s.length()];
           for(int i=s.length()-1;i>=0;i--) {
               dp[i][i] = 1;
           }


           for(int i=s.length()-2;i>=0;i--) {
               for(int j=i+1;j<s.length();j++) {
                   if (s.charAt(i) == s.charAt(j)) {
                       dp[i][j] = dp[i+1][j-1] +2;
                   } else {
                       dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                   }
               }
           }
           return dp[0][s.length()-1];
       }

        // 方法1暴力破解，超时
        private final Map<String, Boolean> cache = new HashMap<>();
        private int max = 0;
        public int longestPalindromeSubseq1(String s) {
            longestPalindromeSubseq1(s, 0, "");
            return max;
        }

        public void longestPalindromeSubseq1(String s, int nowI, String now) {
            if (nowI>=s.length()) {
                return;
            }

            for(int i=nowI;i<s.length();i++) {
                String newNow = now+s.charAt(i);
                if(isHuiwen(newNow)) {
                    if(newNow.length() > max) {
                        max = newNow.length();
                    }
                }
                longestPalindromeSubseq1(s, i+1,newNow);
            }

            longestPalindromeSubseq1(s, nowI+1, now);
        }

       public boolean isHuiwen(String s) {
            if (cache.containsKey(s)) {
                return cache.get(s);
            }
            for(int i=0, j=s.length() -1;i<=s.length()/2;i++, j--) {
                if(s.charAt(i) != s.charAt(j)) {
                    cache.put(s, false);
                    return false;
                }
            }

            cache.put(s, true);
            return true;
        }
    }
}
