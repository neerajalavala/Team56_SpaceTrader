package com.example.spacetrader.entity.world;

import com.example.spacetrader.entity.gamelogic.Difficulty;

import java.io.Serializable;
import java.util.HashMap;

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

    private Integer[][] grid = new Integer[100][150];

    private Difficulty diff;

    private String[] solarSystemNames = {
            "A","B","C","D"
    };

    private int[] xlocs = {30,50,70,90};
    private  int[] ylocs = {20,40,60,80};

    private HashMap<Integer, UniverseEntity> entities  = new HashMap<Integer, UniverseEntity>();

    private SolarSystem[] solarSystems = new SolarSystem[solarSystemNames.length];

    public Universe(int id, Difficulty diff){
        this.playerID = id;
        this.diff = diff;

        for (int i = 0; i < solarSystemNames.length; i++){
            solarSystems[i] = new SolarSystem(playerID, count, solarSystemNames[i]);

            /* Sets coordinates in the solar system to the id */
            setGrid(xlocs[i],ylocs[i], count);

            /* maps id to entity */
            entities.put(count, solarSystems[i]);

            /* increases count to make new id */
            count++;
        }

    }

    public Integer[][] getGrid(){
        return this.grid;
    }

    public UniverseEntity getGridLocData(Integer x,Integer y){
        Integer id = grid[x][y];

        if (id == null) {
            return null;
        } else {
            return entities.get(id);
        }
    }

    public void setGrid(Integer x,Integer y, Integer ent_id){
        grid[x][y] = ent_id;

    }

    public SolarSystem[] getSolarSystems(){
        return solarSystems;
    }

    public int getPlayerID(){
        return playerID;
    }

}
