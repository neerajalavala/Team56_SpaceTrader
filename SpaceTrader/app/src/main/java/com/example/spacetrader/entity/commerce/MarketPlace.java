package com.example.spacetrader.entity.commerce;

import com.example.spacetrader.entity.Resources;
import com.example.spacetrader.entity.TechLevel;
import com.example.spacetrader.entity.gamelogic.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarketPlace implements Serializable {
    private ArrayList<MarketGood> marketGoods = new ArrayList<>();

    private TechLevel techLevel;
    private Resources resources;

    // to avoid having every MarketPlace having a copy of player
    // set the player as here/null during travel in the future
    private Player player;

    public MarketPlace(TechLevel techLevel, Resources resources) {
        this.techLevel = techLevel;
        this.resources = resources;


    }

    public List<MarketGood> getGoods() {
        return marketGoods;
    }

    public int getPrice(MarketGood marketGood) {
        return marketGood.getPrice();
    }
}
