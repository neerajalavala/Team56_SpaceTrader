package com.example.spacetrader.entity.universe;

public enum TechnologyLevel {
    PRE_AGR("Pre-Agriculture"),
    AGR("Agriculture"),
    MEDV("Medieval"),
    RENS("Renaissance"),
    EAR_IND("Early Industrial"),
    IND("Industrial"),
    PST_IND("Post-Industrial"),
    H_TECH("Hi-Tech");

    private String label;
    TechnologyLevel(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
