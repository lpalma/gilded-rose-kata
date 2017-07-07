package com.codurance.gildedRose.qualityUpdater;

import com.codurance.gildedRose.ItemQuality;
import com.codurance.gildedRose.SellIn;
import com.codurance.gildedRose.qualityUpdater.ItemQualityUpdater;

public class SulfurasHandOfRagnarosItemQualityUpdater implements ItemQualityUpdater {
    public static final int QUALITY_THRESHOLD = 50;
    private ItemQuality quality;
    private SellIn sellIn;

    public SulfurasHandOfRagnarosItemQualityUpdater(ItemQuality quality, SellIn sellIn) {
        this.quality = quality;
        this.sellIn = sellIn;
    }

    public ItemQuality update() {
        ItemQuality updatedQuality = new ItemQuality(quality.value());

        if (isQualityBelowThreshold()) {
            updatedQuality.increment();
        }

        return updatedQuality;
    }

    private boolean isQualityBelowThreshold() {
        return quality.below(QUALITY_THRESHOLD);
    }
}
