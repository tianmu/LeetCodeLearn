package com.leetcode.problem;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Problem42 {

    public static void main(String[] args) {
        // true
//        System.out.println(new Solution().lowestCommonAncestor(TreeNode.genTreeNode(new Integer[]{3,5,1,6,2,0,8,null,null,7,4}),
//                new TreeNode(5), new TreeNode(1)));
        System.out.println(new Solution().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new Solution().trap(new int[]{4,2,0,3,2,5}));
        System.out.println(new Solution().trap(new int[]{4,2,3}));
    }

    static class Solution {
        public int trap(int[] height) {
            // 无脑往后走，遇到比我们高的就停。 遇不到就高度下降1再重试重试
            int rain = 0;
            int start = 0;
            int tempRain = 0;
            for(int i=1;i<height.length;i++) {
                if(height[i] < height[start]) {
                    tempRain += height[start] - height[i];
                    if(i == height.length - 1) {
                        // 走到最后还没有遇到比它高的
                        i = start;
                        tempRain = 0;
                        height[start] -=1;
                    }
                } else {
                    start = i;
                    rain += tempRain;
                    tempRain = 0;
                }
            }
            return rain;
        }
    }
}
