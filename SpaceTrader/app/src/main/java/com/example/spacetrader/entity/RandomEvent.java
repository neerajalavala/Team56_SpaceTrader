package com.example.spacetrader.entity;


import java.io.Serializable;

/**
 * Represents possible random events
 */
public enum RandomEvent implements Serializable {
    COPS("Cops"),
    TRADER("Trader"),
    PIRATE("Pirate"),
    CREDITS("Credits");

    private final String diff;

    RandomEvent(String s) {
        this.diff = s;
    }


    @Override
    public String toString() {
        return this.diff;
    }

    public String getDiff() {
        return diff;
    }
}
