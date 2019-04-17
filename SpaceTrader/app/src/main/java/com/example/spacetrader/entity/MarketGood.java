package com.example.spacetrader.entity;

import java.io.Serializable;
import java.util.Random;


/**
 * Represents a MarketGood
 */
public class MarketGood implements Serializable {
    private int quantity;

    private final MarketGoodType marketGoodType;

    private final TechLevel techLevel;
    private final Resources resources;

    private int price_count;

    private int price;


    /**
     * Creates a MarketGood
     *
     * @param mGT specified good type
     * @param genQ boolean determining whether the marketgood is generated
     * @param tL tech level of marketgood
     * @param resources resources affecting marketgood
     */
    public MarketGood(MarketGoodType mGT, boolean genQ, TechLevel tL, Resources resources) {
        this.marketGoodType = mGT;
        this.techLevel = tL;
        this.resources = resources;

        // quantity calculation
        if (!genQ) {
            this.quantity = 0;
        } else {
            int topTechLevMultiplier;

            if (this.marketGoodType.getTechTopProduction().index() == this.techLevel.index()){
                topTechLevMultiplier = 5;
            } else {
                topTechLevMultiplier = 1;
            }

            this.quantity = (new Random().nextInt(7))
                        * (topTechLevMultiplier)
                            * ((1 + techLevel.index())
                    - marketGoodType.getMinimumLevelToProduce().index());
        }
        setPrice();
//        /** start price calculation */
//
//        double cheapResMultiply = 1.0;
//        int expResMultiply = 1;
//
//        int varCoinFlip = -1;
//
//        if (new Random().nextInt() > 0) {
//            varCoinFlip = 1;
//        }
//
//        double variance = ((double) new Random().nextInt(marketGoodType.getVariance()) / 100.0);
//
//        if (marketGoodType.getCheapResource().index() == resources.index()) {
//            cheapResMultiply = 0.5;
//        } else if (marketGoodType.getExpensiveResource().index() == resources.index()) {
//            expResMultiply = 3;
//        }
//
//        int price_calc = ((int)(marketGoodType.getBasePrice() * cheapResMultiply *
// expResMultiply))
//                + (marketGoodType.getIncreasePerLevel() * (techLevel.index() -
// marketGoodType.getMinimumLevelToProduce().index()))
//                + (int)(varCoinFlip * variance * marketGoodType.getBasePrice());
//
//        if (price_calc <= 0) {
//            price_calc = marketGoodType.getBasePrice() / 2;
//        }
//        this.price = price_calc;
//        this.price_count = 1;

    }

    /**
     * Creates a marketgood
     *
     * @param mGT type of marketgood
     * @param id id of marketgood
     */
    public MarketGood(MarketGoodType mGT, int id) {
        this(mGT, false, TechLevel.NONE, Resources.NONE);
        this.price = mGT.getBasePrice();
        this.price_count = 0;
    }

    /**
     * Gets quantity of marketgood
     *
     * @return quantity of marketgood
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Adds to quantity of marketgood
     *
     * @param q amount to be added
     */
    public void addQuantity(int q) {
        quantity += q;
    }

    /**
     * Subtracts from quantity of marketgood
     *
     * @param q amount to be subtracted
     */
    public void subQuantity(int q) {
        this.quantity -= q;
    }

    /**
     * Gets type of marketgood
     *
     * @return type of marketgood
     */
    public MarketGoodType getType() {
        return marketGoodType;
    }

    /**
     * Gets tech level of marketgood
     *
     * @return tech level of marketgood
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Gets resources of marketgood
     *
     * @return resources of marketgood
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * Checks if marketgood is buyable based on tech level
     *
     * @return if marketgood is buyable
     */
    public boolean isBuyable() { return marketGoodType.canBuy(techLevel); }

    /**
     * Checks if marketgood is sellable based on tech level
     *
     * @return if marketgood is sellable
     */
    public boolean isSellable() {
        return marketGoodType.canSell(techLevel);
    }

    /**
     *
     * @param t tech level to sell at
     * @return true if can sell at tech level, else false
     */
    public boolean canSell(TechLevel t) {
        return marketGoodType.canSell(t) && quantity > 0;
    }

    /**
     * Sets price of marketgood
     */
    private void setPrice() {
        double cheapResMultiply = 1.0;
        int expResMultiply = 1;

        int varCoinFlip = -1;

        if (new Random().nextInt() > 0) {
            varCoinFlip = 1;
        }

        double variance = ((double) new Random().nextInt(marketGoodType.getVariance()) / 100.0);

        if (marketGoodType.getCheapResource().index() == resources.index()) {
            cheapResMultiply = 0.5;
        } else if (marketGoodType.getExpensiveResource().index() == resources.index()) {
                expResMultiply = 3;
        }

        int price = ((int)(marketGoodType.getBasePrice() * cheapResMultiply * expResMultiply))
                        + (marketGoodType.getIncreasePerLevel() * (techLevel.index()
                - marketGoodType.getMinimumLevelToProduce().index()))
                            + (int)(varCoinFlip * variance * marketGoodType.getBasePrice());

        if (price <= 0) {
            price = marketGoodType.getBasePrice() / 2;
        }

        this.price = price;
    }

    /**
     * Gets price count of marketgood
     *
     * @return price count of marketgood
     */
    public int getPrice_count() {
        return price_count;
    }

    /**
     * Gets base price of marketgood
     *
     * @return base price of marketgood
     */
    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return marketGoodType.toString();
    }
}
