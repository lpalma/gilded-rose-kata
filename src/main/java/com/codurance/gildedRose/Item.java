package com.codurance.gildedRose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int ZERO = 0;
    public static final int FIFTY = 50;
    public static final int ELEVEN = 11;
    public static final int SIX = 6;

    private ItemName name;

    private SellIn sellIn;

    private ItemQuality quality;

    public Item(String name, int sellIn, int quality) {
        this.name = new ItemName(name);
        this.sellIn = new SellIn(sellIn);
        this.quality = new ItemQuality(quality);
    }

    private boolean hasName(String name) {
        return this.name.is(name);
    }

   @Override
   public String toString() {
        return name.value()
                + ", "
                + sellIn.value()
                + ", "
                + quality.value();
    }

    public String name() {
        return name.value();
    }

    public void update() {
        if (isSpecialItem()) {
            updateSpecialItems();
            return;
        }

        updateOtherItems();
    }

    private boolean isSpecialItem() {
        return isAgedBrie()
                || isBackstagePasses()
                || isSulfurasHandOfRagnaros();
    }

    private boolean isBackstagePasses() {
        return hasName(BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT);
    }

    private boolean isAgedBrie() {
        return hasName(AGED_BRIE);
    }

    private boolean isSulfurasHandOfRagnaros() {
        return hasName(SULFURAS_HAND_OF_RAGNAROS);
    }

    private void updateSpecialItems() {
        if (hasQualityLessThan(FIFTY)) {
            increaseQualityByOne();
        }

        if (isAgedBrie()) {
            updateAgedBrie();
            return;
        }

        if (isBackstagePasses()) {
            updateBackstagePasses();
        }
    }

    private void updateOtherItems() {
        decreaseSellInByOne();

        decreaseQuality();
    }

    private void decreaseQuality() {
        if (hasQualityGreaterThan(ZERO)) {
            decreaseQualityByOne();
        }

        if (isSellInBelow(ZERO) && hasQualityGreaterThan(ZERO)) {
            decreaseQualityByOne();
        }
    }

    private void updateBackstagePasses() {
        increaseQuality();

        decreaseSellInByOne();

        if (isSellInBelow(ZERO)) {
            setQualityToZero();
        }
    }

    private void increaseQuality() {
        if (isSellInBelow(ELEVEN) && hasQualityLessThan(FIFTY)) {

            increaseQualityByOne();

            if (isSellInBelow(SIX)) {
                increaseQualityByOne();
            }
        }
    }

    private void updateAgedBrie() {
        decreaseSellInByOne();

        if (isSellInBelow(ZERO) && hasQualityLessThan(FIFTY)) {
            increaseQualityByOne();
        }
    }

    private void setQualityToZero() {
        quality.setToZero();
    }

    private void decreaseSellInByOne() {
        sellIn.decreaseByOne();
    }

    private boolean hasQualityGreaterThan(int value) {
        return quality.above(value);
    }

    private void decreaseQualityByOne() {
        quality.decreaseByOne();
    }

    private boolean hasQualityLessThan(int value) {
        return quality.below(value);
    }

    private void increaseQualityByOne() {
        quality.increaseByOne();
    }

    private boolean isSellInBelow(int value) {
        return sellIn.below(value);
    }
}
