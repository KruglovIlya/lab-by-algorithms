package com.company;

public class SymbolNode extends Symbol {
    private SymbolNode left;
    private SymbolNode right;
    private boolean isEmptyNode = false;

    public SymbolNode(char value, long frequency) {
        super(value, frequency);
    }

    public void setEmptyNode() {
        isEmptyNode = true;
    }

    public boolean isEmptyNode() {
        return isEmptyNode;
    }

    public void setLeft(SymbolNode s) {
        left = s;
    }

    public void setRight(SymbolNode s) {
        right = s;
    }

    public SymbolNode getRight() {
        return right;
    }

    public SymbolNode getLeft() {
        return left;
    }
}
