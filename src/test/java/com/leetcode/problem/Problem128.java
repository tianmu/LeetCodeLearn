package com.leetcode.problem;

import java.util.HashSet;
import java.util.Set;

public class Problem128 {

    public static void main(String[] args) {
        // 6
        System.out.println(new Solution().longestConsecutive(new int[]{3,5,6,2,5,4,19,5,6,7,12}));
    }

    static class Solution {
        public int longestConsecutive(int[] nums) {
            return hash(nums);
        }


        // 设数组内所有值的hash，然后依次遍历找最长的
        private int hash(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }

            int maxLongest = 0;
            for (int num : nums) {
                if (set.contains(num-1)) {
                    continue;
                }
                int tempNum = num;
                int longest = 0;
                while (set.contains(tempNum++)) {
                    longest ++;
                }
                if (longest > maxLongest) {
                    maxLongest = longest;
                }
            }

            return maxLongest;
        }
    }
}
