package com.leetcode.problem;

public class Problem11 {

    public static void main(String[] args) {
    }

   static class Solution {
       // 方法2
       // max((j-i) * min(height[i], height[j]))
       // 每次移动短的一端
       public int maxArea(int[] height) {
           int max = 0;
           for(int i = 0,j=height.length-1; i!=j; ) {
               int now = (j-i) * Math.min(height[i], height[j]);
               if(now > max) {
                   max = now;
               }
               if(height[i] >= height[j]) {
                   j--;
               } else {
                   i++;
               }
           }

           return max;
       }

       // 方法1 暴力求解
       public int maxArea1(int[] height) {
           int max = 0;
           for(int i = 0; i<height.length-1; i++) {
               for(int j=i+1; j<height.length; j++) {
                   int now = (j-i) * Math.min(height[i], height[j]);
                   if(now > max) {
                       max = now;
                   }
               }
           }

           return max;
       }
    }
}
