package com.hzhim.leetcode;

import java.util.Stack;

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

            } else if (!s.equals(".")&&s.equals("")) {
                stack.push(s);
            }
        }
        String result = "";
        while (!stack.isEmpty()) {
            result = "/" + stack.pop() + result;
        }
        return result.equals("") ? "/" : result;
    }
}

