package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CargoHold implements Serializable {
    private Integer count;

    private final int playerID;

    private Integer capacity;

    private Map<String, MarketGood> hold = new HashMap<>();

    public CargoHold(int cap, int id) {
        MarketGoodType[] types = MarketGoodType.values();
        this.capacity = cap;
        this.playerID = id;

        for (MarketGoodType t : types) {
            hold.put(t.toString(), new MarketGood(t, playerID));
        }
        count = 0;
    }

    public void addGoods(String goodName, int quantity) throws PurchaseException {
        if (count + quantity > capacity) {
            throw new PurchaseException("No room in hold!");
        }
        if (!hold.containsKey(goodName)) {
            throw new PurchaseException("Illegal good name: " + goodName + "!");
        }
        hold.computeIfPresent(goodName, (k, v) -> {
            v.addQuantity(quantity);
            return v;
        });
        count += quantity;
    }

    public void removeGoods(String goodName, int quantity) throws PurchaseException {
        if (count - quantity < 0) {
            throw new PurchaseException("Cannot sell " + quantity + " goods with only " + count + " in the hold!");
        }
        if (!hold.containsKey(goodName)) {
            throw new PurchaseException("Illegal good name: " + goodName + "!");
        }
        hold.computeIfPresent(goodName, (k, v) -> {
            v.subQuantity(quantity);
            return v;
        });
        count -= quantity;
    }

    public ArrayList<MarketGood> getSellableGoods(TechLevel lev) {
        ArrayList<MarketGood> sellList = new ArrayList<>();
        for (MarketGood m : hold.values()) {
            if (m.canSell(lev)){
//                if (m.getPrice_count() % 2 == 0) {
//                    m.setPrice();
//                }
                sellList.add(m);
            }
        }
        return sellList;
    }

    public boolean isFull() {
        return count >= capacity;
    }

    public int getPlayerID() {
        return playerID;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getCount() {
        return count;
    }

    public String toString() {
        return "Hold: " + count + "/" + capacity;
    }
}
