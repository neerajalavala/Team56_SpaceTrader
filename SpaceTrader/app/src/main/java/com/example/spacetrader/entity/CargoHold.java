package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Represents the CargoHold to hold goods
 */
public class CargoHold implements Serializable {
    private Integer count;

    private final int playerID;

    private Integer capacity;

    private Map<String, MarketGood> hold = new HashMap<>();

    /**
     * CargoHold constructor
     *
     * @param cap max capacity of CargoHold
     * @param id player ID
     */
    public CargoHold(int cap, int id) {
        MarketGoodType[] types = MarketGoodType.values();
        this.capacity = cap;
        this.playerID = id;

        for (MarketGoodType t : types) {
            hold.put(t.toString(), new MarketGood(t, playerID));
        }
        count = 0;
    }

    /**
     * Adds goods to CargoHold and increases current capacity
     *
     * @param goodName name of good that is added to CargoHold
     * @param quantity amount of good that is addeded to CargoHold
     * @throws PurchaseException if adding goods would exceed capacity or adding nonexistent good
     */
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

    /**
     * Removes goods from CargoHold and decreases current capacity
     *
     * @param goodName name of good that is removed to CargoHold
     * @param quantity quantity of good that is removed from CargoHold
     * @throws PurchaseException if selling more goods than currently have or selling nonexistent good
     */
    public void removeGoods(String goodName, int quantity) throws PurchaseException {
        if (count - quantity < 0) {
            throw new PurchaseException("Cannot sell " + quantity + " goods with only " + count +
                    " in the hold!");
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

    /**
     * Gets a list of sellable goods based on goods in CargoHold
     *
     * @param lev Tech level that goods are sold at
     * @return list of sellable goods
     */
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

    /**
     * Checks if the CargoHold is full
     *
     * @return True if CargoHold is full, otherwise False
     */
    public boolean isFull() {
        return count >= capacity;
    }

    /**
     * Gets player ID
     *
     * @return int representing player ID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Gets max capacity of CargoHold
     *
     * @return max capacity of CargoHold
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Gets current capacity of CargoHold
     *
     * @return current capacity of CargoHold
     */
    public Integer getCount() {
        return count;
    }

    public String toString() {
        return "Hold: " + count + "/" + capacity;
    }
}
