package com.company;

public class Symbol {
    private final char value;
    private final long frequency;

    public Symbol(char value, long frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public char getValue() {
        return value;
    }

    public long getFrequency() {
        return frequency;
    }
}

