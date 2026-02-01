package com.leetcode.problem;

public class Problem416 {

    public static void main(String[] args) {
        // true
        System.out.println(new Solution().canPartition(new int[]{1,5,11,5}));
        // false
        System.out.println(new Solution().canPartition(new int[]{1,2,3,5}));
        // true
        System.out.println(new Solution().canPartition(new int[]{100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100}));
    }

    static class Solution {
        public boolean canPartition(int[] nums) {
            // 设dp[i][j] 为前i个数，正好和等于j的个数
            // dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i]]
            // dp[0][0] = 1
            // 结果就是 dp[nums.length][sum(nums)/2] > 0

            int sum = 0;
            for(int num:nums) {
                sum += num;
            }
            if (sum%2!=0) {
                return false;
            }
            sum = sum/2;

            int[][] dp = new int[nums.length+1][sum+1];
            dp[0][0] = 1;
            for(int i=1;i<=nums.length;i++) {
                for(int j = 0;j<=sum;j++) {
                    if (j-nums[i-1] >= 0) {
                        dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }

            return dp[nums.length-1][sum] != 0;
        }
    }
}
