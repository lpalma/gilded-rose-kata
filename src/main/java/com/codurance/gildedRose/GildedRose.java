package com.codurance.gildedRose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int ZERO = 0;
    public static final int FIFTY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        updateAllItems();
    }

    private void updateAllItems() {
        for (Item item : items) {
            updateQualityFor(item);
        }
    }

    private void updateQualityFor(Item item) {
        if (shouldDecreaseQuality(item)) {
            decreaseQualityByOne(item);
        } else {
            increaseQuality(item);
        }

        updateSellIn(item);

        updateQualityBasedOnSellIn(item);
    }

    private void updateQualityBasedOnSellIn(Item item) {
        if (item.sellIn < 0) {
            if (!item.hasName(AGED_BRIE)) {
                if (!item.hasName(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT)) {
                    if (item.quality > 0) {
                        if (!item.hasName(SULFURAS_HAND_OF_RAGNAROS)) {
                            decreaseQualityByOne(item);
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

    private void updateSellIn(Item item) {
        if (!item.hasName(SULFURAS_HAND_OF_RAGNAROS)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void decreaseQualityByOne(Item item) {
        item.decreaseQualityByOne();
    }

    private void increaseQuality(Item item) {
        if (item.hasQualityLessThan(FIFTY)) {
            item.increaseQualityByOne();

            if (item.hasName(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT)
                    && item.sellIn < 11
                    && item.quality < 50) {

                item.quality = item.quality + 1;

                if (item.sellIn < 6 && item.sellIn < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
    }

    private boolean shouldDecreaseQuality(Item item) {
        return !item.hasName(AGED_BRIE)
                && !item.hasName(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT)
                && !item.hasName(SULFURAS_HAND_OF_RAGNAROS)
                && item.hasQualityGreaterThan(ZERO);
    }
}