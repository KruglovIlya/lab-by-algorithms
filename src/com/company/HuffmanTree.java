package com.company;

import java.util.ArrayList;
import java.util.List;

public class HuffmanTree {
    private final SymbolNode origin;
    private List<HuffmanCodeSymbol> codeTable = null;

    public HuffmanTree(SymbolNode node) {
        origin = node;
    }

    public List<HuffmanCodeSymbol> getCodeTable() {
        if (codeTable == null)
            initCodeTable();

        return codeTable;
    }

    private void initCodeTable() {
        codeTable = new ArrayList<>();

        addNodeInTable(origin, "");
    }

    private void addNodeInTable(SymbolNode node, String prefixCode) {
        if (node.isEmptyNode()) {
            addNodeInTable(node.getLeft(), prefixCode + "0");
            addNodeInTable(node.getRight(), prefixCode + "1");
        } else {
            codeTable.add(new HuffmanCodeSymbol(node.getValue(), prefixCode));
        }
    }
}
