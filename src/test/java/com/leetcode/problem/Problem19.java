package com.leetcode.problem;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class Problem19 {

    public static void main(String[] args) {
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode temp = head;
            int len = 0;
            while (temp != null) {
                len++;
                temp = temp.next;
            }
            int deletePosition = len - n;
            if (deletePosition < 0) {
                return null;
            }
            ListNode pre = null;
            temp = head;
            for (int i = 0; i < deletePosition; i++) {
                pre = temp;
                temp = temp.next;
            }

            if(pre != null) {
                pre.next = temp.next;
                return head;
            }
            return temp.next;
        }
    }

}
