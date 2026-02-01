package com.leetcode.problem;

import java.util.ArrayList;
import java.util.List;

public class Problem53 {

    public static void main(String[] args) {
    }

    static class Solution {
        public int maxSubArray(int[] nums) {
            return dp3(nums);
        }

        private int dp3(int[] nums) {
            // dp[i]为结尾为i的最大和
            // dp[i] = dp[i-1] > 0? dp[i-1] + nums[i]:nums[i];
            int[] dp = new int[nums.length+1];
            dp[0] = 0;
            int maxSum = -1000000;
            for(int i=1;i<=nums.length;i++) {
                dp[i] = dp[i-1] > 0? dp[i-1] + nums[i-1]:nums[i-1];
                if(dp[i] > maxSum) {
                    maxSum = dp[i];
                }
            }

            return maxSum;
        }

        private int dp2(int[] nums) {
            // dp[i]为从0到i的最大和
            // 子数组和[i][j] = dp[j] - dp[i]
            int[] dp = new int[nums.length];
            int sum = 0;
            int maxSum = -1000000;
            for(int i=0;i<nums.length;i++) {
                sum += nums[i];
                dp[i] = sum;
                if(sum > maxSum) {
                    maxSum = sum;
                }
            }
            for(int i=1;i<nums.length;i++) {
                for(int j=i;j<nums.length;j++) {
                    int temp = dp[j] - dp[i-1];
                    if(temp > maxSum) {
                        maxSum = temp;
                    }
                }
            }
            return maxSum;
        }

        private int dp1(int[] nums) {
            // dp[i][j]为从i到j的最大和
            // dp[i][j] = dp[0][j] - dp[0][i]
            int[][] dp = new int[nums.length][nums.length];
            int sum = 0;
            int maxSum = -1000000;
            for(int i=0;i<nums.length;i++) {
                sum += nums[i];
                dp[0][i] = sum;
                if(sum > maxSum) {
                    maxSum = sum;
                }
            }
            for(int i=1;i<nums.length;i++) {
                for(int j=i;j<nums.length;j++) {
                    dp[i][j] = dp[0][j] - dp[0][i-1];
                    if(dp[i][j] > maxSum) {
                        maxSum = dp[i][j];
                    }
                }
            }
            return maxSum;
        }
    }
}
