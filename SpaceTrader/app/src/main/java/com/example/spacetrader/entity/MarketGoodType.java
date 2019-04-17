package com.example.spacetrader.entity;

import android.support.annotation.NonNull;

/**
 * Documents possible types of marketgoods
 */
public enum MarketGoodType {
    WATER("Water", 30, 4, 3, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE, TechLevel.MEDIEVAL,
            0, Resources.LOTSOFWATER, Resources.DESERT),
    FURS("Furs", 250, 10, 10, TechLevel.PREAGRICULTURE, TechLevel.PREAGRICULTURE,
            TechLevel.PREAGRICULTURE, 1, Resources.RICHFAUNA, Resources.LIFELESS),
    FOOD("Food", 100, 5, 5, TechLevel.AGRICULTURE, TechLevel.PREAGRICULTURE,
            TechLevel.AGRICULTURE, 2, Resources.RICHSOIL, Resources.POORSOIL),
    ORE("Ore", 350, 10, 20, TechLevel.MEDIEVAL, TechLevel.MEDIEVAL, TechLevel.RENAISSANCE, 3,
            Resources.MINERALRICH, Resources.MINERALPOOR),
    GAMES("Games", 250, 5, -10, TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
            TechLevel.POSTINDUSTRIAL, 4, Resources.ARTISTIC, null),
    FIREARMS("Firearms", 1250, 100, -75, TechLevel.RENAISSANCE, TechLevel.AGRICULTURE,
            TechLevel.INDUSTRIAL, 5, Resources.WARLIKE, null),
    MEDICINE("Medicine", 650, 10, -20, TechLevel.EARLYINDUSTRIAL, TechLevel.AGRICULTURE,
            TechLevel.POSTINDUSTRIAL, 6, Resources.LOTSOFHERBS, null),
    MACHINES("Machines", 900, 5, -30, TechLevel.EARLYINDUSTRIAL, TechLevel.RENAISSANCE,
            TechLevel.INDUSTRIAL, 7, null, null),
    NARCOTICS("Narcotics", 3500, 150, -125, TechLevel.INDUSTRIAL, TechLevel.PREAGRICULTURE,
            TechLevel.INDUSTRIAL, 8, Resources.WEIRDMUSHROOMS, null),
    ROBOTS("Robots", 5000, 100, -150, TechLevel.POSTINDUSTRIAL, TechLevel.EARLYINDUSTRIAL,
            TechLevel.HITECH, 9, null, null);

    private final String name;
    private final TechLevel minimumLevelToProduce;
    private final TechLevel minimumLevelToUse;
    private final TechLevel techTopProduction;

    private final Resources cheapResource;
    private final Resources expensiveResource;

    private final int increasePerLevel;
    private final int basePrice;
    private final int variance;
    private final int index;


    MarketGoodType(String name, int bP, int var, int ipl, TechLevel multiply, TechLevel multiple,
                   TechLevel ttp, int i, Resources cr, Resources er) {
        this.name = name;
        this.basePrice = bP;
        this.variance = var;
        this.minimumLevelToProduce = multiply;
        this.minimumLevelToUse = multiple;
        this.techTopProduction = ttp;
        this.increasePerLevel = ipl;
        this.index = i;

        this.cheapResource = cr;
        this.expensiveResource = er;
    }

    /**
     * Checks if can buy a type based on tech level
     *
     * @param planetTech current planet tech level
     * @return true if can buy at tech level, else false
     */
    public boolean canBuy(TechLevel planetTech) {
        return this.minimumLevelToProduce.index() <= planetTech.index();
    }

    /**
     * Checks if can sell a type based on tech level
     *
     * @param planetTech current planet tech level
     * @return true if can sell at tech level, else false
     */
    public boolean canSell(TechLevel planetTech) {
        return this.minimumLevelToUse.index() <= planetTech.index();
    }

    /**
     * Gets min tech level required to produce a type
     *
     * @return min tech level needed
     */
    public TechLevel getMinimumLevelToProduce() {
        return minimumLevelToProduce;
    }

    /**
     * Gets min tech level required to use a type
     *
     * @return min tech level needed
     */
    public TechLevel getMinimumLevelToUse() {
        return minimumLevelToUse;
    }

    /**
     * Gets tech level at which type has highest production
     *
     * @return tech level for highest production of type
     */
    public TechLevel getTechTopProduction() {
        return techTopProduction;
    }

    /**
     * Gets increase in price per tech level
     *
     * @return amount of increase
     */
    public int getIncreasePerLevel() {
        return increasePerLevel;
    }

    /**
     * Gets base price of a type
     *
     * @return base price of type
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * Gets variance of type price
     *
     * @return variance of type price
     */
    public int getVariance() {
        return variance;
    }

    /**
     * Gets resource needed to make a type cheap
     *
     * @return resource necessary
     */
    public Resources getCheapResource(){
        if (cheapResource != null) {
            return cheapResource;
        }
        return Resources.NONE;
    }

    /**
     * Gets resource needed to make a type expensive
     *
     * @return respirce mecessary
     */
    public Resources getExpensiveResource() {
        if (expensiveResource != null){
            return expensiveResource;
        }
        return Resources.NONE;
    }

    /**
     * Returns index of a type
     *
     * @return index of a type
     */
    public int index() {
        return index;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
