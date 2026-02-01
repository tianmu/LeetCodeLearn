package com.leetcode.problem;

public class Problem309 {

    public static void main(String[] args) {
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            // dp[i][j][k] 为第i天的最大利润 j为是否持仓 k 为是否卖出
            // 情况1 今天卖了股票
            // dp[i][0][1] = dp[i-1][1][0] + prices[i];
            // 情况2 今天买股票，注意有冷冻期
            // dp[i][1][0] = dp[i-1][1][0], d[i-1][0][0] - prices[i], dp[i-2][0][1] - prices[i];
            // 情况3 今天什么都不做
            // dp[i][0][0] = dp[i-1][0][0], dp[i][0][1];

            // 结果：dp[i][0][0],dp[i][0][1]

            if(prices.length == 1) {
                return 0;
            } else if(prices.length == 2) {
                return Math.max(prices[1]-prices[0], 0);
            }

            int[][][] dp = new int[prices.length][2][2];
            // init
            dp[0][0][0] = 0;
            dp[0][0][1] = 0;
            dp[0][1][0] = -prices[0];
            for(int i=1;i<prices.length;i++) {
                dp[i][0][1] = dp[i-1][1][0] + prices[i];
                dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][0][0] - prices[i]);
                if(i-2>0) {
                    dp[i][1][0] = Math.max(Math.max(dp[i-1][1][0], dp[i-1][0][0] - prices[i]),
                            dp[i-2][0][1] - prices[i]);
                }
                dp[i][0][0] =  Math.max(dp[i-1][0][0], dp[i-1][0][1]);
            }

            return Math.max(dp[prices.length-1][0][0], dp[prices.length-1][0][1]);
        }
    }
}