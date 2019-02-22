package com.example.spacetrader.entity.universe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * class to contain and randomly generate the various systems needed for the game
 */

public class Universe {
    /** hashmap with form [Name, System] to hold systems */
    private Map<String, SolarSystem> systems;

    /** overall dimensions of the universe */
    private int xSize;
    private int ySize;

    /**
     * @param numSystems how many systems the universe should contain
     * @param xSize the x dimension of the universe, should be power of 2 for greater efficiency
     * @param ySize the y dimension of the universe, should be power of 2 for greater efficiency
     */
    public Universe(int numSystems, int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        systems = new HashMap<>();
        generateSystems(numSystems);
    }

    /**
     * randomly generates planets to populate {@code systems}
     * @param n the number of systems to generate
     */
    public void generateSystems(int n) {
        TechnologyLevel[] levels = TechnologyLevel.values(); // for the random picking
        Resources[] resources = Resources.values(); // for the random picking
        Map<Integer, Integer> coordinates = new HashMap<>();
        Random r = new Random();
        PlanetNameGen pNGEN = new PlanetNameGen(r);

        // randomly generate coordinates using hashmap to avoid collisions
        while (coordinates.size() < n) {
            coordinates.put(r.nextInt(xSize), r.nextInt(ySize));
        }


        for (Integer x : coordinates.keySet()) {
            String rName = pNGEN.randomName();
            SolarSystem solarSystem = new SolarSystem(rName, x, coordinates.get(x), levels[r.nextInt(levels.length)], resources[r.nextInt(resources.length)]);
            systems.put(rName, solarSystem);
        }
    }

    /**
     * getter for the universe's systems' names
     * @return the names of the systems contained by the universe
     */
    public Set<String> getSystemNames() {
        return systems.keySet();
    }

    /**
     * getter for all data on the universe's systems
     * @return a [Name, System] hashmap of the universe's systems
     */
    public Map<String, SolarSystem> getSystems() {
        return systems;
    }

    public String toString() {
        for (SolarSystem s : systems.values()) {
            System.out.println(s);
        }
        return "";
    }
}
