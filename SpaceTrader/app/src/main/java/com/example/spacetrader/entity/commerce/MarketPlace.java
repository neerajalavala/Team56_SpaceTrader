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

    public MarketPlace(TechLevel techLevel, Resources resources) {
        this.techLevel = techLevel;
        this.resources = resources;

        // corey to provide generation algorithm
    }

    public List<MarketGood> getAllGoods() {
        return marketGoods;
    }

    public List<MarketGood> getBuyableGoods() {
        List<MarketGood> buyList = new ArrayList<>();
        for (MarketGood m : marketGoods) {
            if (m.isBuyable()) buyList.add(m);
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
        return marketGood.getPrice();
    }
}
