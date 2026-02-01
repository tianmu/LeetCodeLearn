package com.leetcode.problem;

/**
 *
 */
public class Problem1071 {

    public static void main(String[] args) {
        // 1,2,3,1\2,1\3,2\3,1\2\3
        System.out.println(new Solution().gcdOfStrings("ABCABC", "ABC"));
        System.out.println(new Solution().gcdOfStrings("ABABABAB", "ABAB"));
    }

    static class Solution {
        public String gcdOfStrings(String str1, String str2) {
            String mineStr = str1.length() < str2.length()? str1:str2;

            for(int i=mineStr.length(); i> 0; i--) {
                if(isDivide(str1, mineStr.substring(0, i)) && isDivide(str2, mineStr.substring(0, i))) {
                    return mineStr.substring(0, i);
                }
            }

            return "";
        }

        public boolean isDivide(String mainStr, String subStr) {
            if(mainStr.length() % subStr.length() != 0) {
                return false;
            }

            StringBuilder tempStr = new StringBuilder(subStr);
            while(tempStr.length() < mainStr.length()) {
                tempStr.append(subStr);
            }
            return mainStr.equals(tempStr.toString());
        }
    }
}
