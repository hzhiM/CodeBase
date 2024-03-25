package com.hzhim.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode n1 = head;
        ListNode n2 = head.next;
        while (n1 != n2 && n1 != null && n2 != null) {
            n1 = n1.next;
            if (n2.next == null) {
                return false;
            }
            n2 = n2.next.next;
        }
        return n1 == n2;


    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode result = new ListNode(0);
        if (l1 != null && l2 != null) {
            int val1 = l1.val + l2.val;
            if (val1 >= 10) {
                if (l1.next == null) {
                    l1.next = new ListNode(1);
                } else {
                    l1.next.val += 1;
                }
            }
            result.val = val1 % 10;

            ListNode next1 = addTwoNumbers(l1.next, l2.next);
            if (next1 != null) {
                result.next = next1;
            }
        } else if (l1 == null) {
            result.val = l2.val;
            ListNode next1 = addTwoNumbers(null, l2.next);
            if (next1 != null) {
                result.next = next1;
            }
        } else {
            int val1 = l1.val;
            if (val1>=10){
                if (l1.next == null) {
                    l1.next = new ListNode(1);
                } else {
                    l1.next.val += 1;
                }
            }
            result.val = val1 % 10;
            ListNode next1 = addTwoNumbers(l1.next, null);
            if (next1 != null) {
                result.next = next1;
            }
        }
        return result;
    }
}
