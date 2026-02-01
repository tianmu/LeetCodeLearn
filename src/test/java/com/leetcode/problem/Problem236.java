package com.leetcode.problem;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class Problem236 {

    public static void main(String[] args) {
        // true
//        System.out.println(new Solution().lowestCommonAncestor(TreeNode.genTreeNode(new Integer[]{3,5,1,6,2,0,8,null,null,7,4}),
//                new TreeNode(5), new TreeNode(1)));
        System.out.println(new Solution().lowestCommonAncestor(TreeNode.genTreeNode(new Integer[]{3,5,1,6,2,0,8,null,null,7,4}),
                new TreeNode(5), new TreeNode(4)));

    }

    static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 笨办法，暴力求解法，大量重复计算
            List<TreeNode> iter = new LinkedList<TreeNode>();
            iter.add(root);
            TreeNode ancestor = root;
            while(iter.size()>0) {
                List<TreeNode> newIter = new LinkedList<TreeNode>();
                for(TreeNode node : iter) {
                    if(lowestCommonAncestor2(node, p, q)) {
                        System.out.println(node.val);
                        ancestor = node;
                        if(node.left != null) {
                            newIter.add(node.left);
                        }
                        if(node.right != null) {
                            newIter.add(node.right);
                        }
                        break;
                    }
                }
                iter = newIter;
            }
            return ancestor;
        }

        public boolean lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
            boolean isLeft = false;
            boolean isRight = false;
            List<TreeNode> iter = new LinkedList<TreeNode>();
            iter.add(root);
            while(iter.size()>0) {
                if(isLeft && isRight) {
                    return true;
                }
                List<TreeNode> newIter = new LinkedList<TreeNode>();
                for(TreeNode node : iter) {
                    if(node.val == p.val) {
                        isLeft = true;
                    }
                    if(node.val == q.val) {
                        isRight = true;
                    }
                    if(node.left != null) {
                        newIter.add(node.left);
                    }
                    if(node.right != null) {
                        newIter.add(node.right);
                    }
                }
                iter = newIter;
            }

            return isLeft && isRight;
        }
    }
}
