package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe implements Serializable {

    private final int shipID = 0;

    private String[] solarSystemNames = {
            "System A","System B","System C","System D","System E","System F","System G","System H","System I","System J"
    };

    private SolarSystem[] solarSystems = new SolarSystem[solarSystemNames.length];

    private Planet currentPlayerPlanet;
    private SolarSystem currentPlayerSystem;

    public Universe(int id){

        for (int i = 0; i < solarSystemNames.length; i++) {
            int[] ylocs = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200};
            int[] xlocs = {30, 50, 70, 90, 110, 130, 150, 170, 190, 210};
            /*
             * count for creating unique identities for each entity in universe
             */
            Integer count = 1;
            solarSystems[i] = new SolarSystem(count, solarSystemNames[i], xlocs[i], ylocs[i]);

            /* maps id to entity */
            //entities.put(count, solarSystems[i]);

            /* increases count to make new id */
            count++;
        }

        this.currentPlayerPlanet = solarSystems[0].getPlanet(0);
        this.currentPlayerSystem = solarSystems[0];
    }


    public SolarSystem[] getSolarSystems(){
        return solarSystems;
    }

    /**
     * sets the current planet to the ith planet of system s
     *
     * @param p the planet to have the player be on
     * @param s the system to have the player be on
     */
    public void setCurrentPlayerLocation(SolarSystem s, Planet p) {
        this.currentPlayerSystem = s;
        this.currentPlayerPlanet = p;
    }

    public SolarSystem
    getCurrentPlayerSystem() {
        return currentPlayerSystem;
    }

    public Planet getCurrentPlayerPlanet() {
        return currentPlayerPlanet;
    }

    public RandomEvent getRandomEvent() {

        Random rand = new Random();
        int eventNum = rand.nextInt(11);

        if (eventNum <= 5) {
            return RandomEvent.CREDITS;
        } else if (eventNum <= 7) {
            return RandomEvent.TRADER;
        } else if (eventNum <= 9) {
            return RandomEvent.PIRATE;
        } else {
            return RandomEvent.COPS;
        }
    }


    public MarketPlace getCurrentPlayerMarketPlace() {
        return currentPlayerPlanet.getMarketPlace();
    }

    public void removeCurrentMarketPlaceQuantity(String goodName, int quantity) throws PurchaseException {
        currentPlayerPlanet.removeMarketPlaceQuantity(goodName, quantity);
    }

    public List<SolarSystem> getPlayerTravleablePlanets(int fuel) {
        List<SolarSystem> travelableSystems = new ArrayList<>();
        for (SolarSystem s : solarSystems){
            int fuels;
            int distanceTo = currentPlayerSystem.distanceTo(s);
            if (distanceTo % fuel != 0) {
                fuels = (distanceTo / fuel) + 1;
            } else {
                fuels = (distanceTo / fuel);
            }
            if (fuels <= fuel) {
                travelableSystems.add(s);
            }
        }
        return travelableSystems;
    }
}
