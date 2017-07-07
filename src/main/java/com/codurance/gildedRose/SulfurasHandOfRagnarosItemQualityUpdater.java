package com.codurance.gildedRose;

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

        if (hasQualityLessThan(QUALITY_THRESHOLD)) {
            updatedQuality.increaseByOne();
        }

        return updatedQuality;
    }

    private boolean hasQualityLessThan(int value) {
        return quality.below(value);
    }
}
