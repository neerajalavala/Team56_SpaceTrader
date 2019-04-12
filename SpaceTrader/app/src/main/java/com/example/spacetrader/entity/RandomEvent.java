package com.example.spacetrader.entity;

public enum RandomEvent {
    COPS("Cops"),
    TRADER("Trader"),
    PIRATE("Pirate"),
    CREDITS("Credits");

    private String diff;

    private RandomEvent(String s) {
        this.diff = s;
    }

    @Override
    public String toString() {
        return this.diff;
    }
}
