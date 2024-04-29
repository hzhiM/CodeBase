package com.hzhim.leetcode;

import java.util.*;

/**
 * @author maori
 * @date 2024/02/07
 **/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] a = {5,1,2,3,4};
//        int[] a2 = {4,4,1,5,1};
        int[] a = {5};
        int[] a2 = {4};
//        solution.canCompleteCircuit(a, a2);
//        solution.reverseWords("a good   example");
//        solution.convert("PAYPALISHIRING", 3);
//        solution.strStr("sabbutsad", "sad");
//        solution.lengthOfLongestSubstring("abcabcbb");
        int[][] rota = {{-2147483646, -2147483646}, {2147483646, 2147483646}};
        solution.findMinArrowShots(rota);
    }

    public int hIndex(int[] citations) {
        int num = citations.length;
        int[] array = new int[num + 1];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= num) {
                array[num] += 1;
            } else {
                array[citations[i]] += 1;
            }
        }
        int tmp = 0;
        for (int i = num; i > 0; i--) {
            tmp += array[i];
            if (tmp >= i) {
                return i;
            }
        }
        return 0;
    }

    Set<Integer> set = new HashSet<Integer>();
    Random random = new Random();
    int index = 0;

    public Solution() {

    }

    public boolean insert(int val) {
        if (set.contains(val)) {
            return false;
        }
        set.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (set.contains(val)) {
            set.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int idx = (index++) % set.size();
        return (int) set.toArray()[idx];

    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        //从前往后
        int[] front = new int[nums.length];
        front[0] = 1;
        //从后往前
        int[] behind = new int[nums.length];
        behind[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            front[i] = nums[i - 1] * front[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            behind[i] = nums[i + 1] * behind[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = front[i] * behind[i];
        }
        return result;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //加的油-开到下一站消耗的油
        int total = 0;
        int minSum = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < minSum) {
                minSum = total;
                index = i;
            }
        }
        if (minSum > 0) {
            return 0;
        }
        return total < 0 ? -1 : (index + 1) % gas.length;


    }

    public String intToRoman(int num) {
        String result = "";
        int thousand = (num / 1000) % 10;
        int hundred = (num / 100) % 10;
        int ten = (num / 10) % 10;
        int one = num % 10;
        if (thousand > 0) {
            result += getRoma(thousand, "M", "M", "M");
        }
        if (hundred > 0) {
            result += getRoma(hundred, "C", "D", "M");
        }
        if (ten > 0) {
            result += getRoma(ten, "X", "L", "C");
        }
        if (one > 0) {
            result += getRoma(one, "I", "V", "X");
        }
        return result;

    }

    public String getRoma(int num, String min, String middle, String max) {
        String result = "";
        if (num < 4) {
            for (int i = 0; i < num; i++) {
                result += min;
            }
            return result;
        } else if (num == 4) {
            return min + middle;
        } else if (num < 9) {
            result += middle;
            for (int i = 0; i < num - 5; i++) {
                result += min;
            }
            return result;
        } else {
            return min + max;
        }
    }

    public int lengthOfLastWord(String s) {
        String[] split = s.split(" ");
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].length() > 0) {
                return split[i].length();
            }
        }
        return 0;
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {

            char a = strs[0].charAt(i);
            for (String str : strs) {
                if (str.length() <= i) {
                    return sb.toString();
                }
                if (str.charAt(i) != a) {
                    return sb.toString();
                }
            }
            sb.append(a);
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].equals("")) {
                sb.append(split[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int num = s.length() / (numRows * 2 - 2);
        int totalCol = (num + 1) * (numRows - 1);
        char[][] array = new char[numRows][totalCol];
        for (int i = 0; i < s.length(); i++) {
            int time = i / (numRows * 2 - 2);
            int left = i % (numRows * 2 - 2);
            int row = left > (numRows - 1) ? 2 * (numRows - 1) - left : left;
            int col = time * (numRows - 1) + (left >= numRows ? left - numRows + 1 : 0);
            array[row][col] = s.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < totalCol; j++) {
                if (array[i][j] != '\u0000') {
                    sb.append(array[i][j]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Boyer-Moore 算法
     */
    public int strStr28(String haystack, String needle) {
        int[] skipTable = new int[256];
        for (int i = 0; i < 256; i++) {
            skipTable[i] = -1;
        }
        for (int i = 0; i < needle.length(); i++) {
            skipTable[needle.charAt(i)] = i;
        }

        int skip;
        for (int i = 0; i <= haystack.length() - needle.length(); i += skip) {
            skip = 0;
            for (int j = needle.length() - 1; j >= 0; j--) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    skip = Math.max(1, j - skipTable[haystack.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return -1;

    }

    /**
     * KMP 算法
     */
    public int strStr(String haystack, String needle) {
        //build next array
        int[] next = new int[needle.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < needle.length() - 1) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        int k = 0, l = 0;
        while (k < haystack.length() && l < needle.length()) {
            if (l == -1 || haystack.charAt(k) == needle.charAt(l)) {
                k++;
                l++;
            } else {
                l = next[l];
            }
        }
        if (l == needle.length()) {
            return k - l;
        }

        return -1;
    }

    public boolean isPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;

    }

    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;

            } else {
                j++;
            }
        }
        if (i == s.length()) {
            return true;
        }
        return false;
    }

//    public int[] twoSum(int[] numbers, int target) {
//        int i = 0;
//        int j = numbers.length - 1;
//        while (i < j) {
//            if (numbers[i] + numbers[j] == target) {
//                return new int[]{i + 1, j + 1};
//            } else if (numbers[i] + numbers[j] > target) {
//                j--;
//            } else {
//                i++;
//            }
//        }
//        return new int[]{-1, -1};
//    }

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return result;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        while (right < s.length()) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            ans = Math.max(ans, right - left + 1);

            right++;
        }
        return ans;
    }


    class NumArray {
        private int[] array;

        public NumArray(int[] nums) {
            this.array = nums;
        }

        public int sumRange(int left, int right) {
            int result = 0;
            while (left <= right) {
                result += array[left++];
            }
            return result;
        }
    }

    public boolean isValidSudoku(char[][] board) {
        //记录每列数字
        int[][] column = new int[9][9];
        int[][] array33 = new int[9][9];
        for (int i = 0; i < 9; i++) {
            //记录每行
            int[] row = new int[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                //row处理
                int num = Character.getNumericValue(board[i][j]) - 1;
                if (row[num] != 0) {
                    return false;
                } else {
                    row[num] = 1;
                }
                //col处理
                if (column[j][num] != 0) {
                    return false;
                } else {
                    column[j][num] = 1;
                }

                //3*3处理
                int row33 = i / 3;
                int col33 = j / 3;
                int index33 = row33 * 3 + col33;
                if (array33[index33][num] != 0) {
                    return false;
                } else {
                    array33[index33][num] = 1;
                }

            }
        }
        return false;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        //右->下->左->上
        int left = 0, right = matrix[0].length - 1;
        int up = 0, down = matrix.length - 1;
        int[] rowDir = {0, 1, 0, -1};
        int[] colDir = {1, 0, -1, 0};

        int dir = 0;
        int row = 0, col = 0;
        List<Integer> result = new ArrayList<>();
        while (left <= right && up <= down) {
            result.add(matrix[row][col]);
            if (dir % 4 == 00 && col == right) {
                up++;
                dir++;
            } else if (dir % 4 == 1 && row == down) {
                dir++;
                right--;
            } else if (dir % 4 == 2 && col == left) {
                dir++;
                down--;
            } else if (dir % 4 == 3 && row == up) {
                dir++;
                left++;
            }
            row += rowDir[dir % 4];
            col += colDir[dir % 4];
        }
        return result;
    }

    public void rotate(int[][] matrix) {
        //每行轮流交换
        for (int i = 0; i < matrix.length / 2; i++) {
            //确定边界
            int left = i, right = matrix.length - i - 1;
            int top = i, down = matrix.length - i - 1;
            //一行里的每一个轮流进行4次交换
            for (int j = 0; j < right - left; j++) {
                //4次交换
                int tmp = 0;
                for (int k = 0; k < 4; k++) {
                    if (k == 0) {
                        //上左到右上
                        tmp = matrix[top + j][right];
                        matrix[top + j][right] = matrix[top][left + j];
                    } else if (k == 1) {
                        // 左下到上左
                        matrix[top][left + j] = matrix[down - j][left];
                    } else if (k == 2) {
                        //下右到↙左下
                        matrix[down - j][left] = matrix[down][right - j];
                    } else if (k == 3) {
                        //右上到下右
                        matrix[down][right - j] = tmp;
                    }
                }
            }

        }

    }

    public void setZeroes(int[][] matrix) {
        //记录行 列 需要置为0的
        int[] rows = new int[matrix.length];
        int[] cols = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rows[i] == 1 || cols[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 死变活 设置为 2
     * 活变死 设置为 3
     */
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //找出周围有几个活细胞
                int aliveNum = getAlive(board, i, j);
                if (board[i][j] == 0 && aliveNum == 3) {
                    board[i][j] = 2;
                }
                if (board[i][j] == 1) {
                    if (aliveNum < 2 || aliveNum > 3) {
                        board[i][j] = 3;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private int getAlive(int[][] board, int i, int j) {
        int num = 0;
        int row = board.length - 1;
        int col = board[0].length - 1;
        //8个方位
        //左上
        if (i > 0 && j > 0 && (board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 3)) {
            num++;
        }
        //上
        if (i > 0 && (board[i - 1][j] == 1 || board[i - 1][j] == 3)) {
            num++;
        }
        //右上
        if (i > 0 && j < col && (board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 3)) {
            num++;
        }
        //左
        if (j > 0 && (board[i][j - 1] == 1 || board[i][j - 1] == 3)) {
            num++;
        }
        //右
        if (j < col && (board[i][j + 1] == 1 || board[i][j + 1] == 3)) {
            num++;
        }
        //左下
        if (i < row && j > 0 && (board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 3)) {
            num++;
        }
        //下
        if (i < row && (board[i + 1][j] == 1 || board[i + 1][j] == 3)) {
            num++;
        }
        //右下
        if (i < row && j < col && (board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 3)) {
            num++;
        }

        return num;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : ransomNote.toCharArray()) {
            Integer fromIndex = map.get(c) == null ? 0 : map.get(c);
            int index = magazine.indexOf(c, fromIndex);
            if (index == -1) {
                return false;
            }
            map.put(c, index + 1);
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);
            if (map2.containsKey(y) && map2.get(y) != x) {
                return false;
            }
            if (map.containsKey(x) && map.get(x) != y) {
                return false;
            }
            map.put(x, y);
            map2.put(y, x);

        }
        return true;
    }

    public boolean wordPattern(String pattern, String s) {
        String[] split = s.split(" ");
        if (split.length != pattern.length()) return false;
        Map<String, Character> map2 = new HashMap<>();
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            char c = pattern.charAt(i);
            String s1 = split[i];
            if ((map.containsKey(c) && !map.get(c).equals(s1)) || (map2.containsKey(s1) && map2.get(s1) != c)) {
                return false;
            }
            map.put(c, s1);
            map2.put(s1, c);
        }
        return true;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] sArray = new int[26];
        int[] tArray = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int i1 = s.charAt(i) - 'a';
            sArray[i1] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            int i1 = t.charAt(i) - 'a';
            tArray[i1] += 1;
        }

        for (int i = 0; i < 26; i++) {
            if (sArray[i] != tArray[i]) return false;
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String s = new String(charArray);
            List<String> aDefault = map.getOrDefault(s, new ArrayList<>());
            aDefault.add(str);
            map.put(s, aDefault);
        }
        return new ArrayList<>(map.values());
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public boolean isHappy(int n) {
        List<Integer> list = new ArrayList<>();

        while (!list.contains(n) && n != 1) {
            list.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int result = 0;
        while (n > 0) {
            int i = n % 10;
            result += i * i;
            n = n / 10;
        }
        return result;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int begin = nums[0], end = begin;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                end++;
                continue;
            } else if (begin == end) {
                result.add(String.valueOf(begin));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(begin);
                sb.append("->");
                sb.append(end);
                result.add(sb.toString());
            }
            begin = nums[i];
            end = begin;
        }
        if (end != begin) {
            result.add(begin + "->" + end);
        } else {
            result.add(String.valueOf(begin));
        }
        return result;
    }

    public int[][] merge(int[][] intervals) {
        List<List<Integer>> list = new ArrayList<>();
        if (intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (right >= intervals[i][0]) {
                right = Math.max(right, intervals[i][1]);
                left = Math.min(left, intervals[i][0]);
            } else {
                List<Integer> subList = new ArrayList<>();
                subList.add(left);
                subList.add(right);
                list.add(subList);
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        List<Integer> subList = new ArrayList<>();
        subList.add(left);
        subList.add(right);
        list.add(subList);
        int[][] array = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            List<Integer> innerList = list.get(i);
            array[i] = new int[innerList.size()];
            for (int j = 0; j < innerList.size(); j++) {
                array[i][j] = innerList.get(j);
            }
        }

        return array;

    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int begin = newInterval[0];
        int end = newInterval[1];
        boolean need = true;
        for (int i = 0; i < intervals.length; i++) {
            if (!need) {
                list.add(intervals[i]);
                continue;
            }
            int left = intervals[i][0];
            int right = intervals[i][1];
            if (left > end) {
                list.add(new int[]{begin, end});
                list.add(intervals[i]);
                //不需要判断
                need = false;
            } else if (begin > right) {
                //新的比这个大
                list.add(intervals[i]);
            } else if (begin > left && end < right) {
                //新的是子集
                list.add(intervals[i]);
                need = false;
            } else {
                //交集 重新定义begin end
                begin = Math.min(begin, left);
                end = Math.max(end, right);
            }
        }
        if (need) {
            list.add(new int[]{begin, end});
        }
        return list.toArray(new int[list.size()][]);
    }

    public int findMinArrowShots(int[][] points) {
        if (points.length == 1) {
            return 1;
        }

        Arrays.sort(points, (o1, o2) -> o1[0] - o2[0]);
        int left = points[0][0];
        int right = points[0][1];
        int result = 1;
        for (int i = 1; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (x > right) {
                result++;
                left = x;
                right = y;
            } else {
                left = Math.max(left, x);
                right = Math.min(right, y);
            }
        }
        return result;
    }


}//end of Solution

