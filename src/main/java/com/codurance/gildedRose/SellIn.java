package com.codurance.gildedRose;

public class SellIn {
    private int value;

    public SellIn(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public void decreaseByOne() {
        --value;
    }

    public boolean below(int value) {
        return this.value < value;
    }
}
