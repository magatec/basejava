package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    private static int minValue(int[] values) {
        int reduce;
        reduce = Arrays.stream(values)
                .sorted()
                .peek(System.out::println)
                .distinct()
                .reduce(0, (a, b) -> 10 * a + b);
        return reduce;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int summ = integers.stream().mapToInt(Integer::valueOf).sum() % 2;
        return integers
                .stream()
                .filter(a -> a % 2 != summ)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("minValue = " + minValue(new int[]{1, 2, 1, 5, 3, 7}));
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 121);
        System.out.println("oddOrEven result = " + oddOrEven(integers).toString());
    }
}
