package com.leetcode.problem;

public class Problem122 {

    public static void main(String[] args) {
    }

    static class Solution {
        // 感觉有贪心算法、DP算法两种算法
        public int maxProfit(int[] prices) {
            return dp(prices);
        }

        //    7 1 3 5 6 4
        //dp  0 0 2 4 5 5
        public int dp(int[] prices) {
            // 设dp[i] 为第i天的最大利润
            // 情况1 今天卖了股票
            // dp[i] = MAX(dp[j] + (prices[i] - prices[j])) |j取值0~i
            // 情况2 今天不卖股票
            // dp[i] = dp[i]-1

            int[] dp = new int[prices.length];
            for(int i=1;i<prices.length;i++) {
                int max = 0;
                for(int j=0;j<i;j++) {
                    if(max < dp[j] + (prices[i] - prices[j])) {
                        max = dp[j] + (prices[i] - prices[j]);
                    }
                }
                dp[i] = Math.max(dp[i-1], max);
            }

            return dp[prices.length-1];
        }
    }
}
