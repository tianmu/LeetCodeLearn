package com.leetcode.problem;

public class Problem714 {

    public static void main(String[] args) {
    }

    static class Solution {
        public int maxProfit(int[] prices, int fee) {
            // 设dp[i][j] 为第i天的最大利润,j为是否持仓
            // 情况1 今天卖了股票
            // dp[i][0] = dp[i-1][1] + prices[i] - fee
            // 情况2 今天不卖股票
            // dp[i][0] = dp[i-1][0]
            // dp[i][1] = dp[i-1][1]
            // 情况3 今天买股票
            // dp[i][1] = dp[i-1][0] - prices[i]

            int[][] dp = new int[prices.length][2];
            // init
            dp[0][0] = 0;
            dp[0][1] = - prices[0];
            // 计算
            for(int i=1;i<prices.length;i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
            }

            return dp[prices.length-1][0];
        }

        // 超时了  无语，因为每次都不知道持有的哪一只股票，所以这里优化看看
        public int maxProfit2(int[] prices, int fee) {
            // 设dp[i] 为第i天的最大利润
            // 情况1 今天卖了股票
            // dp[i] = MAX(dp[j] + (prices[i] - prices[j])) - fee |j取值0~i
            // 情况2 今天不卖股票
            // dp[i] = dp[i]-1

            int[] dp = new int[prices.length];
            for(int i=1;i<prices.length;i++) {
                int max = 0;
                for(int j=0;j<i;j++) {
                    if(max < dp[j] + (prices[i] - prices[j]) - fee) {
                        max = dp[j] + (prices[i] - prices[j]) - fee;
                    }
                }
                dp[i] = Math.max(dp[i-1], max);
            }

            return dp[prices.length-1];
        }
    }
}
