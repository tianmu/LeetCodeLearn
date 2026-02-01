package com.leetcode.problem;

import com.leetcode.common.ListNode;

/**
 *
 */
public class Problem2095 {

    public static void main(String[] args) {
    }

    static class Solution {
        public ListNode deleteMiddle(ListNode head) {
            int length = getLength(head);
            if(length == 1) {
                return null;
            }

            int mid = length / 2;
            ListNode point = head;
            ListNode parent = null;
            for(int i = 0; i<mid;i++) {
                parent = point;
                point = point.next;
                // System.out.println(i + "parent" + parent.val);
            }

            if(point != null) {
                parent.next = point.next;
            } else {
                parent.next = null;
            }

            return head;
        }

        public int getLength(ListNode head) {
            int i = 1;
            while(head.next != null) {
                i++;
                head = head.next;
            }
            return i;
        }
    }
}
