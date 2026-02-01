package com.leetcode.problem;

public class Problem221 {

    public static void main(String[] args) {
//        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
    }

    static class Solution {

        public int maximalSquare(char[][] matrix) {
            // 设dp[i][j] 为以此为右下角的最大正方形，那么
            /*
            1 1 1     0 1 1
            1 1 1     1 1 1
            1 1 1     1 1 1

            1 1 1     0 1 1
            1 2 2     1 1 2
            1 2 3     1 2 2
            */
            // dp[i][j] = if m[i][j] = 1 then min{dp[i-1][j],dp[i][j-1], dp[i-1][j-1]} + 1
            int maxSquare = 0;
            int dp[][] = new int[matrix.length + 1][310];
            for (int i = 1; i <= matrix.length; i++) {
                for (int j = 1; j <= matrix[i - 1].length; j++) {
                    if (matrix[i - 1][j - 1] == '1') {
                        int firstmin = dp[i - 1][j] > dp[i][j - 1] ? dp[i][j - 1] : dp[i - 1][j];
                        int min = firstmin > dp[i - 1][j - 1] ? dp[i - 1][j - 1] : firstmin;
                        dp[i][j] = min + 1;
                        if (dp[i][j] > maxSquare) {
                            maxSquare = dp[i][j];
                        }
                    }
                }
            }

            return maxSquare * maxSquare;
        }
    }
}
