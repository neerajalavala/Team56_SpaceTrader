package com.example.spacetrader.entity;

public enum TechLevel {
    PREAGRICULTURE("Pre-agriculture"),
    AGRICULTURE("Agriculture"),
    MEDIEVAL("Medieval"),
    RENAISSANCE("Renaissance"),
    EARLYINDUSTRIAL("Early-Industrial"),
    INDUSTRIAL("Industrial"),
    POSTINDUSTRIAL("Post-Industrial"),
    HITECH("Hi-Tech");

    private String name;

    private TechLevel(String s){
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
