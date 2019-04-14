package com.example.spacetrader.entity;



public enum TechLevel {
    PREAGRICULTURE("Pre-agriculture", 0),
    AGRICULTURE("Agriculture", 1),
    MEDIEVAL("Medieval", 2),
    RENAISSANCE("Renaissance", 3),
    EARLYINDUSTRIAL("Early-Industrial", 4),
    INDUSTRIAL("Industrial", 5),
    POSTINDUSTRIAL("Post-Industrial", 6),
    HITECH("Hi-Tech", 7),
    NONE("NONE", 8);

    private String name;
    private int index;

    TechLevel(String s, int i){
        this.name = s;
        this.index = i;
    }

    public int index() {
        return index;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
