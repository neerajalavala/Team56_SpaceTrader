package com.example.spacetrader.entity.world;

import android.util.Log;

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

    private Integer[][] grid = new Integer[250][450];

    private Difficulty diff;

    private String[] solarSystemNames = {
            "System A","System B","System C","System D","System E","System F","System G","System H","System I","System J"
    };

    private int[] xlocs = {30,50,70,90,110,130,150,170,190,210};
    private  int[] ylocs = {20,40,60,80,100,120,140,160,180,200};

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
        Log.d("M6_LOGCAT", "Player " + id + " Universe:");
        for (int i = 0; i < solarSystems.length; i++) {
            largeLog("M6_LOGCAT", "In System " + solarSystemNames[i] + " at location <" + xlocs[i] + ", " + ylocs[i] + ">: " + solarSystems[i].toString());
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

    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }

    public int getPlayerID(){
        return playerID;
    }

}
