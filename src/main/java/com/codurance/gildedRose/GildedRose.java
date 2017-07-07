package com.codurance.gildedRose;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        updateAllItems();
    }

    private void updateAllItems() {
        for (Item item : items) {
            item.decreaseSellIn();

            item.updateQuality();
        }
    }
}