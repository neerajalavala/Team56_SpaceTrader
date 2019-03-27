package com.example.spacetrader.entity.gamelogic;

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

    private String ship;
    private int capacity;
    private Integer speed;

    private ShipType(String s, int c, Integer speed) {
        this.ship = s;
        this.capacity = c;
        this.speed =speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public Integer getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return this.ship;
    }
}
