package com.example.spacetrader.entity;


/**
 * Represents possible random events
 */
public enum RandomEvent {
    COPS("Cops"),
    TRADER("Trader"),
    PIRATE("Pirate"),
    CREDITS("Credits");

    private String diff;

    RandomEvent(String s) {
        this.diff = s;
    }


    @Override
    public String toString() {
        return this.diff;
    }
}
