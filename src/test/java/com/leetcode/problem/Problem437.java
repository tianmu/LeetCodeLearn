package com.leetcode.problem;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem437 {

    public static void main(String[] args) {
        Integer[] values = new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode root = new TreeNode();
        Queue<TreeNode> roots = new LinkedList<>();
        roots.add(root);
        for (int i = 0; i < values.length; i++) {
            TreeNode poll = roots.poll();
            if (values[i] == null) {
                continue;
            }

            poll.val = values[i];
            poll.left = new TreeNode();
            roots.add(poll.left);
            poll.right = new TreeNode();
            roots.add(poll.right);
        }

        System.out.println(new Problem437.Solution().pathSum(root, 22));
    }


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    static class Solution {
        // root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
        // root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        public int pathSum(TreeNode root, int targetSum) {
            int result = 0;
            Queue<TreeNode> roots = new LinkedList<>();
            roots.add(root);
            while (!roots.isEmpty()) {
                TreeNode treeNode = roots.poll();
                if (treeNode == null) {
                    continue;
                }
                result += pathSubSum(treeNode, 0, targetSum);
                if (treeNode.left != null) {
                    roots.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    roots.add(treeNode.right);
                }
            }

            return result;
        }

        public int pathSubSum(TreeNode root, int nowSum, int targetSum) {
            if (root == null || root.val == null) {
                return 0;
            }
            int result = 0;
            nowSum += root.val;
            if (nowSum == targetSum) {
                result = 1;
            }
            if (root.left != null) {
                result += pathSubSum(root.left, nowSum, targetSum);
            }
            if (root.right != null) {
                result += pathSubSum(root.right, nowSum, targetSum);
            }
            return result;
        }
    }
}
