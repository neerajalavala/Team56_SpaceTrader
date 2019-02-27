package com.example.spacetrader.entity.commerce;

import com.example.spacetrader.entity.TechLevel;

import java.io.Serializable;

public enum MarketGoodType implements Serializable {
    WATER("Water", 30, 4, 3, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.MEDIEVAL),
    FURS("Furs", 250, 10, 10, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE),
    FOOD("Food", 100, 5, 5, TechLevel.AGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.AGRICULTURE),
    ORE("Ore", 350, 10, 20, TechLevel.MEDIEVAL, TechLevel.MEDIEVAL, TechLevel.RENAISSANCE),
    GAMES("Games", 250, 5, -10, TechLevel.RENAISSANCE, TechLevel.AGRICULTURE, TechLevel.POSTINDUSTRIAL),
    FIREARMS("Firearms", 1250, 100, -75, TechLevel.RENAISSANCE, TechLevel.AGRICULTURE, TechLevel.INDUSTRIAL),
    MEDICINE("Medicine", 650, 10, -20, TechLevel.EARLYINDUSTRIAL, TechLevel.AGRICULTURE, TechLevel.POSTINDUSTRIAL),
    MACHINES("Machines", 900, 5, -30, TechLevel.EARLYINDUSTRIAL, TechLevel.RENAISSANCE, TechLevel.INDUSTRIAL),
    NARCOTICS("Narcotics", 3500, 150, -125, TechLevel.INDUSTRIAL, TechLevel.PREAGRICULTURE, TechLevel.INDUSTRIAL),
    ROBOTS("Robots", 5000, 100, -150, TechLevel.POSTINDUSTRIAL, TechLevel.EARLYINDUSTRIAL, TechLevel.HITECH);

    private String name;
    private TechLevel minimumLevelToProduce;
    private TechLevel minimumLevelToUse;
    private TechLevel techTopProduction;
    private int increasePerLevel;
    private int basePrice;
    private int variance; // percent value from 0 to 1

    private MarketGoodType(String name, int bP, int var, int ipl, TechLevel mltp, TechLevel mltu, TechLevel ttp) {
        this.name = name;
        this.basePrice = bP;
        this.variance = var;
        this.minimumLevelToProduce = mltp;
        this.minimumLevelToUse = mltu;
        this.techTopProduction = ttp;
        this.increasePerLevel = ipl;
    }

    public TechLevel getMinimumLevelToProduce() {
        return minimumLevelToProduce;
    }

    public TechLevel getMinimumLevelToUse() {
        return minimumLevelToUse;
    }

    public TechLevel getTechTopProduction() {
        return techTopProduction;
    }

    public int getIncreasePerLevel() {
        return increasePerLevel;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getVariance() {
        return variance;
    }

    public String toString() {
        return this.name;
    }
}
