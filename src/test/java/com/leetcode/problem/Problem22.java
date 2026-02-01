package com.leetcode.problem;

import java.util.LinkedList;
import java.util.List;

public class Problem22 {

    public static void main(String[] args) {
        // bab aba
//        System.out.println(new Solution().longestPalindrome("babad"));
        // bb
        System.out.println(new Solution().generateParenthesis(3));
        // a
        //System.out.println(new Solution().longestPalindrome("a"));
        // bb
        //System.out.println(new Solution().longestPalindrome("bb"));
        // aca
        // System.out.println(new Solution().longestPalindrome("aacabdkacaa"));
    }

    static class Solution {

        // 方法1 暴力破解， 当作一棵树，根须遍历。每一层都是()
        private List<String> allParent = new LinkedList();
        public List<String> generateParenthesis(int n) {
            generateParenthesisTree("(", 1, n * 2);
            return allParent;
        }

        public void generateParenthesisTree(String pa, int layer, int n) {
            if (layer == n) {
                if (isOk(pa)) {
                    allParent.add(pa);
                }
                return;
            }

            generateParenthesisTree(pa + "(", layer + 1, n);
            generateParenthesisTree(pa + ")", layer + 1, n);
        }

        public boolean isOk(String pa) {
            char[] tree = pa.toCharArray();
            int left = 0, right = 0;
            for (char a : tree) {
                if (a == '(') {
                    left++;
                } else {
                    right++;
                }
            }
            if (left != right) {
                return false;
            }

            List<Character> list = new LinkedList<>();
            list.add(tree[0]);
            for (int i = 1; i < tree.length; i++) {
                if (tree[i] == ')') {
                    if (list.size() <= 0) {
                        return false;
                    }
                    if (list.get(list.size() - 1) != '(') {
                        return false;
                    }
                    list.remove(list.size() - 1);
                } else {
                    list.add(tree[i]);
                }
            }

            return list.size() <= 0;
        }
    }
}
