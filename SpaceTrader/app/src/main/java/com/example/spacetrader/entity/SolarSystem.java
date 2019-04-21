package com.example.spacetrader.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a solar system
 */
public class SolarSystem extends UniverseEntity{

    private int EntityID;

    private final int[] location = new int[2];

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setEntityID(int entityID) {
        EntityID = entityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Planet> getPlanetList() {
        return PlanetList;
    }

    private String name;

    private List<Planet> PlanetList;

    public void setPlanetList(List<Planet> planetList) {
        PlanetList = planetList;
    }

    /**
     * Creates a solar system
     *
     * @param id id of entity
     * @param name name of solar system
     * @param xLocation x coordinates of solar system
     * @param yLocation y coordinates of solar system
     */
    public SolarSystem(int id, String name, int xLocation, int yLocation){
        super(id, name);

        PlanetList = new ArrayList<>();
        for (int i =0; i < 3; i++) {
            Planet plan = new Planet( "Planet " + i, id);
            PlanetList.add(plan);
        }

        this.position = new Position(xLocation, yLocation);

        this.location[0] = xLocation;
        this.location[1] = yLocation;

        this.EntityID = id;
        this.name = name;
    }

    public SolarSystem() {}

    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return planets of solar system
     */
    public List<Planet> getPlanets() {
        return PlanetList;
    }

    @Override
    public int getEntityID() {
        return EntityID;
    }


    public String toString() {
        return getName();
    }

    /**
     *
     * @return location of solar system
     */
    private int[] getLocation() {
        return location;
    }

    /**
     *
     * @param i index of solar system
     * @return planet of solar system
     */
    public Planet getPlanet(int i) {
        return PlanetList.get(i);
    }

    /**
     * Checks if one solar system's name is equal to another
     *
     * @param solarSystem other solar system to be compared
     * @return true if equal, else false
     */
    public boolean equals(SolarSystem solarSystem) {
        return this.name.equals(solarSystem.name);
    }

    /**
     *
     * @param s solar system to get to
     * @return distance to another solar system
     */
    public int distanceTo(SolarSystem s) {
        int xLocation1 = position.getX(); //location[0];
        int yLocation1 = position.getY(); //location[1];

        int xLocation2 = s.getPosition().getX(); //s.getLocation()[0];
        int yLocation2 = s.getPosition().getY(); //s.getLocation()[1];

        return (int) Math.sqrt(Math.pow((xLocation1 - xLocation2), 2) +
                Math.pow((yLocation1 - yLocation2), 2));
    }

    /**
     * Number of turns to a solar system
     *
     * @param s solar system to get to
     * @param fuel amount of fuel required
     * @return number of turns to a solar system
     */
    public int turnsTo(SolarSystem s, int fuel) {
        int distance = distanceTo(s);
        if (distance % fuel != 0) {
            return (distance / fuel) + 1;
        } else {
            return (distance / fuel);
        }
    }
}
