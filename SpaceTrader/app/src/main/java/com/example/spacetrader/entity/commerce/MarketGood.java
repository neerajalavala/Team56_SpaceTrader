package com.example.spacetrader.entity.commerce;

import com.example.spacetrader.entity.world.TechLevel;

import java.io.Serializable;

public class MarketGood implements Serializable {
    private int quantity;
    private MarketGoodType marketGoodType;
    private TechLevel techLevel;

    public MarketGood(MarketGoodType mGT, boolean genQ, TechLevel tL) {
        this.marketGoodType = mGT;
        this.techLevel = tL;
        if (!genQ) quantity = 0;
        else {
            // corey to provide quantity algorithm
        }
    }

    public MarketGood(MarketGoodType mGT) {
        this(mGT, false, null);
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
        return 0;
        // corey to provide pricing algorithm
    }
}
