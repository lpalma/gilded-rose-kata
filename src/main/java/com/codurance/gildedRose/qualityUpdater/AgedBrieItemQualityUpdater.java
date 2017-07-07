package com.codurance.gildedRose.qualityUpdater;

import com.codurance.gildedRose.ItemQuality;
import com.codurance.gildedRose.SellIn;
import com.codurance.gildedRose.qualityUpdater.ItemQualityUpdater;

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

        if (isQualityBelowThreshold()) {
            updatedQuality.increment();
        }

        if (isSellInBelowThreshold() && isQualityBelowThreshold()) {
            updatedQuality.increment();
        }

        return updatedQuality;
    }

    private boolean isQualityBelowThreshold() {
        return quality.below(QUALITY_THRESHOLD);
    }

    private boolean isSellInBelowThreshold() {
        return sellIn.below(SELL_IN_THRESHOLD);
    }
}
