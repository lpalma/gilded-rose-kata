package com.codurance.gildedRose.qualityUpdater;

import com.codurance.gildedRose.ItemQuality;
import com.codurance.gildedRose.SellIn;

public class ConjuredItemQualityUpdater implements ItemQualityUpdater {
    public static final int QUALITY_THRESHOLD = 0;
    public static final int SELL_IN_THRESHOLD = 0;

    private ItemQuality quality;
    private SellIn sellIn;

    public ConjuredItemQualityUpdater(ItemQuality quality, SellIn sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }

    public ItemQuality update() {
        ItemQuality updatedQuality = new ItemQuality(quality.value());

        if (isQualityAboveThreshold(updatedQuality)) {
            updatedQuality.decrement();
            updatedQuality.decrement();
        }

        if (isSellInBelowThreshold() && isQualityAboveThreshold(updatedQuality)) {
            updatedQuality.decrement();
            updatedQuality.decrement();
        }

        return updatedQuality;
    }

    private boolean isQualityAboveThreshold(ItemQuality updatedQuality) {
        return updatedQuality.above(QUALITY_THRESHOLD);
    }

    private boolean isSellInBelowThreshold() {
        return sellIn.below(SELL_IN_THRESHOLD);
    }
}
