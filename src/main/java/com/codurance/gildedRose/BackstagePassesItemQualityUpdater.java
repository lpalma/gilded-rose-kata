package com.codurance.gildedRose;

public class BackstagePassesItemQualityUpdater implements ItemQualityUpdater {
    public static final int QUALITY_THRESHOLD = 50;
    public static final int SELL_IN_THRESHOLD = 0;

    private ItemQuality quality;
    private SellIn sellIn;

    public BackstagePassesItemQualityUpdater(ItemQuality quality, SellIn sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }


    public ItemQuality update() {
        ItemQuality updatedQuality = new ItemQuality(quality.value());

        if (isQualityBelowThreshold(updatedQuality)) {
            updatedQuality.increaseByOne();
        }

        updateBackstagePasses(updatedQuality);

        return updatedQuality;
    }

    private void updateBackstagePasses(ItemQuality updatedQuality) {
        if (isSellInBelow(SELL_IN_THRESHOLD)) {
            updatedQuality.setToZero();
            return;
        }

        increaseQuality(updatedQuality);
    }

    private void increaseQuality(ItemQuality updatedQuality) {
        if (!isQualityBelowThreshold(updatedQuality)) {
            return;
        }

        if (isSellInBelow(10)) {
            updatedQuality.increaseByOne();
        }

        if (isSellInBelow(5)) {
            updatedQuality.increaseByOne();
        }
    }


    private boolean isQualityBelowThreshold(ItemQuality updatedQuality) {
        return updatedQuality.below(QUALITY_THRESHOLD);
    }

    private boolean isSellInBelow(int value) {
        return sellIn.below(value);
    }
}