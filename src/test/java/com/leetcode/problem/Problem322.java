package com.leetcode.problem;

public class Problem322 {

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange(new int[]{1, 2, 5}, 100));
        System.out.println(new Solution().coinChange(new int[]{186,419,83,408}, 6249));
    }

    static class Solution {
        public int coinChange(int[] coins, int amount) {
//            int[] dp = new int[amount + 1];
//            return dp(coins, amount, dp);
            return dp2(coins, amount);
        }

        public int dp2(int[] coins, int amount) {
            // 设dp[i] = 总额=i的最少硬币数量
            // dp[i] =  min (dp[i - 0~n Cn]) + 1
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int i = 1; i <= amount; i++) {
                int min = -1;
                for (int coin : coins) {
                    if (i-coin < 0) {
                        continue;
                    }
                    if (dp[i-coin] != 0 && dp[i-coin] != -1 && (dp[i-coin] < min || min == -1)) {
                        min = dp[i-coin] + 1;
                    }
                }
                dp[i] = min;
            }
            if (dp[amount] != -1) {
                dp[amount]--;
            }
            return dp[amount];
        }

        public int dp(int[] coins, int amount, int[] dp) {
            // 设dp[i] = 总额=i的最少硬币数量
            // dp[i] =  min (dp[i - 0~n Cn]) + 1
            if (dp[amount] != 0) {
                return dp[amount];
            }

            if (amount <= 0) {
                return 0;
            }

            int min = -1;
            for (int i = 0; i < coins.length; i++) {
                if (amount - coins[i] < 0) {
                    continue;
                }
                int temp = dp(coins, amount - coins[i], dp);
                if (temp != -1 && (temp < min || min == -1)) {
                    min = temp + 1;
                    dp[amount] = min;
                }
            }

            return min;
        }
    }
}
