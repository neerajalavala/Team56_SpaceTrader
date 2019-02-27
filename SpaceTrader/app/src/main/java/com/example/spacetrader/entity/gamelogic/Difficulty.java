package com.example.spacetrader.entity.gamelogic;

public enum Difficulty {
    BEGINNER("Beginner"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    IMPOSSIBLE("Impossible");

    private String diff;

    private Difficulty(String s) {
        this.diff = s;
    }

    @Override
    public String toString() {
        return this.diff;
    }
}
