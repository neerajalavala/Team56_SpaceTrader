package com.example.spacetrader.entity;

import android.support.annotation.NonNull;

public enum MarketGoodType {
    WATER("Water", 30, 4, 3, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.MEDIEVAL, 0, Resources.LOTSOFWATER, Resources.DESERT),
    FURS("Furs", 250, 10, 10, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE, 1, Resources.RICHFAUNA, Resources.LIFELESS),
    FOOD("Food", 100, 5, 5, TechLevel.AGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.AGRICULTURE, 2, Resources.RICHSOIL, Resources.POORSOIL),
    ORE("Ore", 350, 10, 20, TechLevel.MEDIEVAL, TechLevel.MEDIEVAL, TechLevel.RENAISSANCE, 3, Resources.MINERALRICH, Resources.MINERALPOOR),
    GAMES("Games", 250, 5, -10, TechLevel.RENAISSANCE, TechLevel.AGRICULTURE, TechLevel.POSTINDUSTRIAL, 4, Resources.ARTISTIC, null),
    FIREARMS("Firearms", 1250, 100, -75, TechLevel.RENAISSANCE, TechLevel.AGRICULTURE, TechLevel.INDUSTRIAL, 5, Resources.WARLIKE, null),
    MEDICINE("Medicine", 650, 10, -20, TechLevel.EARLYINDUSTRIAL, TechLevel.AGRICULTURE, TechLevel.POSTINDUSTRIAL, 6, Resources.LOTSOFHERBS, null),
    MACHINES("Machines", 900, 5, -30, TechLevel.EARLYINDUSTRIAL, TechLevel.RENAISSANCE, TechLevel.INDUSTRIAL, 7, null, null),
    NARCOTICS("Narcotics", 3500, 150, -125, TechLevel.INDUSTRIAL, TechLevel.PREAGRICULTURE, TechLevel.INDUSTRIAL, 8, Resources.WEIRDMUSHROOMS, null),
    ROBOTS("Robots", 5000, 100, -150, TechLevel.POSTINDUSTRIAL, TechLevel.EARLYINDUSTRIAL, TechLevel.HITECH, 9, null, null);

    private String name;
    private TechLevel minimumLevelToProduce;
    private TechLevel minimumLevelToUse;
    private TechLevel techTopProduction;

    private Resources cheapResource;
    private Resources expensiveResource;

    private int increasePerLevel;
    private int basePrice;
    private int variance;
    private int index;


    MarketGoodType(String name, int bP, int var, int ipl, TechLevel mltp, TechLevel mltu,
                   TechLevel ttp, int i, Resources cr, Resources er) {
        this.name = name;
        this.basePrice = bP;
        this.variance = var;
        this.minimumLevelToProduce = mltp;
        this.minimumLevelToUse = mltu;
        this.techTopProduction = ttp;
        this.increasePerLevel = ipl;
        this.index = i;

        this.cheapResource = cr;
        this.expensiveResource = er;
    }

    public boolean canBuy(TechLevel planetTech) {
        return this.minimumLevelToProduce.index() <= planetTech.index();
    }

    public boolean canSell(TechLevel planetTech) {
        return this.minimumLevelToUse.index() <= planetTech.index();
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

    public Resources getCheapResource(){
        if (cheapResource != null) {
            return cheapResource;
        }
        return Resources.NONE;
    }

    public Resources getExpensiveResource() {
        if (expensiveResource != null){
            return expensiveResource;
        }
        return Resources.NONE;
    }

    public int index() {
        return index;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
