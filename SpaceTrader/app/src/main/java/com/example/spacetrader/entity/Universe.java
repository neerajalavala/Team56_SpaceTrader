package com.example.spacetrader.entity;

import java.io.Serializable;
import java.util.Random;

public class Universe implements Serializable {
    /**
     * gamestate is a players specific universe
     */
    private final int playerID;


    private final int shipID = 0;

    /**
     * count for creating unique identities for each entity in universe
     */
    private Integer count = 1;

    /**
     * my current thinking for making the map is to make an int array filled with nulls
     *
     * each object created ie. solarsystem, policeship, trader ship has a specific int id
     *
     * id is mapped through hashmap to object and location
     */

    private Integer[][] grid = new Integer[250][450];

    private String[] solarSystemNames = {
            "System A","System B","System C","System D","System E","System F","System G","System H","System I","System J"
    };

    private int[] xlocs = {30,50,70,90,110,130,150,170,190,210};
    private  int[] ylocs = {20,40,60,80,100,120,140,160,180,200};

    //private HashMap<Integer, UniverseEntity> entities  = new HashMap<Integer, UniverseEntity>();

    private SolarSystem[] solarSystems = new SolarSystem[solarSystemNames.length];

    private Planet currentPlayerPlanet;

    public Universe(int id){
        this.playerID = id;

        for (int i = 0; i < solarSystemNames.length; i++) {
            solarSystems[i] = new SolarSystem(count, solarSystemNames[i], xlocs[i], ylocs[i]);

            /* Sets coordinates in the solar system to the id */
            setGrid(xlocs[i], ylocs[i], count);

            /* maps id to entity */
            //entities.put(count, solarSystems[i]);

            /* increases count to make new id */
            count++;
        }

        this.currentPlayerPlanet = solarSystems[0].getPlanet(0);
    }

    public Integer[][] getGrid(){
        return this.grid;
    }

//    public UniverseEntity getGridLocData(Integer x,Integer y){
//        Integer id = grid[x][y];
//
//        if (id == null) {
//            return null;
//        } else {
//            return entities.get(id);
//        }
//    }

    public void setGrid(Integer x,Integer y, Integer ent_id){
        grid[x][y] = ent_id;

    }

    public SolarSystem[] getSolarSystems(){
        return solarSystems;
    }

    /**
     * sets the current planet to the ith planet of system s
     *
     * @param s the system to have the player be on
     * @param i the index of the planet that the player is on
     */
    public void setCurrentPlayerPlanet(SolarSystem s, int i) {
        this.currentPlayerPlanet = s.getPlanet(i);
    }

    public Planet getCurrentPlayerPlanet() {
        return currentPlayerPlanet;
    }

    public RandomEvent getRandomEvent() {

        Random rand = new Random();
        int eventNum = rand.nextInt(11);

        if (eventNum <= 5) {
            return RandomEvent.CREDITS;
        } else if (eventNum > 5 && eventNum <= 7) {
            return RandomEvent.TRADER;
        } else if (eventNum > 7 && eventNum <= 9) {
            return RandomEvent.PIRATE;
        } else if (eventNum > 9) {
            return RandomEvent.COPS;
        }
        return RandomEvent.COPS;
    }


}
