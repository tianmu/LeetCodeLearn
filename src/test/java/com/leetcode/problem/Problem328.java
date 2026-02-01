package com.leetcode.problem;

import com.leetcode.common.ListNode;

/**
 *
 */
public class Problem328 {

    public static void main(String[] args) {
        System.out.println(new Solution().oddEvenList(ListNode.of(1, 2, 3, 4, 5)));
    }

    static class Solution {
        public ListNode oddEvenList(ListNode head) {
            ListNode evenNums = new ListNode();
            ListNode iter = head;
            ListNode oddIter = new ListNode();
            ListNode evenNumIter = evenNums;
            int i = 0;
            while(iter != null) {
                i++;
                if(i%2==0) {
                    evenNumIter.next = iter;
                    iter = iter.next;
                    evenNumIter = evenNumIter.next;
                    continue;
                }
                oddIter.next = iter;
                iter = iter.next;
                oddIter = oddIter.next;
            }

            oddIter.next = evenNums.next;
            evenNumIter.next = null;
            return head;
        }
    }
}
