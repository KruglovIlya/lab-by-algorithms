package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Random rnd = new Random(System.currentTimeMillis());

    public static void lab_1() {
        System.out.println(Encrypting.CaesarEncrypt("testing message", 1));

        String myString = "testing";

        System.out.println(myString);
        System.out.println(Encrypting.VizhinerEncrypt(myString.toUpperCase(), "KEY"));
        System.out.println(Encrypting.VizhinerDecrypt(Encrypting.VizhinerEncrypt(myString.toUpperCase(), "KEY"), "KEY"));

    }

    static int getRandomNumber(int min, int max) {
        return min + rnd.nextInt(max - min + 1);
    }

    public static void lab_2() {
        MyQueue myQueue = new MyQueue();
        for (int epoch = 0; epoch < 10; epoch++) {
            long timeSum = 0;

            for (int i = 0; i < 100; i++) {
                myQueue.addPerson(getRandomNumber(180, 600));
            }

            for (int i = 0; i < 100; i++) {
                timeSum += myQueue.pollPerson(getRandomNumber(120, 300)).getSubWithInsertAndOutTime();
            }

            System.out.println("Суммарное время 100 посетителей - " + TimeUnit.MILLISECONDS.toSeconds(timeSum) + " секунд");
            System.out.println("В среднем посетитель стоит в очереди - " + TimeUnit.MILLISECONDS.toSeconds(timeSum/100) + " секунд");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        lab_2();
    }
}
