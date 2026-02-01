package com.leetcode.problem;

public class Problem123 {

    public static void main(String[] args) {
        // 0
        System.out.println(new Solution().maxProfit(new int[]{7,6,4,3,1}));
        // 4
        System.out.println(new Solution().maxProfit(new int[]{1,2,3,4,5}));
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            // 设dp[i][j][k] 为第i天的最大利润[天数][是否持有股票][卖出次数]
            // 那么就有以下这些情况
            // 今天买股票
            // dp[i][1][0] = dp[i-1][0][0] - prices[i];
            // dp[i][1][1] = dp[i-1][0][1] - prices[i];
            // 今天卖股票
            // dp[i][0][1] = dp[i-1][1][0] + prices[i];
            // dp[i][0][2] = dp[i-1][1][1] + prices[i];
            // 啥也不干，值和昨天一样
            // dp[i][1][0] = dp[i-1][1][0]
            // dp[i][1][1] = dp[i-1][1][1]
            // dp[i][0][1] = dp[i-1][0][1]
            // dp[i][0][2] = dp[i-1][0][2]


            int[][][] dp = new int[prices.length][2][3];
            dp[0][1][0] = - prices[0];
            dp[0][1][1] = -1111111111;
            dp[0][0][1] = -1111111111;
            dp[0][0][2] = -1111111111;
            for(int i=1;i<prices.length;i++) {
                 dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][0][0] - prices[i]);
                 dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][1] - prices[i]);
                 dp[i][0][1] = Math.max(dp[i-1][0][1], dp[i-1][1][0] + prices[i]);
                 dp[i][0][2] = Math.max(dp[i-1][0][2], dp[i-1][1][1] + prices[i]);
            }

            return Math.max(Math.max(dp[prices.length-1][0][1], dp[prices.length-1][0][2]), 0);
        }

        // 超时了，i次交易不太行。。。对2次交易做特殊处理
        //    7 1 3 5 6 4 7
        //dp  0 0 2 4 5 5 8
        public int maxProfit2(int[] prices) {
            // 设dp[i][j] 为交易i次，第j天的最大利润
            // 两种情况，1 今天卖出股票了
            // dp[i][j] = max{dp[i-1][k] + (prices[j] - prices[k])} | k取值 0 ~j
            // 2 今天没卖出
            // dp[i][j] = max{dp[i][j-1],dp[i-1][j]}
            int[][] dp = new int[3][prices.length];
            for(int i=1;i<3;i++) {
                for(int j=1;j<prices.length;j++) {
                    int max = 0;
                    for(int k=0;k<j;k++) {
                        if(max < dp[i-1][k] + (prices[j] - prices[k])) {
                            max = dp[i-1][k] + (prices[j] - prices[k]);
                        }
                    }
                    dp[i][j] = Math.max(Math.max(dp[i][j-1], max), dp[i-1][j]);
                }
            }

            return dp[2][prices.length-1];
        }
    }
}
