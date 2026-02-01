package com.leetcode.problem;

import java.util.HashMap;
import java.util.Map;

public class Problem70 {

    public static void main(String[] args) {
        // BANC
//        System.out.println(new Problem70.Solution().minWindow("ADOBECODEBANC", "ABC"));
    }

    static class Solution {
        public int climbStairs(int n) {
            if (n == 1) {
                return 1;
            }
            if (n <= 0) {
                return 0;
            }
            // 每次只可以1步或者2步， 那么爬到楼顶的方式就是要么上1步要么上2步。
            // 设dp[n]为到达n所拥有的方式数量，那么
            // dp[n] = dp[n-1] + dp[n-2];
            int[] dp = new int[n+1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for (int i=3;i<=n;i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n];
        }
    }
}
