package com.example.spacetrader.entity.commerce;

import com.example.spacetrader.entity.world.Resources;
import com.example.spacetrader.entity.world.TechLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarketPlace implements Serializable {
    private ArrayList<MarketGood> marketGoods = new ArrayList<>();

    private TechLevel techLevel;
    private Resources resources;

    private final int playerID;

    private final MarketGoodType[] goodTypes = MarketGoodType.values();

    public MarketPlace(TechLevel techLevel, Resources resources, int id) {
        this.techLevel = techLevel;
        this.resources = resources;
        this.playerID = id;

        for(int i = 0; i < goodTypes.length; i++){
            if (goodTypes[i].canBuy(this.techLevel)) {
                marketGoods.add(new MarketGood(goodTypes[i], true, this.techLevel, this.resources, this.playerID));
            } else {
                if (goodTypes[i].canSell(this.techLevel)) {
                    marketGoods.add(new MarketGood(goodTypes[i], false, this.techLevel, this.resources, this.playerID));
                }
            }
        }
    }

    public List<MarketGood> getAllGoods() {
        return marketGoods;
    }

    public List<MarketGood> getBuyableGoods() {
        List<MarketGood> buyList = new ArrayList<>();
        for (MarketGood m : marketGoods) {
            if (m.isBuyable() && m.getQuantity() != 0){
                if (m.getPrice_count() % 2 == 0) {
                    m.setPrice();
                }
                buyList.add(m);
            }
        }
        return buyList;
    }

    public List<MarketGood> getSellableGoods() {
        List<MarketGood> sellList = new ArrayList<>();
        for (MarketGood m : marketGoods) {
            if (m.isSellable()) sellList.add(m);
        }
        return sellList;
    }

    public int getPrice(MarketGood marketGood) {
        if (marketGood.getPrice_count() % 2 == 0) {
            marketGood.setPrice();
        }
        return marketGood.getPrice();
    }

    public int getPlayerID() {
        return playerID;
    }
}
