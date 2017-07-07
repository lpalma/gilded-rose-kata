package com.codurance.gildedRose;

public class ItemQuality {

    private int value;

    public ItemQuality(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean above(int value) {
        return this.value > value;
    }

    public void decrement() {
        --value;
    }

    public boolean below(int value) {
        return this.value < value;
    }

    public void increment() {
        ++value;
    }

    public void setToZero() {
        value = 0;
    }
}
