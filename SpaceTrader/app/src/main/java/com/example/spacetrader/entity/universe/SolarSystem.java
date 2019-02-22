package com.example.spacetrader.entity.universe;

/**
 * class representing a single system in the universe
 */

public class SolarSystem {
    /** name of the system*/
    private String name;

    /** technology level of the system */
    private TechnologyLevel techLevel;

    /** resources of the system */
    private Resources resources;

    /** x coordinate of the system within the universe */
    private int xCoord;

    /** y coordinate of the system within the universe */
    private int yCoord;

    /**
     * creates a new solar system
     *
     * @param name the system's name
     * @param x the system's x coordinate
     * @param y the system's y coordinate
     * @param t the system's tech level
     * @param r the system's resources
     */
    public SolarSystem(String name, int x, int y, TechnologyLevel t, Resources r) {
        this.name = name;
        this.xCoord = x;
        this.yCoord = y;
        this.techLevel = t;
        this.resources = r;
    }

    /**
     * getter for system name
     * @return system name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for system tech level
     * @return system tech level
     */
    public TechnologyLevel getTechLevel() {
        return techLevel;
    }

    /**
     * getter for system resources
     * @return system resources
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * getter for system x coordinate
     * @return system x coordinate
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * getter for system y coordinate
     * @return system y coordinate
     */
    public int getyCoord() {
        return yCoord;
    }

    public String toString() {
        return "Name: " + name + "; Coordinates: " + xCoord + ", " + yCoord + "; Tech Level: " + techLevel.toString() + "; Resources: " + resources;
    }
}
