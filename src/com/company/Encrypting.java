package com.company;

public class Encrypting {
    public static String CaesarEncrypt(String input, int shift) {
        StringBuilder str = new StringBuilder(input.toUpperCase());

        for (int j = 0; j < str.length() - 1; j++) {
            if (str.charAt(j) != ' ') {
                char temp = (char) (str.charAt(j) + shift);

                if (temp < 'A')
                    temp += 26;

                str.setCharAt(j, temp);
            }
        }

        return str.toString();
    }

    public static String VizhinerEncrypt(String input, String key) {
        final int bias = 'A';
        final int countLetters = 27;

        StringBuilder res = new StringBuilder();
        final int keyLen = key.length();

        for (int i = 0, len = input.length(); i < len; i++) {
            res.append((char) (((input.charAt(i) + key.charAt(i % keyLen) - 2 * bias) % countLetters) + bias));
        }

        return res.toString();
    }

    public static String VizhinerDecrypt(String input, String key) {
        final int bias = 'A';
        final int countLetters = 27;

        StringBuilder decrypt = new StringBuilder();
        final int keyLen = key.length();

        for (int i = 0, len = input.length(); i < len; i++) {
            decrypt.append((char) (((input.charAt(i) - key.charAt(i % keyLen) + countLetters) % countLetters) + bias));
        }

        return decrypt.toString();
    }
}
