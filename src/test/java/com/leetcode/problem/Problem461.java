package com.leetcode.problem;

public class Problem461 {

    public static void main(String[] args) {
        System.out.println(new Solution().hammingDistance(3,1));;
    }

    static class Solution {
        public int hammingDistance(int x, int y) {
            int hammingDistance = 0;
            String xInteger = Integer.toBinaryString(x);
            String yInteger = Integer.toBinaryString(y);
            System.out.println(xInteger);
            System.out.println(yInteger);
            for(int i=0;i<=32 ;i++) {
                if(xInteger.length() > i && yInteger.length() > i) {
                    if(xInteger.charAt(xInteger.length()-i-1) != yInteger.charAt(yInteger.length()-i-1)) {
                        hammingDistance++;
                    }
                } else if(xInteger.length() > i && xInteger.charAt(xInteger.length()-i-1) == '1'){
                    hammingDistance++;
                }  else if(yInteger.length() > i && yInteger.charAt(yInteger.length()-i-1) == '1'){
                    hammingDistance++;
                }
            }
            return hammingDistance;
        }
    }
}
