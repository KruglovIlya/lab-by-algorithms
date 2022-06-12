package com.company;

import java.util.Scanner;

public class TaskOne {
    Scanner sc = new Scanner(System.in);
    int[] stones;
    int N;
    int sum = 0;

    public void run() {
        System.out.println("Введите кол-во камней:");
        N = sc.nextInt();
        stones = new int[N];

        System.out.println("Введите их веса по порядку:");
        for (int i = 0; i < N; ++i) {
            stones[i] = sc.nextInt();
            sum += stones[i];
        }

        System.out.println("Минимальная разница куч: " + getMinDif());

    }

    private int getMinDif() {
        final int max = (1) << N;
        int different;
        int minDifferent = sum;

        for (int variant = 1; variant <= max; variant++) {
            different = 0;

            int variantPosition = variant;

            for (int position = 0;
                 position < N;
                 ++position, variantPosition /= 2) {

                if (variantPosition % 2 == 1)
                    different += stones[position];
                else
                    different -= stones[position];
            }

            if (minDifferent > Math.abs(different))
                minDifferent = Math.abs(different);
        }

        return minDifferent;
    }
}
