package com.example.spacetrader.entity.commerce;

import java.io.Serializable;

public class MarketGood implements Serializable {
    private int quantity;
    private MarketGoodType marketGoodType;

    public MarketGood(MarketGoodType mGT) {
        this.marketGoodType = mGT;

        // how to determine quantity???
    }

    public int getPrice() {
        return 0;
        // implement when corey has the pricing algorithm
    }
}
