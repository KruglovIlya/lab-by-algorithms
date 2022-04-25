package com.company;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        System.out.println(Encrypting.CaesarEncrypt("testing message", 1));

        String myString = "testing";

        System.out.println(myString);
        System.out.println(Encrypting.VizhinerEncrypt(myString.toUpperCase(), "KEY"));
        System.out.println(Encrypting.VizhinerDecrypt(Encrypting.VizhinerEncrypt(myString.toUpperCase(), "KEY"), "KEY"));

    }
}
