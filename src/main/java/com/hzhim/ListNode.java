package com.hzhim;

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
        ListNode result = new ListNode(0);
        while (l1 != null || l2 != null) {
            int r=0;
            if (l1!=null){
                r+=l1.val;
            }
            if (l2!=null){
                r+=l2.val;
            }
            result.val=r;

        }
        return result;
    }
}
