package com.example.spacetrader.entity.world;

import com.example.spacetrader.entity.commerce.MarketPlace;

import java.io.Serializable;
import java.util.Random;

public class Planet implements Serializable {

    private String name;

    private final int playerID;

    private Resources resources;

    private TechLevel techLevel;

    private MarketPlace marketPlace;

    public Planet(int playerid, String name){
        this.name = name;
        this.playerID = playerid;

        int tLev = new Random().nextInt(7);
        int rType = new Random().nextInt(34);

        if(rType < 10){
            rType = 0;
        } else {
            rType = (rType - 10)/2;
        }

        this.resources = Resources.values()[rType];
        this.techLevel = TechLevel.values()[tLev];

        this.marketPlace = new MarketPlace(this.techLevel, this.resources, playerID);
    }

    public String getName() {
        return name;
    }

    public Resources getResources() {
        return resources;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    public String toString() {
        return name + ", " + techLevel + ", " + resources + "; ";
    }

    public int getPlayerID() {
        return playerID;
    }
}
