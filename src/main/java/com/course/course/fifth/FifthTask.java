package com.course.course.fifth;

import java.util.*;
import java.util.stream.Collectors;

public class FifthTask {
    public static double getAverage(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    public static List<String> toUpperCaseWithPrefix(List<String> list) {
        return list.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Integer> getUniqueSquares(List<Integer> list) {
        return list.stream()
                .filter(i -> Collections.frequency(list, i) == 1)
                .mapToInt(i -> i * i)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<String> filterAndSort(List<String> list, char c) {
        return list.stream()
                .filter(s -> s.startsWith(String.valueOf(c)))
                .sorted()
                .collect(Collectors.toList());
    }

    public static <T> T getLastElement(List<T> list) throws NoSuchElementException {
        return list.stream()
                .reduce((first, second) -> second)
                .orElseThrow(NoSuchElementException::new);
    }

    public static int sumOfEvenNumbers(List<Integer> arr) {
        return arr.stream()
                .filter(i -> i % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static Map<Character, String> toMap(List<String> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (s1, s2) -> s1 + "," + s2));
    }
}
