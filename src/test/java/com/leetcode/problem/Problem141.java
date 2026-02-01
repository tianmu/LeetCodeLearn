package com.leetcode.problem;

import com.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Problem141 {

    public static void main(String[] args) {
    }

    static class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> vals = new HashSet<>();
            while(head != null && head.next != null) {
                if(vals.contains(head)) {
                    return true;
                }
                vals.add(head);
                head=head.next;
            }

            return false;
        }
    }
}
