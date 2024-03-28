package com.hzhim.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

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
            if (val1 >= 10) {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = null;
        ListNode end = null;
        while (list1 != null || list2 != null) {
            int value;
            if (list1 == null) {
                value = list2.val;
                list2 = list2.next;
            } else if (list2 == null) {
                value = list1.val;
                list1 = list1.next;
            } else if (list1.val <= list2.val) {
                value = list1.val;
                list1 = list1.next;
            } else {
                value = list2.val;
                list2 = list2.next;
            }
            if (result == null) {
                result = end = new ListNode(value);
            } else {
                end.next = new ListNode(value);
                end = end.next;
            }
        }
        return result;

    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node result = null;
        Node newNode = head;
        while (newNode != null) {
            map.put(newNode, new Node(newNode.val));
            newNode = newNode.next;
        }

        newNode = head;
        while (newNode != null) {
            map.get(newNode).next = map.get(newNode.next);
            map.get(newNode).random = map.get(newNode.random);
            newNode = newNode.next;
        }


        return map.get(head);
    }

    /**
     * 1.找到当前curr，就是需要转换的第一个（也就是排序完后的最后一个）设置为curr
     * 2. 找到需要颠倒的下一个，就是curr.next next=curr.next
     * 3. 把curr的下一个设置为 下一个的下一个 curr.next=next.next
     * 4. 把next指向拍完序的第一个，也就是pre.next
     * 5. 把拍完序的下一个设置为新的，pre.next=next
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }


        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode remove = head;
        while (remove != null) {
            remove = remove.next;
            count++;
        }
        remove = dummy;
        for (int i = 0; i < count - n ; i++) {
            remove = remove.next;
        }
        if (remove.next != null) {
            remove.next = remove.next.next;
        }
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode
    }
}
