package com.leetcode.problem;

import com.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Problem105 {

    public static void main(String[] args) {
        // [3,9,20,15,7], inorder = [9,3,15,20,7]
        System.out.println(new Solution().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
        System.out.println(new Solution().buildTree(new int[]{1, 2}, new int[]{2, 1}));
    }

    static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            List<Integer> values = new LinkedList<>();
            Map<Integer, Integer> preorderMap = new HashMap<>();
            for (int i = 0; i < preorder.length; i++) {
                preorderMap.put(preorder[i], i);
            }
            return buildTree2(preorder, inorder, preorderMap, 0, inorder.length - 1);
        }

        /**
         * 前序遍历:[根,左,右]
         * 中序遍历:[左,根,右]
         * 每次找到根节点进行创建，然后用递归分成两个子树各自构建去。
         **/
        public TreeNode buildTree2(int[] preorder, int[] inorder, Map<Integer, Integer> preorderMap, int left,
                                   int right) {
            if (left > right) {
                return null;
            }
            if (left == right) {
                TreeNode root = new TreeNode();
                root.val = inorder[left];
                return root;
            }
            // 找到根节点
            int minPosition = 10000;
            int mid = -1;
            for (int i = left; i <= right; i++) {
                int position = preorderMap.get(inorder[i]);
                if (position < minPosition) {
                    minPosition = position;
                    mid = i;
                }
            }

            TreeNode root = new TreeNode();
            root.val = inorder[mid];
            root.left = buildTree2(preorder, inorder, preorderMap, left, mid - 1);
            root.right = buildTree2(preorder, inorder, preorderMap, mid + 1, right);
            return root;
        }
    }
}
