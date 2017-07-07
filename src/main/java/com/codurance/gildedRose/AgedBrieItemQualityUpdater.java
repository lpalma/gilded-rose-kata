package com.codurance.gildedRose;

public class AgedBrieItemQualityUpdater implements ItemQualityUpdater {
    public static final int QUALITY_THRESHOLD = 50;
    public static final int SELL_IN_THRESHOLD = 0;

    private ItemQuality quality;
    private SellIn sellIn;

    public AgedBrieItemQualityUpdater(ItemQuality quality, SellIn sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }


    public ItemQuality update() {
        ItemQuality updatedQuality = new ItemQuality(quality.value());

        if (hasQualityLessThan(QUALITY_THRESHOLD)) {
            updatedQuality.increaseByOne();
        }

        if (isSellInBelow(SELL_IN_THRESHOLD) && hasQualityLessThan(QUALITY_THRESHOLD)) {
            updatedQuality.increaseByOne();
        }

        return updatedQuality;
    }

    private boolean hasQualityLessThan(int value) {
        return quality.below(value);
    }

    private boolean isSellInBelow(int value) {
        return sellIn.below(value);
    }
}
