package com.codurance.gildedRose;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final int ZERO = 0;
    public static final int FIFTY = 50;
    public static final int ELEVEN = 11;
    public static final int SIX = 6;

    private String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    private boolean hasName(String name) {
        return this.name.equals(name);
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public String name() {
        return name;
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
        if (isAgedBrie()) {
            updateAgedBrie();
            return;
        }

        if (isBackstagePasses()) {
            updateBackstagePasses();
            return;
        }

        updateSulfurasHandOfRagnaros();
    }

    private void updateOtherItems() {
        if (hasQualityGreaterThan(ZERO)) {
            decreaseQualityByOne();
        }

        decreaseSellInByOne();

        if (isSellInBelow(ZERO) && hasQualityGreaterThan(ZERO)) {
            decreaseQualityByOne();
        }
    }

    private void updateSulfurasHandOfRagnaros() {
        if (hasQualityLessThan(FIFTY)) {
            increaseQualityByOne();
        }
    }

    private void updateBackstagePasses() {
        if (hasQualityLessThan(FIFTY)) {
            increaseQualityByOne();
        }

        if (isSellInBelow(ELEVEN) && hasQualityLessThan(FIFTY)) {

            increaseQualityByOne();

            if (isSellInBelow(SIX) && isSellInBelow(FIFTY)) {
                increaseQualityByOne();
            }
        }

        decreaseSellInByOne();

        if (isSellInBelow(ZERO)) {
            updateQualityForBackstagePasses();
        }
    }

    private void updateAgedBrie() {
        if (hasQualityLessThan(FIFTY)) {
            increaseQualityByOne();
        }

        decreaseSellInByOne();

        if (isSellInBelow(ZERO)) {
            updateQualityForAgedBrie();
        }
    }

    private void updateQualityForBackstagePasses() {
        quality = 0;
    }

    private void updateQualityForAgedBrie() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    private void decreaseSellInByOne() {
        sellIn = sellIn - 1;
    }

    private boolean hasQualityGreaterThan(int value) {
        return quality > value;
    }

    private void decreaseQualityByOne() {
        --quality;
    }

    private boolean hasQualityLessThan(int value) {
        return quality < value;
    }

    private void increaseQualityByOne() {
        ++quality;
    }

    private boolean isSellInBelow(int value) {
        return sellIn < value;
    }
}
