package com.leetcode.problem;

import java.util.Arrays;

public class Problem31 {

    public static void main(String[] args) {
        // 2 1 3
        int[] nums = new int[]{1, 3, 2};
        new Solution().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));

        // [5,5,2,3,4,7]
        nums = new int[]{5, 4, 7, 5, 3, 2};
        new Solution().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    static class Solution {

        // 方法一： 看题解吧。。。。
        // 如果已经是逆序，全量逆序返回
        public void nextPermutation(int[] nums) {
            // 6721 -> 7126 -> 7162 -> 7216 -> 7261 -> 7612 -> 7621

            // 找到逆序的数字
            int middle = -1;
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i] > nums[i - 1]) {
                    middle = i - 1;
                    break;
                }
            }
            if (middle == -1) {
                // 已经是逆序，直接逆序即可
                for (int i = 0; i < nums.length / 2; i++) {
                    int temp = nums[i];
                    nums[i] = nums[nums.length - i - 1];
                    nums[nums.length - i - 1] = temp;
                }
                return;
            }

            // 找到右面比middle大的数字中，最小的那个
            int minVal = 200, minPostion = middle;
            for (int i = nums.length - 1; i > middle; i--) {
                if (nums[i] > nums[middle] && nums[i] < minVal) {
                    minVal = nums[i];
                    minPostion = i;
                }
            }
            {
                int temp = nums[middle];
                nums[middle] = nums[minPostion];
                nums[minPostion] = temp;
            }
            // 对 middle右侧进行排序, middle+1 到 length
            for (int i = middle + 1; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] > nums[j]) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                    }
                }
            }

        }
    }
}
