package src.main.java.com.hzhim;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        byte a = 12;
        byte b = 12;
        Main main = new Main();
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }

    static class MinStack {
        private Stack<Integer> stack;
        private List<Integer> list;

        public MinStack() {
            this.stack = new Stack();
            this.list = new ArrayList<>();
        }

        public void push(int val) {
            stack.push(val);
            list.add(val);
            list.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 < o2) {
                        return -1;
                    } else if (o1 == o2) {
                        return 0;
                    } else {
                        return 1;
                    }

                }
            });
        }

        public void pop() {
            Integer pop = stack.pop();
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(pop)) {
                    iterator.remove();
                    return;
                }
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return list.get(0);
        }
    }

    public int evalRPN(String[] tokens) {
        int result = 0;
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("/")) {
                String a = stack.pop();
                String b = stack.pop();
                result = Integer.parseInt(b) / Integer.parseInt(a);
                stack.push(String.valueOf(result));
            } else if (token.equals("+")) {
                String a = stack.pop();
                String b = stack.pop();
                result = Integer.parseInt(b) + Integer.parseInt(a);
                stack.push(String.valueOf(result));
            } else if (token.equals("-")) {
                String a = stack.pop();
                String b = stack.pop();
                result = Integer.parseInt(b) - Integer.parseInt(a);
                stack.push(String.valueOf(result));
            } else if (token.equals("*")) {
                String a = stack.pop();
                String b = stack.pop();
                result = Integer.parseInt(b) * Integer.parseInt(a);
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
                result = Integer.parseInt(token);
            }
        }
        return result;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
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
            n2=n2.next.next;
        }
        return n1==n2;


    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    }
}
