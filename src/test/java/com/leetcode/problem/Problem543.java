package com.leetcode.problem;

import com.leetcode.common.TreeNode;

public class Problem543 {

    public static void main(String[] args) {
        System.out.println(new Solution().diameterOfBinaryTree(TreeNode.genTreeNode(new Integer[]{1, 2, 3, 4, 5})));
        System.out.println(new Solution().diameterOfBinaryTree(TreeNode.genTreeNode(new Integer[]{1, 2})));
    }

    static class Solution {
        /**
         * 根部遍历节点
         * 节点路径长度为左孩子高度+右孩子高度
         */
        public int diameterOfBinaryTree(TreeNode root) {
            return rootIter(root, 0);
        }

        private int rootIter(TreeNode root, int max) {
            if (root == null) {
                return max;
            }
            int meLength = deepLength(root);
            if (max < meLength) {
                max = meLength;
            }
            int rightLength = rootIter(root.right, max);
            int leftLength = rootIter(root.left, max);
            if (max < rightLength) {
                max = rightLength;
            }
            if (max < leftLength) {
                max = leftLength;
            }
            return max;
        }

        private int deepLength(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // 路径长度是我的左子树长度+右子树长度
            return treeDeepLength(root.left) + treeDeepLength(root.right);
        }

        private int treeDeepLength(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = treeDeepLength(root.left);
            int right = treeDeepLength(root.right);
            return 1+ Math.max(left, right);
        }
    }
}
