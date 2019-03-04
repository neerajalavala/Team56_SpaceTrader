package com.example.spacetrader.entity.gamelogic;

import com.example.spacetrader.entity.commerce.MarketGood;
import com.example.spacetrader.entity.commerce.MarketGoodType;

import java.io.Serializable;

public class CargoHold implements Serializable {
    private int count;
    private int capacity;
    private MarketGood[] hold;

    public CargoHold(int cap) {
        MarketGoodType[] types = MarketGoodType.values();
        hold = new MarketGood[types.length];
        this.capacity = cap;
        for (int i = 0; i < hold.length; i++) {
            hold[i] = new MarketGood(types[i]);
        }
        count = 0;
    }

    public boolean addGood(MarketGood good, int q) {
        if (count + q > capacity) {
            return false;
        }
        for (MarketGood m : hold) {
            if (m.getType() == good.getType()) {
                m.addQuantity(q);
                break;
            }
        }
        return true;
    }

    public boolean subQuant(MarketGood good, int q) {
        if (count - q < 0) {
            return false;
        }
        for (MarketGood m : hold) {
            if (m.getType() == good.getType() && m.getQuantity() - q >= 0) {
                m.subQuantity(q);
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return count > capacity;
    }
}
