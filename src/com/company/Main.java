package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Random rnd = new Random(System.currentTimeMillis());

    static int getRandomNumber(int min, int max) {
        return min + rnd.nextInt(max - min + 1);
    }

    public static void lab_1() {
        System.out.println(Encrypting.CaesarEncrypt("testing message", 1));

        String myString = "testing";

        System.out.println(myString);
        System.out.println(Encrypting.VizhinerEncrypt(myString.toUpperCase(), "KEY"));
        System.out.println(Encrypting.VizhinerDecrypt(Encrypting.VizhinerEncrypt(myString.toUpperCase(), "KEY"), "KEY"));

    }

    public static long lab_2() {
        MyQueue myQueue = new MyQueue();
        long fullTime = 0;

        for (int epoch = 0; epoch < 10; epoch++) {
            long timeSum = 0;

            for (int i = 0; i < 100; i++) {
                myQueue.addPerson(getRandomNumber(90, 420));
            }

            Person firstPerson = myQueue.pollPerson(getRandomNumber(120, 300));
            Date StartTimeAbsolute = firstPerson.getInsertTime();

            timeSum += firstPerson.getSubWithInsertAndOutTime();

            for (int i = 1; i < 99; i++) {
                timeSum += myQueue.pollPerson(getRandomNumber(120, 300)).getSubWithInsertAndOutTime();
            }

            Date finalTimeAbsolute = myQueue.pollPerson(getRandomNumber(120, 300)).getOutTime();

            fullTime += finalTimeAbsolute.getTime() - StartTimeAbsolute.getTime();

            System.out.println("Общее время очереди - " + TimeUnit.MILLISECONDS.toMinutes(finalTimeAbsolute.getTime() - StartTimeAbsolute.getTime()) + " минут");
            System.out.println("Суммарное время ожидания 100 посетителей - " + TimeUnit.MILLISECONDS.toMinutes(timeSum) + " минут");
            System.out.println("В среднем посетитель стоит в очереди - " + TimeUnit.MILLISECONDS.toMinutes(timeSum / 100) + " минут");
            System.out.println();
        }

        return fullTime;
    }

    public static long lab_2_part_2() {
        MyQueue myQueueOne = new MyQueue();
        MyQueue myQueueTwo = new MyQueue();

        long fullTime = 0;

        for (int epoch = 0; epoch < 10; epoch++) {
            long timeSum = 0;

            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0)
                    myQueueOne.addPerson(getRandomNumber(90, 420));
                else
                    myQueueTwo.addPerson(getRandomNumber(90, 420));
            }

            Person firstPerson = myQueueOne.pollPerson(getRandomNumber(120, 300));
            Date StartTimeAbsolute = firstPerson.getInsertTime();

            timeSum += firstPerson.getSubWithInsertAndOutTime();

            for (int i = 1; i < 98; i++) {
                if (i % 2 == 0)
                    timeSum += myQueueOne.pollPerson(getRandomNumber(120, 300)).getSubWithInsertAndOutTime();
                else
                    timeSum += myQueueTwo.pollPerson(getRandomNumber(120, 300)).getSubWithInsertAndOutTime();

            }

            Date finalTimeOne = myQueueOne.pollPerson(getRandomNumber(120, 300)).getOutTime();
            Date finalTimeTwo = myQueueTwo.pollPerson(getRandomNumber(120, 300)).getOutTime();
            Date finalTimeAbsolute = finalTimeTwo;

            if (finalTimeOne.getTime() > finalTimeTwo.getTime())
                finalTimeAbsolute = finalTimeOne;

            fullTime += finalTimeAbsolute.getTime() - StartTimeAbsolute.getTime();

            System.out.println("Общее время очереди из 100 посетителей - " + TimeUnit.MILLISECONDS.toMinutes(finalTimeAbsolute.getTime() - StartTimeAbsolute.getTime()) + " минут");
            System.out.println("Суммарное время ожидания 100 посетителей - " + TimeUnit.MILLISECONDS.toMinutes(timeSum) + " минут");
            System.out.println("В среднем посетитель стоит в очереди - " + TimeUnit.MILLISECONDS.toMinutes(timeSum / 100) + " минут");

            System.out.println();
        }

        return fullTime;
    }

    public static void lab_3() {
        Shop myShop = new Shop("Товар 1", new Date(2022, Calendar.APRIL, 20), 500, 10, 1);

        myShop.pushToEnd("Товар 2", new Date(2022, Calendar.APRIL, 20), 700, 5, 2);

        myShop = myShop.pushToStart("Товар 3", new Date(2022, Calendar.APRIL, 21), 400, 12, 3);
        myShop = myShop.pushToStart("Товар 4", new Date(2022, Calendar.APRIL, 21), 400, 12, 4);

        if (!myShop.delById(4))
            System.out.println("Товар не был найден");

        for (Shop product : myShop.getAllItems()) {
            product.printItem();
        }


        System.out.println();
        Double price = myShop.getPriceByName("Товар 1");

        if (price != null)
            System.out.printf("Полная стоимость партии Товар 1: %.2f р \n\n", price);
        else
            System.out.println("Товар не найден \n\n");

        for (Shop product : myShop.getItemsByArrivingDate(new Date(2022, Calendar.APRIL, 20))) {
            product.printItem();
        }

    }

    public static void main(String[] args) {
        lab_3();
    }
}
