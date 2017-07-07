package com.codurance.gildedRose;

public class RegularItemQualityUpdater implements ItemQualityUpdater {
    public static final int QUALITY_THRESHOLD = 0;
    public static final int SELL_IN_THRESHOLD = 0;

    private ItemQuality quality;
    private SellIn sellIn;

    public RegularItemQualityUpdater(ItemQuality quality, SellIn sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }

    public ItemQuality update() {
        ItemQuality updatedQuality = new ItemQuality(quality.value());

        if (isQualityAboveThreshold(updatedQuality)) {
            updatedQuality.decreaseByOne();
        }

        if (isSellInBelow(SELL_IN_THRESHOLD) && isQualityAboveThreshold(updatedQuality)) {
            updatedQuality.decreaseByOne();
        }

        return updatedQuality;
    }

    private boolean isQualityAboveThreshold(ItemQuality updatedQuality) {
        return updatedQuality.above(QUALITY_THRESHOLD);
    }

    private boolean isSellInBelow(int value) {
        return sellIn.below(value);
    }
}
