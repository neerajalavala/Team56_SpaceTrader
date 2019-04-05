package com.example.spacetrader.entity.gamelogic;

public enum randomEvent {
    COPS("Cops"),
    TRADER("Trader"),
    PIRATE("Pirate"),
    CREDITS("Credits");

    private String diff;

    private randomEvent(String s) {
        this.diff = s;
    }

    @Override
    public String toString() {
        return this.diff;
    }
}
