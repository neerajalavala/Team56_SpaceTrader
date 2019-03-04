package com.example.spacetrader.entity.commerce;

import com.example.spacetrader.R;
import com.example.spacetrader.entity.world.Resources;
import com.example.spacetrader.entity.world.TechLevel;

import java.io.Serializable;
import java.util.Random;

public class MarketGood implements Serializable {
    private int quantity;

    private MarketGoodType marketGoodType;

    private TechLevel techLevel;
    private Resources resources;

    private final MarketGoodType[] goodTypes = MarketGoodType.values();


    public MarketGood(MarketGoodType mGT, boolean genQ, TechLevel tL, Resources resources) {
        this.marketGoodType = mGT;
        this.techLevel = tL;
        this.resources = resources;
        if (!genQ) quantity = 0;
        else {
            Random random = new Random();
            int baseQ = random.nextInt(11) + 20;
            int topProduction = marketGoodType.getTechTopProduction().index();
            if (topProduction == techLevel.index()) {
                quantity = (int)(baseQ * 1.5);
                quantity += random.nextInt(3);
            } else {
                int ratio = 7 - Math.abs(techLevel.index() - topProduction);
                quantity = (baseQ * ratio) / 7;
                quantity += random.nextInt(3);
            }
        }
    }

    public MarketGood(MarketGoodType mGT) {
        this(mGT, false, null, null);
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int q) {
        quantity += q;
    }

    public void subQuantity(int q) {
        quantity -= q;
    }

    public MarketGoodType getType() {
        return marketGoodType;
    }

    public boolean isBuyable() {
        return marketGoodType.canBuy(marketGoodType, techLevel);
    }

    public boolean isSellable() {
        return marketGoodType.canSell(marketGoodType, techLevel);
    }

    public int getPrice() {
<<<<<<< Updated upstream
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

        return price;
=======

        // corey to provide pricing algorithm
        Random random = new Random();
        int basePrice = marketGoodType.getBasePrice();
        int iPL = marketGoodType.getIncreasePerLevel();
        int tL = techLevel.index();
        int mLTP = marketGoodType.getMinimumLevelToProduce().index();
        int vary = random.nextInt(marketGoodType.getVariance() + 1);
        if (random.nextBoolean() == true) {
            vary *= -1;
        }
        return basePrice + (iPL * (tL - mLTP)) + (basePrice * vary);
>>>>>>> Stashed changes
    }
}
