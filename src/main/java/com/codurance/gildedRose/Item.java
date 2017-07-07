package com.codurance.gildedRose;

import com.codurance.gildedRose.qualityUpdater.*;

public class Item {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    private ItemName name;

    private SellIn sellIn;

    private ItemQuality quality;

    public Item(String name, int sellIn, int quality) {
        this.name = new ItemName(name);
        this.sellIn = new SellIn(sellIn);
        this.quality = new ItemQuality(quality);
    }

   @Override
   public String toString() {
        return name.value()
                + ", "
                + sellIn.value()
                + ", "
                + quality.value();
    }

    public void update() {
        decreaseSellIn();

        ItemQualityUpdater qualityUpdater = makeItemQualityUpdater();

        quality = qualityUpdater.update();
    }

    private ItemQualityUpdater makeItemQualityUpdater() {
        if (isAgedBrie()) {
            return new AgedBrieItemQualityUpdater(quality, sellIn);
        }

        if (isBackstagePasses()) {
            return new BackstagePassesItemQualityUpdater(quality, sellIn);
        }

        if (isSulfurasHandOfRagnaros()) {
            return new SulfurasHandOfRagnarosItemQualityUpdater(quality, sellIn);
        }

        return new RegularItemQualityUpdater(quality, sellIn);
    }

    private boolean hasName(String name) {
        return this.name.is(name);
    }

    private void decreaseSellIn() {
        if (!isSulfurasHandOfRagnaros()) {
            sellIn.decreaseByOne();
        }
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
}
