package com.leetcode.problem;

public class Problem10 {

    public static void main(String[] args) {
        // true
        System.out.println(new Problem10.Solution().isMatch("aab", "c*a*b"));
        // true
        System.out.println(new Problem10.Solution().isMatch("ab", ".*"));
    }

   static class Solution {
       public boolean isMatch(String s, String p) {
           // dp[i][j] 为p[i]匹配s[j]的结果
           // if p[i] 是字符 then dp[i][j] = dp[i-1][j-1] && s[j] = p[i]
           // if p[i] 是. then dp[i][j] = dp[i-1][j-1]
           // if p[i] 是* then dp[i][j] = dp[i-2][j] || (dp[i][j-1] && s[j] = p[i-1]) || (dp[i][j-1] && p[i-1] == '.')
           //                             不用这个a*     匹配这个a* 要等于a        匹配.*
           // dp[0][0] = true

           boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
           dp[0][0] = true;
           // 初始化处理 a*a*a*a*的匹配空问题
           for(int i = 2; i < p.length() + 1; i += 2) {
               dp[i][0] = dp[i - 2][0] && p.charAt(i - 1) == '*';
           }

           for(int i=1;i<=p.length();i++) {
               for(int j=1;j<=s.length(); j++) {
                   if(p.charAt(i-1) == '.') {
                       dp[i][j] = dp[i-1][j-1];
                   } else if(p.charAt(i-1) == '*') {
                       if (dp[i-2][j]) dp[i][j] = true;
                       else if (dp[i][j-1] && s.charAt(j - 1) == p.charAt(i - 2)) dp[i][j] = true;
                       else if (dp[i][j-1] && p.charAt(i - 2) == '.') dp[i][j] = true;
                   } else if(p.charAt(i-1) == s.charAt(j-1)) {
                       dp[i][j] = dp[i-1][j-1];
                   }
               }
           }

           return dp[p.length()][s.length()];
       }
    }
}
