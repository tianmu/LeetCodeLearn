package com.leetcode.problem;

import java.util.HashMap;
import java.util.Map;

public class Problem136 {

    public static void main(String[] args) {
        // 1
        System.out.println(new Solution().singleNumber(new int[]{2,2,1}));
    }

    static class Solution {
        // 方法2，异或运算， a^a=0
        public int singleNumber(int[] nums) {
            int single = 0;
            for (int num : nums) {
                single ^= num;
            }
            return single;
        }

        // 方法1， map遍历
        public int singleNumber1(int[] nums) {
            Map<Integer, Boolean> map = new HashMap<>(nums.length);
            for(int num : nums) {
                if(map.containsKey(num)) {
                    map.put(num, false);
                    continue;
                }
                map.put(num, true);
            }

            for(Integer key : map.keySet()) {
                if(map.get(key)) {
                    return key;
                }
            }

            return 0;
        }
    }
}
