package com.example.spacetrader.entity;

import java.io.Serializable;
import java.util.Random;

public class MarketGood implements Serializable {
    private int quantity;

    private MarketGoodType marketGoodType;

    private TechLevel techLevel;
    private Resources resources;

    private int price_count;

    private int price;


    public MarketGood(MarketGoodType mGT, boolean genQ, TechLevel tL, Resources resources) {
        this.marketGoodType = mGT;
        this.techLevel = tL;
        this.resources = resources;

        /** quantity calculation */
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
                            * ((1 + techLevel.index()) - marketGoodType.getMinimumLevelToProduce().index());
        }
        setPrice();
//        /** start price calculation */
//
//        double cheapResMult = 1.0;
//        int expResMult = 1;
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
//            cheapResMult = 0.5;
//        } else if (marketGoodType.getExpensiveResource().index() == resources.index()) {
//            expResMult = 3;
//        }
//
//        int price_calc = ((int)(marketGoodType.getBasePrice() * cheapResMult * expResMult))
//                + (marketGoodType.getIncreasePerLevel() * (techLevel.index() - marketGoodType.getMinimumLevelToProduce().index()))
//                + (int)(varCoinFlip * variance * marketGoodType.getBasePrice());
//
//        if (price_calc <= 0) {
//            price_calc = marketGoodType.getBasePrice() / 2;
//        }
//        this.price = price_calc;
//        this.price_count = 1;

    }

    public MarketGood(MarketGoodType mGT, int id) {
        this(mGT, false, TechLevel.NONE, Resources.NONE);
        this.price = mGT.getBasePrice();
        this.price_count = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int q) {
        quantity += q;
    }

    public void subQuantity(int q) {
        this.quantity -= q;
    }

    public MarketGoodType getType() {
        return marketGoodType;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public Resources getResources() {
        return resources;
    }

    public boolean isBuyable() { return marketGoodType.canBuy(techLevel); }

    public boolean isSellable() {
        return marketGoodType.canSell(techLevel);
    }

    public boolean canSell(TechLevel t) {
        return marketGoodType.canSell(t) && quantity > 0;
    }

    public void setPrice() {
        double cheapResMult = 1.0;
        int expResMult = 1;

        int varCoinFlip = -1;

        if (new Random().nextInt() > 0) {
            varCoinFlip = 1;
        }

        double variance = ((double) new Random().nextInt(marketGoodType.getVariance()) / 100.0);

        if (marketGoodType.getCheapResource().index() == resources.index()) {
            cheapResMult = 0.5;
        } else if (marketGoodType.getExpensiveResource().index() == resources.index()) {
                expResMult = 3;
        }

        int price = ((int)(marketGoodType.getBasePrice() * cheapResMult * expResMult))
                        + (marketGoodType.getIncreasePerLevel() * (techLevel.index() - marketGoodType.getMinimumLevelToProduce().index()))
                            + (int)(varCoinFlip * variance * marketGoodType.getBasePrice());

        if (price <= 0) {
            price = marketGoodType.getBasePrice() / 2;
        }

        this.price = price;
    }

    public int getPrice_count() {
        return price_count;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return marketGoodType.toString();
    }
}
