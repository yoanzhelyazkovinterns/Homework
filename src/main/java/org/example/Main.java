package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

        public static void main(String[] args) {
            readAndPrintNumberInput();
        }

        public static int countDuplicates(List<Integer> numbers) {
            long nonUniqueCount = numbers
                    .stream()
                    .distinct()
                    .filter(num -> Collections.frequency(numbers, num) > 1)
                    .count();

            return Long.valueOf(nonUniqueCount).intValue();
        }

        private static void readAndPrintNumberInput() {
            var scanner = new Scanner(System.in);
            var n = scanner.nextInt();

            List<Integer> numbers = IntStream.range(0, n)
                    .mapToObj(i -> scanner.nextInt())
                    .collect(Collectors.toList());

            var result = countDuplicates(numbers);
            System.out.println(result);
        }
    }