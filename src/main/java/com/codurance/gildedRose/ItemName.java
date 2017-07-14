package com.codurance.gildedRose;

public class ItemName {
    public static final String CONJURED = "Conjured";
    private String name;

    public ItemName(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

    public boolean is(String name) {
        return this.name
                .equals(name);
    }

    public boolean isConjured() {
        return name.startsWith(CONJURED);
    }
}
