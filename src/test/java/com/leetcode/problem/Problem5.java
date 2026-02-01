package com.leetcode.problem;

import java.util.HashMap;
import java.util.Map;

public class Problem5 {

    public static void main(String[] args) {
        // bab aba
//        System.out.println(new Solution().longestPalindrome("babad"));
        // bb
        System.out.println(new Solution().longestPalindrome("cbbd"));
        // a
        //System.out.println(new Solution().longestPalindrome("a"));
        // bb
        //System.out.println(new Solution().longestPalindrome("bb"));
        // aca
        // System.out.println(new Solution().longestPalindrome("aacabdkacaa"));
    }

   static class Solution {
       // 方法2 dp
       // 设dp[i][j] == true 表示i,j为回文串。 那么题意变为找 max(j-i)
       // dp[i][j] = d[i+1][j-1] == true && s[i] == s[j]
       // dp[i][i] = true
       public String longestPalindrome(String s) {
           boolean[][] dp = new boolean[s.length()][s.length()];
           for(int i=0;i<s.length();i++) {
               dp[i][i] = true;
           }

           for(int i=s.length()-2;i>=0;i--) {
               for(int j = i+1; j< s.length(); j++) {
                   if (i+1 > j-1) {
                       dp[i][j] = s.charAt(i) == s.charAt(j);
                   } else {
                       dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j);
                   }
               }
           }

           int max = 0;
           String maxString = "";
           for(int i=0;i< s.length();i++) {
               for(int j = 0; j< s.length(); j++) {
                   if(dp[i][j] && j-i >= max) {
                       max = j-i;
                       maxString = s.substring(i,j+1);
                   }
               }
           }
           return maxString;
       }


       // 方法1暴力破解,超出内存限制或时间限制
       private final Map<String, Boolean> cache = new HashMap<>();

       public String longestPalindrome1(String s) {
           int max = 0;
           String maxString = "";
           if (s.length() > 0) {
               maxString = s.substring(0, 1);
           }
           for(int i=0;i<s.length();i++) {
               for(int j=s.length();j>i;j--) {
                   String newNow = s.substring(i,j);
                   if(isHuiwen(newNow)) {
                       if(newNow.length() > max) {
                           max = newNow.length();
                           maxString = newNow;
                       }
                   }
               }
           }
           return maxString;
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
