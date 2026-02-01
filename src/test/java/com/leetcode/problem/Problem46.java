package com.leetcode.problem;

import java.util.ArrayList;
import java.util.List;

public class Problem46 {

    public static void main(String[] args) {
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            dfs(nums);
            return finalList;
        }

        private List<List<Integer>> finalList = new ArrayList<>();
        private void dfs(int[] nums) {
            dfs(nums, 0, new ArrayList<>());
        }

        private void dfs(int[] nums, int depth, List<Integer> list) {
            if(depth >= nums.length) {
                List<Integer> newlist = new ArrayList<>();
                newlist.addAll(list);
                finalList.add(newlist);
                return;
            }

            for(int i=0;i<nums.length;i++) {
                if(list.contains(nums[i])) {
                    continue;
                }
                list.add(nums[i]);
                dfs(nums, depth + 1, list);
                list.remove((Integer)nums[i]);
            }
        }
    }
}
