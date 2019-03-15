package com.example.spacetrader.entity.gamelogic;

import com.example.spacetrader.entity.commerce.MarketGood;
import com.example.spacetrader.entity.commerce.MarketGoodType;
import com.example.spacetrader.entity.world.TechLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CargoHold implements Serializable {
    private Integer count;

    private final int playerID;

    private Integer capacity;

    private ArrayList<MarketGood> hold = new ArrayList<>();

    public CargoHold(int cap, int id) {
        MarketGoodType[] types = MarketGoodType.values();
        this.capacity = cap;
        this.playerID = id;

        for (int i = 0; i < types.length; i++) {
            hold.add(new MarketGood(types[i], playerID));
        }
        count = 0;
    }

    public void addGood(MarketGood good, int q) {
        for (MarketGood m : hold) {
            if (m.getType() == good.getType()) {
                m.addQuantity(q);
                break;
            }
        }
        count += q;
    }

    public void subQuant(MarketGood good, int q) {
        for (MarketGood m : hold) {
            if (m.getType() == good.getType() && m.getQuantity() - q >= 0) {
                m.subQuantity(q);
                count -= q;
            }
        }
    }

    public List<MarketGood> getSellableGoods(TechLevel lev) {
        List<MarketGood> sellList = new ArrayList<>();
        for (MarketGood m : hold) {
            if (m.getType().canSell(lev) && m.getQuantity() > 0){
                if (m.getPrice_count() % 2 == 0) {
                    m.setPrice();
                }
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

    public boolean canAdd(int quant) {
        return count + quant <= capacity;
    }

    public boolean canRemove(int quant) {
        return count - quant >= 0;
    }
}
