package kz.akbar.model;

public enum RangeRadius {
    LOCAL(1000),
    COUNTRY(3000),
    INTERNATIONAL(6000);

    private int range;

    RangeRadius(int range) {
        this.range = range;
    }

    public int getRange() {
        return range;
    }
}
