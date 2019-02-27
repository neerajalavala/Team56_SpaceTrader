package com.example.spacetrader.entity;

import com.example.spacetrader.entity.commerce.MarketPlace;

import java.io.Serializable;
import java.util.Random;

public class Planet implements Serializable {

    private String name;

    private Resources resources;

    private TechLevel techLevel;

    private MarketPlace marketPlace;

    public Planet(String name){
        this.name = name;

        int tLev = new Random().nextInt(7);
        int rType = new Random().nextInt(34);

        if(rType < 10){
            rType = 0;
        } else {
            rType = (rType - 10)/2;
        }

        this.resources = Resources.values()[rType];
        this.techLevel = TechLevel.values()[tLev];
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


}
