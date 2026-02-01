package com.leetcode.problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem494 {

    public static void main(String[] args) {
    }

    class Solution {

        public int findTargetSumWays(int[] nums, int target) {
            count = 0;
            findTargetSumWays(nums, target, 0, 0);

            return count;
        }

        /**
         * 回溯法
         */
        public void findTargetSumWays(int[] nums, int target, int index, int sum) {
            if(index == nums.length) {
                if(target == sum) {
                    count ++;
                }
                return;
            }
            findTargetSumWays(nums, target, index + 1,  sum + nums[index]);
            findTargetSumWays(nums, target, index + 1,  sum - nums[index]);
        }

        /**
         * 遍历数组，每次将和计算存储下来，直到最后得到结果
         * 超时了
         */
        public int findTargetSumWays2(int[] nums, int target) {

            List<Integer> sums = new ArrayList<>();
            sums.add(0);
            for(int i=0;i<nums.length;i++) {
                List<Integer> temp = new ArrayList<>(sums.size()*2);
                for(Integer sum : sums) {
                    temp.add(sum+nums[i]);
                    temp.add(sum-nums[i]);
                }
                sums = temp;
            }

            int count = 0;
            for(Integer sum : sums) {
                if(sum == target) {
                    count ++;
                }
            }

            return count;
        }


        private static int count = 0;
        private class Node {
            Node left;
            Node right;
            int val;
            int flag;
        }

        /**
         * 依托二叉树，遍历所有结果
         * 超时了
         * @param nums
         * @param target
         * @return
         */
        public int findTargetSumWays1(int[] nums, int target) {
            count = 0;
            Node root = new Node();
            root.val = 0;

            List<Node> list = new LinkedList();
            list.add(root);
            for(int i=0;i<nums.length;i++) {
                List<Node> list2 = new LinkedList();
                for(Node now : list) {
                    Node left = new Node();
                    left.val = nums[i];
                    left.flag = 0;
                    now.left = left;
                    list2.add(left);
                    Node right = new Node();
                    right.val=nums[i];
                    right.flag = 1;
                    now.right = right;
                    list2.add(right);
                }
                list = list2;
            }

            findTargetSumWays(root, 0, target);

            return count;
        }

        public void findTargetSumWays(Node node, int sum, int target) {
            if(node.flag == 0) {
                sum += node.val;
            } else {
                sum -= node.val;
            }
            if(node.left == null && node.right == null) {
                if(sum == target) {
                    count ++;
                }
                return ;
            }

            findTargetSumWays(node.left, sum, target);
            findTargetSumWays(node.right,sum, target);
        }
    }
}
