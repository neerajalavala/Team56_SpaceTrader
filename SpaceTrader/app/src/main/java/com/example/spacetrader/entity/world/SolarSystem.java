package com.example.spacetrader.entity.world;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends UniverseEntity{

    private final int playerID;
    private final int EntityID;

    private int[] location = new int[2];

    private String name;

    private List<Planet> PlanetList = new ArrayList<>();

    public SolarSystem(int playerID, int id, String name, int xloc, int yloc){
        super(id, name);

        for (int i =0; i < 3; i++) {
            Planet plan = new Planet(playerID, "Planet " + i, id);
            PlanetList.add(plan);
        }

        this.location[0] = xloc;
        this.location[1] = yloc;

        this.playerID = playerID;
        this.EntityID = id;
        this.name = name;


    }

    public String getName() {
        return name;
    }

    public List<Planet> getPlanets() {
        return PlanetList;
    }

    public int getEntityID() {
        return EntityID;
    }

    public String toString() {
        String a = "";
        for (Planet p : PlanetList) {
            a += p.toString();
        }
        return a;
    }

    public int getPlayerID() { return playerID; }

    public int[] getLocation() {
        return location;
    }

    public Planet getPlanet(int i) {
        return PlanetList.get(i);
    }

}
