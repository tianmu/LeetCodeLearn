package com.leetcode.problem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 字节面试题
 */
public class ProblemX {

    public static void main(String[] args) {
        // 1,2,3,1\2,1\3,2\3,1\2\3
        System.out.println(new Solution().longestPalindrome(3));
    }

   static class Solution {
       // 创建数组
        // 依次遍历数字组合大小
       // 从前往后回溯，只能往后
       public List<List<Integer>> longestPalindrome(int n) {
            int[] arrays = new int[n];
           for (int i = 0; i < n; i++) {
               arrays[i] = i +1;
           }
           for (int i = 1; i <= n; i++) {
               longestPalindrome(arrays, 0, i, new LinkedList<>(), 0);
           }
           return list;
       }
       List<List<Integer>> list = new LinkedList<>();

       public void longestPalindrome(int[] arrays, int now, int layer, List<Integer> nowList, int start) {
          if (now == layer) {
              List<Integer> temp = new LinkedList<>(nowList);
              list.add(temp);
              return;
          }

          for (int i=start;i<arrays.length; i++) {
              nowList.add(arrays[i]);
              longestPalindrome(arrays, now+1, layer, nowList, i+1);
              nowList.remove(Integer.valueOf(arrays[i]));
          }
       }
    }
}
