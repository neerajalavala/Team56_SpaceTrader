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
            // generate quantities when???
        }
    }

    public MarketGood(MarketGoodType mGT) {
        this(mGT, false, null);
    }

    public int getQuantity() {
        return quantity;
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
        // implement when corey has the pricing algorithm
    }
}
