package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketPlace implements Serializable {
    private Map<String, MarketGood> marketGoods = new HashMap<>();

    public MarketPlace(TechLevel techLevel, Resources resources) {

        MarketGoodType[] goodTypes = MarketGoodType.values();
        for(int i = 0; i < goodTypes.length; i++){
            if (goodTypes[i].canBuy(techLevel)) {
                marketGoods.put(goodTypes[i].toString(), new MarketGood(goodTypes[i], true, techLevel, resources));
            } else {
                if (goodTypes[i].canSell(techLevel)) {
                    marketGoods.put(goodTypes[i].toString(), new MarketGood(goodTypes[i], false, techLevel, resources));
                }
            }
        }
    }

    public void removeGoods(String goodName, int quantity) throws PurchaseException{
        if (!marketGoods.containsKey(goodName)) {
            throw new PurchaseException("Invalid good name: " + goodName + "!");
        }
        marketGoods.computeIfPresent(goodName, (k, v) -> {
            v.subQuantity(quantity);
            return v;
        });
    }

    public ArrayList<MarketGood> getBuyableGoods() {
        ArrayList<MarketGood> buyList = new ArrayList<>();
        for (MarketGood m : marketGoods.values()) {
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
        for (MarketGood m : marketGoods.values()) {
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
}
