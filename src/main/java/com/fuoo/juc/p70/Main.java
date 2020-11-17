package com.fuoo.juc.p70;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fuoo
 * @date 2020/11/16 16:48
 * @description: stream
 */
public class Main {
    public static void main(String[] args) {
        List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> collect = arrayList.stream().filter(num -> {
            return num > 3;
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
}
