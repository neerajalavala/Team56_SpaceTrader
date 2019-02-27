package com.example.spacetrader.entity.gamelogic;

public enum ShipType {
    FLEA("Flea", 10),
    GNAT("Gnat", 15),
    FIREFLY("Firefly", 20),
    MOSQUITO("Mosquito", 15),
    BUMBLEBEE("Bumblebee", 20),
    BEETLE("Beetle", 50),
    HORNET("Hornet", 20),
    GRASSHOPPER("Grasshopper", 30),
    TERMITE("Termite", 60),
    WASP("Wasp", 35);

    private String ship;
    private int capacity;

    private ShipType(String s, int c) {
        this.ship = s;
        this.capacity = c;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return this.ship;
    }
}
