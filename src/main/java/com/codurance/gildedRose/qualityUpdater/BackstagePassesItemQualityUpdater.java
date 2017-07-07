package com.codurance.gildedRose.qualityUpdater;

import com.codurance.gildedRose.ItemQuality;
import com.codurance.gildedRose.SellIn;
import com.codurance.gildedRose.qualityUpdater.ItemQualityUpdater;

public class BackstagePassesItemQualityUpdater implements ItemQualityUpdater {
    public static final int QUALITY_THRESHOLD = 50;

    private ItemQuality quality;
    private SellIn sellIn;

    public BackstagePassesItemQualityUpdater(ItemQuality quality, SellIn sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }


    public ItemQuality update() {
        ItemQuality updatedQuality = new ItemQuality(quality.value());

        if (isSellInBelow(0)) {
            updatedQuality.setToZero();

            return updatedQuality;
        }

        increaseQuality(updatedQuality);

        return updatedQuality;
    }

    private void increaseQuality(ItemQuality updatedQuality) {
        if (isQualityBelowThreshold(updatedQuality)) {
            updatedQuality.increment();
        }

        if (isSellInBelow(10) && isQualityBelowThreshold(updatedQuality)) {
            updatedQuality.increment();
        }

        if (isSellInBelow(5) && isQualityBelowThreshold(updatedQuality)) {
            updatedQuality.increment();
        }
    }


    private boolean isQualityBelowThreshold(ItemQuality updatedQuality) {
        return updatedQuality.below(QUALITY_THRESHOLD);
    }

    private boolean isSellInBelow(int value) {
        return sellIn.below(value);
    }
}