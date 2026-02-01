package com.leetcode.problem;

public class Problem33 {

    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8));
        System.out.println(new Solution().search(new int[]{1}, 1));
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, -1));
    }

    static class Solution {

        public int search(int[] nums, int target) {
            // 二分法，选择一个点之后，两侧总有一侧是有序的
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int checkPosition = (end + start) / 2;
                if (nums[checkPosition] == target) {
                    return checkPosition;
                }
                // 先找到有序的地方
                if (nums[checkPosition] < nums[end] && target>nums[checkPosition] && target <= nums[end]) {
                    // 后半段二分
                    start = checkPosition + 1;
                } else if (nums[start] < nums[checkPosition] && target < nums[checkPosition] && target >= nums[start]) {
                    // 前半段二分
                    end = checkPosition - 1;
                } else if (nums[checkPosition] <= nums[end]) {
                    // 前半段二分
                    end = checkPosition - 1;
                } else if ( nums[start] <= nums[checkPosition]) {
                    // 后半段二分
                    start = checkPosition + 1;
                }
            }
            return -1;
        }
    }
}
