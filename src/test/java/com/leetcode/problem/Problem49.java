package com.leetcode.problem;

import java.util.*;

/*
49. 字母异位词分组

给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

 

示例 1:

输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]

输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

解释：

在 strs 中没有字符串可以通过重新排列来形成 "bat"。
字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
示例 2:

输入: strs = [""]

输出: [[""]]

示例 3:

输入: strs = ["a"]

输出: [["a"]]

 

提示：

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] 仅包含小写字母

*/


public class Problem49 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 测试用例1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("输入: " + Arrays.toString(strs1));
        System.out.println("输出: " + solution.groupAnagrams(strs1));
        System.out.println("输出: " + solution.groupAnagramsByCount(strs1));

        // 测试用例2
        String[] strs2 = {""};
        System.out.println("输入: " + Arrays.toString(strs2));
        System.out.println("输出: " + solution.groupAnagrams(strs2));
        System.out.println("输出: " + solution.groupAnagramsByCount(strs2));


        // 测试用例3
        String[] strs3 = {"a"};
        System.out.println("输入: " + Arrays.toString(strs3));
        System.out.println("输出: " + solution.groupAnagrams(strs3));
        System.out.println("输出: " + solution.groupAnagramsByCount(strs3));
    }

    static class Solution {
        /**
         * 方法1: 排序法
         * 思路：字母异位词排序后会得到相同的字符串
         * 时间复杂度: O(n * k * log k) - n是字符串数量，k是字符串平均长度
         * 空间复杂度: O(n * k)
         */
        public List<List<String>> groupAnagrams(String[] strs) {
            // 使用HashMap存储，key是排序后的字符串，value是原始字符串列表
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                // 将字符串转换为字符数组并排序
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String sortedStr = new String(chars);

                // 如果key不存在，创建新的列表
                if (!map.containsKey(sortedStr)) {
                    map.put(sortedStr, new ArrayList<>());
                }
                // 将原始字符串添加到对应的列表中
                map.get(sortedStr).add(str);
            }

            // 返回所有分组
            return new ArrayList<>(map.values());
        }

        /**
         * 方法2: 字符计数法（更优化）
         * 思路：统计每个字符串中26个字母出现的次数，用计数数组作为key
         * 时间复杂度: O(n * k) - 比排序法更快
         * 空间复杂度: O(n * k)
         */
        public List<List<String>> groupAnagramsByCount(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                // 统计每个字符出现的次数
                int[] count = new int[26];
                for (char c : str.toCharArray()) {
                    count[c - 'a']++;
                }

                // 将计数数组转换为字符串作为key
                // 格式: "1#2#0#0#...#0" 表示a出现1次，b出现2次，等等
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 26; i++) {
                    sb.append(count[i]).append('#');
                }
                String key = sb.toString();

                // 如果key不存在，创建新的列表
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(str);
            }

            return new ArrayList<>(map.values());
        }
    }
}
