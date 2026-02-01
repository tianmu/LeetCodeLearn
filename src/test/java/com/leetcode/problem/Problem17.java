package com.leetcode.problem;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 */
public class Problem17 {

    public static void main(String[] args) {
        //System.out.println(new com.leetcode.problem.Problem17.Solution().letterCombinations("23"));
        System.out.println(new Problem17.Solution().letterCombinations(""));
        System.out.println(new Problem17.Solution().letterCombinations("2"));
    }

    static class Solution {
        List<String> resultList = new LinkedList<>();
        char[][] phoneKey = new char[][]{{},{},{'a','b','c'},{'d','e','f'},
            {'g','h','i'}, {'j','k','l'}, {'m', 'n', 'o'}, {'p','q','r','s'},
                {'t','u','v'},{'w','x','y','z'}};
        public List<String> letterCombinations(String digits) {
            aaa(new StringBuilder(), digits, 0);
            return resultList;
        }

        public void aaa(StringBuilder result, String digits, int now){
            if (now >= digits.length()) {
                if (result.length() > 0) {
                    resultList.add(result.toString());
                    result.deleteCharAt(result.length() -1);
                }
                return;
            }

            for (char key : phoneKey[Integer.parseInt(String.valueOf(digits.charAt(now)))]) {
                result.append(key);
                aaa(result, digits, now+1);
            }
            if (result.length() > 0) {
                result.deleteCharAt(result.length() -1);
            }
        }
    }

}
