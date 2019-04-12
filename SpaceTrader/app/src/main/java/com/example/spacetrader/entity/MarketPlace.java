package com.example.spacetrader.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarketPlace implements Serializable {
    private ArrayList<MarketGood> marketGoods = new ArrayList<>();

    private TechLevel techLevel;
    private Resources resources;

    private final MarketGoodType[] goodTypes = MarketGoodType.values();

    public MarketPlace(TechLevel techLevel, Resources resources) {
        this.techLevel = techLevel;
        this.resources = resources;

        for(int i = 0; i < goodTypes.length; i++){
            if (goodTypes[i].canBuy(this.techLevel)) {
                marketGoods.add(new MarketGood(goodTypes[i], true, this.techLevel, this.resources));
            } else {
                if (goodTypes[i].canSell(this.techLevel)) {
                    marketGoods.add(new MarketGood(goodTypes[i], false, this.techLevel, this.resources));
                }
            }
        }
    }

    public List<MarketGood> getAllGoods() {
        return marketGoods;
    }

    public ArrayList<MarketGood> getBuyableGoods() {
        ArrayList<MarketGood> buyList = new ArrayList<>();
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

    public void updateGoodQuantity(MarketGood good, int quan) {
        ArrayList<MarketGood> goods = this.marketGoods;

        for (MarketGood m : goods) {
            if (good.getType().equals(m.getType())) {
                System.out.println("before " + m.getQuantity());

                System.out.println("subtracting " + quan + " " + m.getType().toString());

                m.subQuantity(quan);

                System.out.println("after " + m.getQuantity());
            };
        }
        this.marketGoods = goods;
    }
}
