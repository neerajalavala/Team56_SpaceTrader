package com.example.spacetrader.entity;

/**
 * Represents types of ships
 */
public enum ShipType {
    FLEA("Flea", 10, 20),
    GNAT("Gnat", 15, 15),
    FIREFLY("Firefly", 20, 17),
    MOSQUITO("Mosquito", 15, 13),
    BUMBLEBEE("Bumblebee", 20, 15),
    BEETLE("Beetle", 50, 14),
    HORNET("Hornet", 20, 16),
    GRASSHOPPER("Grasshopper", 30, 15),
    TERMITE("Termite", 60, 13),
    WASP("Wasp", 35, 14);

    private final String ship;
    private final int capacity;
    private final int maxFuel;

    ShipType(String s, int c, int maxFuel) {
        this.ship = s;
        this.capacity = c;
        this.maxFuel = maxFuel;
    }

    /**
     *
     * @return max capacity of ship type
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *
     * @return max fuel of ship type
     */
    public Integer getMaxFuel() {
        return maxFuel;
    }


    @Override
    public String toString() {
        return this.ship;
    }
}
