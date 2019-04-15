package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;
import java.util.Random;


/**
 * Represents a Planet
 */
public class Planet implements Serializable {

    private String name;

    private Resources resources;

    private TechLevel techLevel;

    private MarketPlace marketPlace;

    private int solar_id;

    /**
     * Creates a planet
     *
     * @param name name of planet
     * @param solar_id id of solar system planet belongs to
     */
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

    /**
     *
     * @return name of planet
     */
    public String getName() {
        return name;
    }

    /**
     * Get resources of planet
     *
     * @return resource of planet
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * Get tech level of planet
     *
     * @return tech level of planet
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }

    /**
     * Get marketplace of planet
     *
     * @return marketplace of planet
     */
    public MarketPlace getMarketPlace() {
        return marketPlace;
    }

    /**
     * Get id of solar system of planet
     *
     * @return id of solar system
     */
    public int getSolar_id() {
        return solar_id;
    }

    /**
     * Sets the marketplace of planet
     *
     * @param marketPlace marketplace to set
     */
    public void setMarketPlace(MarketPlace marketPlace) {
        this.marketPlace = marketPlace;
    }


    public String toString() {
        return name;
    }

    /**
     * If planet name equals another planet name
     *
     * @param planet other planet to compare with
     * @return true if planets are equal, else false
     */
    public boolean equals(Planet planet) {
        return this.name.equals(planet.name);
    }

    /**
     * Removes quantity from marketplace
     *
     * @param goodName name of good to remove
     * @param quantity quantity to remove
     * @throws PurchaseException if good name is nonexistent
     */
    public void removeMarketPlaceQuantity(String goodName, int quantity) throws PurchaseException {
        marketPlace.removeGoods(goodName, quantity);
    }
}
