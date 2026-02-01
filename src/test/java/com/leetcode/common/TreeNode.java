package com.leetcode.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static TreeNode genTreeNodeNew(Integer[] values) {
        Queue<TreeNode> nodes = new LinkedList<>();
        TreeNode root = new TreeNode(values[0]);
        TreeNode iter = root;
        for (int i = 1; i < values.length; i++) {
            if (iter == null) {
                throw new IllegalArgumentException("TreeNode is null is impossible");
            }
            if (values[i] != null) {
                if (i % 2 != 0) {
                    iter.left = new TreeNode(values[i]);
                    nodes.offer(iter.left);
                } else {
                    iter.right = new TreeNode(values[i]);
                    nodes.offer(iter.right);
                    iter = nodes.poll();
                }
            }
        }

        return root;
    }

    /**
     * 按数组生成一棵树
     *
     * @param values
     * @return
     */
    public static TreeNode genTreeNode(Integer[] values) {
        List<TreeNode> allNodes = new LinkedList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        TreeNode root = new TreeNode();
        nodes.add(root);
        allNodes.add(root);
        for (Integer value : values) {
            if (value == null) {
                continue;
            }
            TreeNode treeNode = nodes.poll();
            treeNode.val = value;
            treeNode.left = new TreeNode();
            treeNode.right = new TreeNode();
            nodes.add(treeNode.left);
            nodes.add(treeNode.right);
            allNodes.add(treeNode.left);
            allNodes.add(treeNode.right);
        }

        for (TreeNode node : allNodes) {
            if (node.right != null && node.right.val == null) {
                node.right = null;
            }

            if (node.left != null && node.left.val == null) {
                node.left = null;
            }
        }

        return root;
    }
}
