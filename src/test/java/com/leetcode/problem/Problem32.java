package com.leetcode.problem;

public class Problem32 {

    public static void main(String[] args) {
        // 2
        System.out.println(new Solution().longestValidParentheses( "(()"));
        // 2
        System.out.println(new Solution().longestValidParentheses( "())"));
        // 6
        System.out.println(new Solution().longestValidParentheses( "(()())"));
    }

    static class Solution {

        // 2.dp
        // 设dp[i]为以i结尾的，有效括号长度
        // 情况1 ()结尾 那么dp[i]= dp[i-2] + 2 && s[i-1] == '(' and s[i]==')'
        // 情况2 ))结尾 dp[i] = dp[i-1] + dp[i-1-dp[i-1]-1] + 2 && s[i-1-dp[i-1]] == '(' and s[i]==')'
        //
        public int longestValidParentheses(String s) {
            // (()() )) ()()()()(
            int[] dp = new int[s.length()];
            char[] ss = s.toCharArray();
            int max = 0;
            for(int i=1;i<ss.length; i++){
                if(ss[i] != ')') {
                    continue;
                }
                if(ss[i -1] == '(') {
                    if(i-2>=0) {
                        dp[i]=dp[i-2];
                    }
                    dp[i]= dp[i] + 2;
                } else if(i-1-dp[i-1] >= 0 && ss[i-1-dp[i-1]] == '(') {
                    if(i-1-dp[i-1]-1>=0) {
                        dp[i]=dp[i-1-dp[i-1]-1];
                    }
                    dp[i] = dp[i-1] + dp[i] + 2;
                }
                if(dp[i] > max) {
                    max = dp[i];
                }
            }
            return max;
        }

        // 1.暴力破解法， 从左到右依次寻找
        public int longestValidParentheses1(String s) {
            // (()() )) ()()()()(
            int max = 0;
            char[] ss = s.toCharArray();
            for(int i=0;i<ss.length; i++){
                if(ss[i] != '(') {
                    continue;
                }
                for(int j=ss.length-1;j>i;j--) {
                    if(isOk(ss, i, j)) {
                        if(j-i+1 > max) {
                            max = j-i+1;
                        }
                        i=j;
                        break;
                    }
                }
            }
            return max;
        }

        private boolean isOk(char[] ss, int m, int n) {
            int left = 0, right = 0;
            for(int i=m;i<=n;i++) {
                if(ss[i] == '(') {
                    left++;
                } else if(ss[i] == ')') {
                    if(left<=right) {
                        return false;
                    }
                    right++;
                }
            }
            return left == right;
        }
    }
}
