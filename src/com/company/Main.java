package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final Comparator<SymbolNode> comparator = new SymbolComparator();
    static String stringToEncode;
    static final PriorityQueue<SymbolNode> priorityQueue = new PriorityQueue<>(10, comparator);
    static HuffmanTree huffmanTree;
    static List<HuffmanCodeSymbol> codeTable = null;

    private static String input() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество строк: ");
        int N = sc.nextInt();

        System.out.println("Теперь введите сами строки: ");
        StringBuilder fullString = new StringBuilder();

        for (int i = 0; i <= N; i++) {
            fullString.append(sc.nextLine());
        }

        return fullString.toString();
    }

    private static void setPriorityQ(String str) {
        Set<Character> dict = str.chars().mapToObj(character -> (char) character).collect(Collectors.toSet());

        for (Character symbol : dict) {
            long count = str.chars().filter(ch -> ch == symbol).count();

            priorityQueue.add(new SymbolNode(symbol, count));
        }
    }

    private static void initHuffmanTree() {
        while (priorityQueue.size() > 1) {
            SymbolNode left = priorityQueue.poll();
            SymbolNode right = priorityQueue.poll();

            SymbolNode center = new SymbolNode(' ', left.getFrequency() + right.getFrequency());

            center.setEmptyNode();
            center.setLeft(left);
            center.setRight(right);

            priorityQueue.add(center);
        }

        huffmanTree = new HuffmanTree(priorityQueue.poll());
    }

    private static String encodeString(String inputString) {
        StringBuilder result = new StringBuilder();

        if (codeTable == null)
            throw new RuntimeException("Таблица кодировки не создана");

        Optional<HuffmanCodeSymbol> hufSymbol;
        for (char symbol : inputString.toCharArray()) {
            hufSymbol = codeTable.stream().filter(s -> s.getSymbol() == symbol).findAny();

            if (hufSymbol.isPresent())
                result.append(hufSymbol.get().getCode());
            else
                throw new RuntimeException("Символ не найден в таблице кодировки");
        }

        return result.toString();
    }

    private static String decodeString(String inputString) {
        StringBuilder result = new StringBuilder();

        if (codeTable == null)
            throw new RuntimeException("Таблица кодировки не создана");

        Optional<HuffmanCodeSymbol> hufSymbol;
        StringBuilder prefix = new StringBuilder();

        for (char symbol : inputString.toCharArray()) {
            hufSymbol = codeTable.stream().filter(s -> Objects.equals(s.getCode(), prefix.toString() + symbol)).findAny();

            if (hufSymbol.isPresent()) {
                result.append(hufSymbol.get().getSymbol());
                prefix.setLength(0);
            } else {
                prefix.append(symbol);
            }
        }

        if (prefix.length() > 0)
            throw new RuntimeException("Не все символы были декодированы");

        return result.toString();
    }

    public static void main(String[] args) {
        // write your code here
        stringToEncode = input();
        setPriorityQ(stringToEncode);

        initHuffmanTree();

        codeTable = huffmanTree.getCodeTable();

        String encodeString = encodeString(stringToEncode);

        System.out.println(encodeString);
        System.out.println(decodeString(encodeString));
    }
}

