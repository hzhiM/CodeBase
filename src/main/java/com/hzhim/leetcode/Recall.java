package com.hzhim.leetcode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author maori
 * @since 2024/04/23
 **/
public class Recall {
    public static void main(String[] args) {
        Recall recall = new Recall();
//        recall.letterCombinations("23");
//        recall.combine(4, 2);
        recall.permute(new int[]{1, 2, 3});
    }

    public List<String> letterCombinations(String digits) {
        Map<Character, List<Character>> initMap = new HashMap<>();
        initMap.put('2', Arrays.asList('a', 'b', 'c'));
        initMap.put('3', Arrays.asList('d', 'e', 'f'));
        initMap.put('4', Arrays.asList('g', 'h', 'i'));
        initMap.put('5', Arrays.asList('j', 'k', 'l'));
        initMap.put('6', Arrays.asList('m', 'n', 'o'));
        initMap.put('7', Arrays.asList('p', 'q', 'r', 's'));
        initMap.put('8', Arrays.asList('t', 'u', 'v'));
        initMap.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        List<String> subResult = Arrays.asList("");
        if (digits.length() > 1) {
            subResult = letterCombinations(digits.substring(1));
        }
        char c = digits.charAt(0);
        for (Character character : initMap.get(c)) {
            for (String s : subResult) {
                result.add(character + s);
            }
        }
        return result;
    }

    public List<List<Integer>> combine(int n, int k) {
        return combine(n, k, 1);
    }

    private List<List<Integer>> combine(int n, int k, int begin) {
        List<List<Integer>> result = new ArrayList<>();
        if (k == 1) {
            for (int i = begin; i <= n; i++) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                result.add(list);
            }
            return result;
        }
        if (n - begin + 1 == k) {
            List<Integer> subList = new ArrayList<>();
            for (int i = begin; i <= n; i++) {
                subList.add(i);
            }
            result.add(subList);
        } else {
            //选
            List<List<Integer>> combine = combine(n, k - 1, begin + 1);
            if (combine.size() == 0) {
                List<Integer> objects = new ArrayList<>();
                objects.add(begin);
                combine.add(objects);
            } else {
                for (List<Integer> integers : combine) {
                    integers.add(begin);
                }
            }

            //不选
            List<List<Integer>> combine1 = combine(n, k, begin + 1);
            result.addAll(combine1);
            result.addAll(combine);
        }
        return result;

    }

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return permute(list);
    }

    public List<List<Integer>> permute(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.size() == 1) {
            ArrayList<Integer> e = new ArrayList<>();
            e.add(nums.get(0));
            result.add(e);
            return result;
        }
        for (int i = 0; i < nums.size(); i++) {
            ArrayList<Integer> newList = new ArrayList<>(nums);
            newList.remove(i);
            List<List<Integer>> subList = permute(newList);
            //加上i
            for (List<Integer> integers : subList) {
                integers.add(nums.get(i));
            }
            result.addAll(subList);

        }
        return result;

    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, target, 0);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target, int begin) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = begin; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate > target) {
                return result;
            }
            if (candidate == target) {
                List<Integer> list = new ArrayList<>();
                list.add(candidate);
                result.add(list);
            }
            List<List<Integer>> lists = combinationSum(candidates, target - candidate, i);
            if (!lists.isEmpty()) {
                for (List<Integer> list : lists) {
                    list.add(candidate);
                }
            }
            result.addAll(lists);
        }
        return result;
    }

    private int n;
    private char[] path;
    private List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        this.n = n;
        path = new char[n * 2];
        dfs(0, 0);
        return result;
    }

    private void dfs(int i, int open) {
        if (i == n * 2) {
            result.add(new String(path));
            return;
        }
        if (open < n) {
            path[i] = '(';
            dfs(i + 1, open + 1);

        }
        if (i - open < open) {
            path[i] = ')';
            dfs(i + 1, open);
        }
    }

    public boolean exist(char[][] board, String word) {
        char[] charArray = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (wordExists(board, charArray, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean wordExists(char[][] board, char[] charArray, int i, int j, int index) {
        if (i < 0 || j < 0
                || i >= board.length || i >= board[0].length
                || board[i][j] != charArray[index]) return false;
        if (index == (charArray.length - 1)) return true;
        board[i][j] = '\0';
        boolean res = wordExists(board, charArray, i-1, j, index+1)
                || wordExists(board, charArray, i+1, j, index+1)
                || wordExists(board, charArray, i, j-1, index+1)
                || wordExists(board, charArray, i, j+1, index+1);
        board[i][j] = charArray[index];
        return res;
    }


}
