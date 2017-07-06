package com.codurance.gildedRose;

public class Item {

    private String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public String name() {
        return name;
    }

    public boolean hasQualityGreaterThan(int value) {
        return quality > value;
    }
}
