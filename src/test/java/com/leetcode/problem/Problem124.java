package com.leetcode.problem;

import com.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Problem124 {

    public static void main(String[] args) {
        // [3,9,20,15,7], inorder = [9,3,15,20,7]
        System.out.println(new Solution().maxPathSum(null));
    }

    static class Solution {
        public int maxPathSum(TreeNode root) {
            // 递归
            // 情况1，这里是路径的根节点，路径是从左到右的（左右可能为0也就是不走，比如负数的时候）。
            // 其最大路径和为  root.value + maxPathSum(left) + maxPathSum(right)
            // 情况2，这里不是路径的根节点， 路径只能走左面或者右面。注意，也可以都不走，比如负数的时候
            // 其最大路径和为  max{0,root.value + max{0, maxPathSum(left), maxPathSum(right)}}
            // 注意，任何一个节点都可能为路径的根节点。
            return 0;
        }
    }
}
