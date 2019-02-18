package com.example.spacetrader.entity;

public enum ShipType {
    FLEA("Flea"),
    GNAT("Gnat"),
    FIREFLY("Firefly"),
    MOSQUITO("Mosquito"),
    BUMBLEBEE("Bumblebee"),
    BEETLE("Beetle"),
    HORNET("Hornet"),
    GRASSHOPPER("Grasshopper"),
    TERMITE("Termite"),
    WASP("Wasp");

    private String ship;

    private ShipType(String s) {
        this.ship = s;
    }

    @Override
    public String toString() {
        return this.ship;
    }
}
