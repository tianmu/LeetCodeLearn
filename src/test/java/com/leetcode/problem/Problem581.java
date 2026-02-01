package com.leetcode.problem;

public class Problem581 {

    public static void main(String[] args) {
    }


    class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int start = findStart(nums);
            //System.out.println(start);
            int end = findEnd(nums);
            if(start == -1 || end == -1) {
                return 0;
            }

            return end-start+1;
        }


        private int findStart(int[] nums) {
            // 找出不连续的数，然后查找是否之前有比它还小的数值
            int min = -10000000;
            for(int i=0;i<nums.length;i++) {
                if(min ==  -10000000 && i+1 < nums.length && nums[i]> nums[i+1]) {
                    min = nums[i+1];
                } else if(min > nums[i]) {
                    min = nums[i];
                }
            }
            //System.out.println(min);
            if(min ==  -10000000) {
                return -1;
            }

            for(int i=0;i<nums.length;i++) {
                if(nums[i]> min) {
                    return i;
                }
            }
            return -1;
        }

        private int findEnd(int[] nums) {
            // 找出不连续的数，然后查找是否之前有比它还大的数值
            int max = 10000000;
            for(int i=nums.length-1;i>=0;i--) {
                if(max ==  10000000 && i-1 >= 0 && nums[i-1]> nums[i]) {
                    max = nums[i-1];
                } else if(max < nums[i]) {
                    max = nums[i];
                }
            }

            if(max ==  10000000) {
                return -1;
            }

            for(int i=nums.length-1;i>=0;i--) {
                if(nums[i]< max) {
                    return i;
                }
            }
            return -1;
        }
    }
}
