package com.leetcode.common;


public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int... values) {
        ListNode head = new ListNode();
        ListNode iter = head;
        for (int value : values) {
            iter.next = new ListNode(value);
            iter = iter.next;
        }
        return head.next;
    }
}
