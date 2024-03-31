package com.hzhim.codewar;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maori
 * @date 2024/01/25
 **/
public class NumberN {
    /**
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
     *
     * @param n
     * @return
     */
    public static int zeros(int n) {
        int count = 0;
        // 计算在n的阶乘中能分解出多少个5
        while (n >= 5) {
            n /= 5;
            count += n;
        }
        return count;
    }


    public static String order(String words) {
        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
                .reduce((a, b) -> a + " " + b).get();
        // ...

    }

    public static int[] sortArray(int[] array) {
        List<Integer> oddList = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                oddList.add(array[i]);
                indexList.add(i);
            }
        }
        List<Integer> collect = oddList.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < oddList.size(); i++) {
            array[indexList.get(i)] = collect.get(i);
        }
        return array;
    }


    public static void main(String[] args) {
        int[][] array
                = {{1, 2, 3, 9},
                {4, 5, 6, 4},
                {7, 8, 9, 1},
                {1, 2, 3, 4}};
        snail(array);
        int a = -3;

        System.out.println(-3 % 2);
    }

    public static int[] snail(int[][] array) {
        int n = array.length;
        int index = 0;
        int[] result = new int[n * n];
        int x = 0, y = 0;
        // enjoy
        int length = n - 1;
        int circleIndex = 0;
        while (index < n * n) {
            result[index++] = array[x][y];
            if (index == n * n) {
                return result;
            }
            if (circleIndex / length == 0) {
                y++;
            } else if (circleIndex / length == 1) {
                x++;
            } else if (circleIndex / length == 2) {
                y--;
            } else if (circleIndex / length == 3) {
                x--;
            }
            if (circleIndex == 4 * length - 1) {
                circleIndex = 0;
                length -= 2;
                x++;
                y++;
            } else {
                circleIndex++;
            }
        }
        return result;
    }


    public static int getCount(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                count++;
            }
        }
        return count;
    }

}
