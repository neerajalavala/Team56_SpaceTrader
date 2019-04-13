package com.example.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends UniverseEntity{

    private final int EntityID;

    private int[] location = new int[2];

    private String name;

    private List<Planet> PlanetList = new ArrayList<>();

    public SolarSystem(int id, String name, int xloc, int yloc){
        super(id, name);

        for (int i =0; i < 3; i++) {
            Planet plan = new Planet( "Planet " + i, id);
            PlanetList.add(plan);
        }

        this.location[0] = xloc;
        this.location[1] = yloc;

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
        return getName();
    }

    public int[] getLocation() {
        return location;
    }

    public Planet getPlanet(int i) {
        return PlanetList.get(i);
    }

    public boolean equals(SolarSystem solarSystem) {
        return this.name.equals(solarSystem.name);
    }

    public int distanceTo(SolarSystem s) {
        int xloc1 = location[0];
        int yloc1 = location[1];

        int xloc2 = s.getLocation()[0];
        int yloc2 = s.getLocation()[1];

        return (int) Math.sqrt(Math.pow((xloc1 - xloc2), 2) + Math.pow((yloc1 - yloc2), 2));
    }

    public int turnsTo(SolarSystem s, int fuel) {
        int distance = distanceTo(s);
        if (distance % fuel != 0) {
            return (distance / fuel) + 1;
        } else {
            return (distance / fuel);
        }
    }
}
