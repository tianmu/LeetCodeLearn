package com.leetcode.problem;

public class Problem560 {

    public static void main(String[] args) {
        // 1
        System.out.println(new Solution().subarraySum(new int[]{-1,-1,1},0));
        // 1
        System.out.println(new Solution().subarraySum(new int[]{1,1,1},3));
        // 2
        System.out.println(new Solution().subarraySum(new int[]{1,1,1},2));
        // 2
        System.out.println(new Solution().subarraySum(new int[]{1,2,3},3));
    }

    static class Solution {
        int all = 0;
        public int subarraySum(int[] nums, int k) {
            for(int i=0;i<nums.length;i++) {
                int alreadyNum = 0;
                for(int j=i;j<nums.length;j++) {
                    alreadyNum += nums[j];
                    if(alreadyNum == k) {
                        all++;
                    }
                }
            }
            return all;
        }

        /**
         * 不是连续的子数组
         */
        private int iterNums(int[] nums, int k, int start, int alreadyNum) {
            for(int i=start;i<nums.length;i++) {
                alreadyNum += nums[i];
                if(alreadyNum > k) {
                    //System.out.println(alreadyNum);
                    return 0;
                }
                if(alreadyNum == k) {
                    all++;
                    return 1;
                }
                iterNums(nums, k, ++i, alreadyNum);
            }
            return 0;
        }
    }
}
