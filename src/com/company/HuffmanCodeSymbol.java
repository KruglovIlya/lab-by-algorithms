package com.company;

public class HuffmanCodeSymbol {
    private final char symbol;
    private final String code;

    public HuffmanCodeSymbol(char symbol, String code) {
        this.symbol = symbol;
        this.code = code;
    }


    public char getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }
}
