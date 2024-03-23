package com.hzhim.codewar;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author maori
 * @date 2024/01/10
 **/
public class DuplicateEncoder {


    public static void main(String[] args) {

    }

    private Map<Integer, String> timesMap = new HashMap<>();

    public String solution(int n) {
        buildMap();
        String result = "";
        int times = 0;
        while (n > 0) {
            result = toRoma(n % 10, times) + result;
            times++;
            n /= 10;
        }
        return result;
    }

    private String toRoma(int n, int times) {
        String[] split = timesMap.get(times).split(",");
        if (n == 0) {
            return "";
        } else if (n == 1) {
            return split[0];
        } else if (n == 2) {
            return split[0] + split[0];
        } else if (n == 3) {
            return split[0] + split[0] + split[0];
        } else if (n == 4) {
            return split[0] + split[1];
        } else if (n == 5) {
            return split[1];
        } else if (n == 6) {
            return split[1] + split[0];
        } else if (n == 7) {
            return split[1] + split[0] + split[0];
        } else if (n == 8) {
            return split[1] + split[0] + split[0] + split[0];
        } else if (n == 9) {
            return split[0] + split[2];
        }
        return "";
    }

    private void buildMap() {
        timesMap.put(0, "I,V,X");
        timesMap.put(1, "X,L,C");
        timesMap.put(2, "C,D,M");
        timesMap.put(3, "M,M,M");
    }
}
