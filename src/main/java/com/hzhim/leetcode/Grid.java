package com.hzhim.leetcode;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author maori
 * @since 2024/04/07
 **/
public class Grid {
    public static void main(String[] args) {
        Grid solu = new Grid();
//        char[][] grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
//        solu.numIslands(grid);
        List<List<String>> equations = new ArrayList<List<String>>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("e", "f"));
        equations.add(Arrays.asList("b", "e"));
//        equations.add(Arrays.asList("x4", "x5"));
        double[] values = {3.4, 1.4, 2.3};
        List<List<String>> queries = new ArrayList<List<String>>();
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "f"));
        queries.add(Arrays.asList("a", "a"));
        solu.calcEquation(equations, values, queries);
    }

    public int numIslands(char[][] grid) {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    result++;

                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int[] rows = {-1, 1, 0, 0};
        int[] cols = {0, 0, 1, -1};
        for (int k = 0; k < 4; k++) {
            int row = i + rows[k];
            int col = j + cols[k];
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
                continue;
            }
            dfs(grid, row, col);
        }

    }

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                solvedfs(board, i, 0);
            }
            if (board[i][board[0].length - 1] == 'O') {
                solvedfs(board, i, board[0].length - 1);
            }

        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                solvedfs(board, 0, i);
            }
            if (board[board.length - 1][i] == 'O') {
                solvedfs(board, board.length - 1, i);
            }

        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void solvedfs(char[][] grid, int i, int j) {
        grid[i][j] = '1';
        int[] rows = {-1, 1, 0, 0};
        int[] cols = {0, 0, 1, -1};
        for (int k = 0; k < 4; k++) {
            int row = i + rows[k];
            int col = j + cols[k];
            if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 'X' || grid[row][col] == '1') {
                continue;
            }
            solvedfs(grid, row, col);
        }

    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return node;
        Map<Node, Node> map = new HashMap<>();
        Set<Node> set = new HashSet<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(node);

        while (!deque.isEmpty()) {
            Node pop = deque.pop();
            set.add(pop);
            Node outNode = map.getOrDefault(pop, new Node(pop.val));
            List<Node> newNeighbor = new ArrayList<>();
            for (Node neighbor : pop.neighbors) {
                if (!set.contains(neighbor)) {
                    deque.push(neighbor);
                }
                Node mapNode = map.getOrDefault(neighbor, new Node(neighbor.val));
                map.put(neighbor, mapNode);
                newNeighbor.add(mapNode);

            }
            outNode.neighbors = newNeighbor;
        }
        return map.get(node);
    }

    /**
     * a,b b,c c,d d,e
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> strings = equations.get(i);
            String a = strings.get(0);
            String b = strings.get(1);
            double value = values[i];
            // a/b=value  a=b*value b=a/value
            Map<String, Double> amap = map.getOrDefault(a, new HashMap<>());
            //a中的其他元素 也可以添加b
            amap.put(b, value);
            Map<String, Double> bmap = map.getOrDefault(b, new HashMap<>());
            bmap.put(a, 1 / value);
//            amap.put(a, 1d);
//            bmap.put(b, 1d);
            map.put(a, amap);
            map.put(b, bmap);

            //处理额外的数据
            for (String s : amap.keySet()) {
                //s/a =val a/b=value ->s/b=val*value
                map.get(s).put(b, value * map.get(s).get(a));
                map.get(b).put(s, 1 / (value * map.get(s).get(a)));
            }

            //b中的其他元素 可以添加a
            for (String s : bmap.keySet()) {
                //s/b =val a/b=value ->s/a=val/value
                map.get(s).put(a, map.get(s).get(b) / value);
                map.get(a).put(s, value / map.get(s).get(b));
            }
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> strings = queries.get(i);
            String a = strings.get(0);
            String b = strings.get(1);

            Map<String, Double> amap = map.get(a);
            Map<String, Double> bmap = map.get(b);
            if (amap == null || bmap == null) {
                result[i] = -1;
                continue;
            }
            if (a.equals(b)) {
                result[i] = 1;
                continue;
            }
            Set<String> aset = new HashSet<>(amap.keySet());
            Set<String> bset = new HashSet<>(bmap.keySet());
            aset.retainAll(bset);
            String[] array = aset.toArray(new String[0]);
            if (array.length == 0) {
                result[i] = -1;
            } else {
                Map<String, Double> inmap = map.get(array[0]);
                Double doubleA = inmap.get(a);
                Double doubleB = inmap.get(b);
                result[i] = doubleB / doubleA;
            }
        }
        return result;
    }

    /**
     * 1->2 2->3 3->4 1-
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            degree[prerequisite[0]]++;
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                deque.push(i);
            }
        }

        while (!deque.isEmpty()) {
            numCourses--;
            Integer pop = deque.pop();
            List<Integer> nei = adjacency.get(pop);
            for (Integer i : nei) {
                degree[i]--;
                if (degree[i] == 0) {
                    deque.push(i);
                }
            }

        }
        return numCourses == 0;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degree = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            degree[prerequisite[0]]++;
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                deque.push(i);
            }
        }
        List<Integer> result = new ArrayList<>();

        while (!deque.isEmpty()) {
            numCourses--;
            Integer pop = deque.pop();
            result.add(pop);
            List<Integer> nei = adjacency.get(pop);
            for (Integer i : nei) {
                degree[i]--;
                if (degree[i] == 0) {
                    deque.push(i);
                }
            }

        }
        int[] intArray = new int[result.size()];

        // 遍历 result，将每个 Integer 元素转换为 int 并添加到 intArray 中
        for (int i = 0; i < result.size(); i++) {
            intArray[i] = result.get(i); // 自动拆箱，将 Integer 转换为 int
        }
        return numCourses == 0 ? intArray : new int[0];
    }


    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visit = new boolean[n * n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i <= 6; i++) {

                int next = poll[0] + i;
                if (next > n * n) {
                    break;
                }
                int[] rc = id2rx(next, n);
                if (board[rc[0]][rc[1]] > 0) {
                    next = board[rc[0]][rc[1]];
                }
                if (next == n * n) {
                    return poll[1] + 1;
                }
                if (!visit[next]) {
                    visit[next] = true;
                    queue.offer(new int[]{next, poll[1] + 1});
                }
            }
        }
        return -1;
    }

    private int[] id2rx(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<String>();
        Set<String> visible = new HashSet<String>();
        for (String s : bank) {
            bankSet.add(s);
        }

        if (!bankSet.contains(endGene)) {
            return -1;
        }
        if (startGene.equals(endGene)) {
            return 0;
        }
        char[] gens = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        visible.add(startGene);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < poll.length(); j++) {
                    for (int k = 0; k < gens.length; k++) {
                        if (poll.charAt(j) == gens[k]) {
                            continue;
                        }
                        StringBuffer sb = new StringBuffer(poll);
                        sb.setCharAt(j, gens[k]);
                        String newStr = sb.toString();
                        if (newStr.equals(endGene)) {
                            return step;
                        }
                        if (bankSet.contains(newStr) && !visible.contains(newStr)) {
                            visible.add(newStr);
                            queue.add(newStr);
                        }
                    }

                }
            }
            step++;
        }
        return -1;


    }
}
