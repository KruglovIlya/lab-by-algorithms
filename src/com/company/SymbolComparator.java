package com.company;

import java.util.Comparator;

public class SymbolComparator implements Comparator<SymbolNode> {
    @Override
    public int compare(SymbolNode x, SymbolNode y) {
        return Long.compare(x.getFrequency(), y.getFrequency());
    }
}