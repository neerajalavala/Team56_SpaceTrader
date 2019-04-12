package com.example.spacetrader.entity;

import java.io.Serializable;
import java.util.Random;

public class Planet implements Serializable {

    private String name;

    private Resources resources;

    private TechLevel techLevel;

    private MarketPlace marketPlace;

    private int solar_id;

    public Planet(String name, int solar_id){
        this.name = name;
        this.solar_id = solar_id;

        int tLev = new Random().nextInt(7);
        int rType = new Random().nextInt(34);

        if(rType < 10){
            rType = 0;
        } else {
            rType = (rType - 10)/2;
        }

        this.resources = Resources.values()[rType];
        this.techLevel = TechLevel.values()[tLev];

        this.marketPlace = new MarketPlace(this.techLevel, this.resources);
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

    public int getSolar_id() {
        return solar_id;
    }

    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }

    public String toString() {
        return name + ", " + techLevel + ", " + resources + "; ";
    }
}
