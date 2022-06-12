package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N = 5;
    static int[][] table = {
            {0, 0, 1, 0, 0},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {0, 0, 1, 0, 0}
    };

    public static void main(String[] args) {
        printTable();

        algorithmWarshall();
    }

    static void algorithmWarshall() {
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                if (table[row][col] == 1)
                    for(int path : getAvailablePaths(col)){
                        table[row][path] = 1;
                        printTable();
                    }


    }
    static Integer[] getAvailablePaths(int row) {
        List<Integer> result = new ArrayList<>(5);

        for (int col = 0; col < N; col++)
            if (table[row][col] == 1)
                result.add(col);

        return result.toArray(new Integer[0]);
    }

    static void printTable() {
        for (int[] row : table)
            System.out.println(Arrays.toString(row));

        System.out.println();
    }
}
