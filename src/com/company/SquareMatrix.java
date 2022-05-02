package com.company;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.log;

public class SquareMatrix {
    private final int[][] Matrix;
    private final int N;
    private int analytic = 0;

    SquareMatrix(int size, int maxNum, int randomSpeed) {
        N = size;

        Random rnd = new Random(randomSpeed);

        Matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            Matrix[i] = new int[size];

            for (int j = 0; j < size; j++)
                Matrix[i][j] = rnd.nextInt(maxNum + 1);
        }
    }


    public void printMatrix() {
        for (int[] item : Matrix) {
            System.out.println(Arrays.toString(item));
        }

        System.out.println();
    }


    private int getSumInterval(int X, int Y, int lenX, int lenY) {
        int sum = 0;
        analytic += lenX * lenY;

        for (int i = X; i < X + lenX; i++)
            for (int j = Y; j < Y + lenY; j++)
                sum += Matrix[i][j];

        return sum;
    }


    public int[][] getSumForMiniSquare(int miniSize) {
        startAnalytic();

        int[][] sum;

        if (miniSize > N)
            throw new IllegalArgumentException("Размер малого квадрата слишком большой!");

        sum = new int[N - miniSize + 1][N - miniSize + 1];
        for (int i = 0; i + miniSize <= N; i++) {
            sum[i] = new int[N - miniSize + 1];

            for (int j = 0; j + miniSize <= N; j++)
                sum[i][j] = 0;
        }

        analytic += miniSize * miniSize;
        for (int i = 0; i < miniSize; i++)
            for (int j = 0; j < miniSize; j++)
                sum[0][0] += Matrix[i][j];

        int prevSumX = sum[0][0],
                prevSumY = sum[0][0];

        for (int y = 0; y + miniSize - 1 < N; y++) {
            for (int x = 0; x + miniSize < N; x++) { // Вертикальные полоски

                prevSumX -= getSumInterval(x, y, 1, miniSize);
                prevSumX += getSumInterval(x + miniSize, y, 1, miniSize);

                sum[x + 1][y] = prevSumX;
            }

            if (y + miniSize < N) { // Горизонтальные полоски

                prevSumY -= getSumInterval(0, y, miniSize, 1);
                prevSumY += getSumInterval(0, y + miniSize, miniSize, 1);

                prevSumX = prevSumY;

                sum[0][y + 1] = prevSumY;
            }
        }

        return sum;
    }

    private void startAnalytic() {
        analytic = 0;
    }


    public int getAbsoluteSize() {
        return N * N;
    }

    public double getAlgorithmComplexity() {
        if (analytic == 0) return 0;
        return log(analytic) / log(getAbsoluteSize());
    }
}
