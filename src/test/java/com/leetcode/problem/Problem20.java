package com.leetcode.problem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Problem20 {

    public static void main(String[] args) {
        System.out.println(new Problem20.Solution().isValid("()[]{}"));
        System.out.println(new Problem20.Solution().isValid("(("));
    }

    static class Solution {
        private static Map<Character, Character> flagMap = new HashMap<>(3);
        static {
            flagMap.put('(', ')');
            flagMap.put('{','}');
            flagMap.put('[',']');
        }
        public boolean isValid(String s) {
            List<Character> statk = new LinkedList<>();
            for(int i =0; i<s.length();i++){
                char flag = s.charAt(i);
                if(flagMap.containsKey(flag)) {
                    statk.add(flag);
                } else if(flagMap.containsValue(flag)) {
                    if(statk.size() > 0 && flag == flagMap.get(statk.get(statk.size() - 1))) {
                        statk.remove(statk.size() - 1);
                        continue;
                    }
                    return false;
                }
            }
            return statk.size() <= 0;
        }
    }

}
