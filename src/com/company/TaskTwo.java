package com.company;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TaskTwo {
    Scanner sc = new Scanner(System.in);
    Integer[] numbers;
    int N;

    public void run() {
        System.out.println("Введите кол-во чисел:");
        N = sc.nextInt();
        numbers = new Integer[N];

        System.out.println("Введите их по порядку:");
        for (int i = 0; i < N; ++i) {
            numbers[i] = sc.nextInt();
        }

        System.out.println("Максимальное произведение трех чисел из набора это: " + findMaxTrinity());
    }

    private int findMaxTrinity() {
        Arrays.sort(numbers, Collections.reverseOrder());

        int firstMul = numbers[0] * numbers[1] * numbers[2];
        int secondMul = numbers[0] * numbers[N - 1] * numbers[N - 2];

        return Math.max(firstMul, secondMul);
    }
}
