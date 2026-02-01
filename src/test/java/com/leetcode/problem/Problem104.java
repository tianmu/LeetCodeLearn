package com.leetcode.problem;

import com.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem104 {

    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().maxDepth(TreeNode.genTreeNode(new Integer[]{3,9,20,null,null,15,7})));
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null) {
                return 0;
            }

            return Math.max(1+maxDepth(root.left), 1+maxDepth(root.right));
        }
    }
}
