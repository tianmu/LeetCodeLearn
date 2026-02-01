package com.leetcode.problem;

public class Problem300 {

    public static void main(String[] args) {
        // 6
        System.out.println(new Solution().lengthOfLIS(new int[]{3,5,6,2,5,4,19,5,6,7,12}));
        // 4
        System.out.println(new Solution().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }

    static class Solution {
        public int lengthOfLIS(int[] nums) {
//            return dfs(nums);
            return dp2(nums);
        }


        // 设dp[i]是以j结尾的最长
        // dp[i] = max{dp[k 0~i] && s[k] < s[j]} + 1
        private int dp2(int[] nums) {
            int maxDepth = 1;
            int dp[] = new int[nums.length];
            for(int i=0;i<nums.length;i++) {
                dp[i] = 1;
            }
            for(int i=0;i<nums.length;i++) {
                int maxLength = 0;
                for(int j=0;j<=i-1;j++) {
                    if(nums[j] < nums[i] && dp[j] > maxLength) {
                        maxLength = dp[j];
                    }
                }
                dp[i] = maxLength + 1;
                if(dp[i] > maxDepth) {
                    maxDepth = dp[i];
                }

            }
            return maxDepth;
        }

        // 设dp[i][j]是以i 开头，以j结尾的最长
        // dp[i][j] = max{dp[i][k i~j-1] && s[k] < s[j]} + 1
        private int dp(int[] nums) {
            int maxDepth = 1;
            int dp[][] = new int[nums.length][nums.length];
            for(int i=0;i<nums.length;i++) {
                dp[i][i] = 1;
            }
            for(int i=0;i<nums.length;i++) {
                for(int j=i+1;j<nums.length;j++) {
                    if (nums[i] >= nums[j]) {
                        continue;
                    }
                    int maxLength = 1;
                    for(int k=i;k<=j-1;k++) {
                        if(nums[k] < nums[j] && dp[i][k] > maxLength) {
                            maxLength = dp[i][k];
                        }
                    }
                    dp[i][j] = maxLength + 1;
                    if(dp[i][j] > maxDepth) {
                        maxDepth = dp[i][j];
                    }
                }
            }
            return maxDepth;
        }

        // dfs
        private int dfs(int[] nums) {
            int maxDepth = 0;
            int xxx = Integer.MAX_VALUE;
            for(int i=0;i<nums.length;i++) {
                if (nums[i] > xxx) {
                    continue;
                }
                xxx = nums[i];
                int tempMaxDepth = dfs(nums, i, i, 1);
                if(tempMaxDepth > maxDepth) {
                    maxDepth = tempMaxDepth;
                }
            }
            return maxDepth;
        }

        private int dfs(int[] nums, int last, int layer, int depth) {
            if(layer >= nums.length) {
                return depth;
            }

            int maxDepth = 0;
            for(int i=layer;i<nums.length;i++) {
                if(layer > 0 && nums[last] < nums[layer]) {
                    int tempMaxDepth = dfs(nums, layer, layer+1, depth+1);
                    if(tempMaxDepth > maxDepth) {
                        maxDepth = tempMaxDepth;
                    }
                }

                int tempMaxDepth = dfs(nums, last, layer+1, depth);
                if(tempMaxDepth > maxDepth) {
                    maxDepth = tempMaxDepth;
                }
            }

            return maxDepth;
        }
    }
}
