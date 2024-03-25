package com.hzhim.leetcode;

import java.util.*;

/**
 * @author maori
 * @since 2024/03/21
 **/
public class StackQ {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }

            } else if (s.charAt(i) == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if (s.charAt(i) == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();

        for (String s : path.split("/")) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }

            } else if (!s.equals(".") && s.equals("")) {
                stack.push(s);
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return result.equals("") ? "/" : result;
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

}

