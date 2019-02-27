package com.example.spacetrader.entity.world;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends UniverseEntity{

    private final int EntityID;

    private String name;

    private List<Planet> PlanetList = new ArrayList<>();

    public SolarSystem(int id, String name){
        super(id, name);

        for (int i =0; i < 3; i++) {
            Planet plan = new Planet("Planet " + i);
            PlanetList.add(plan);
        }

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

}
