package com.example.spacetrader.entity;

import android.support.annotation.NonNull;

/**
 * Represents levels of difficulty
 */
public enum Difficulty {
    BEGINNER("Beginner"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    IMPOSSIBLE("Impossible");

    private final String diff;

    Difficulty(String s) {
        this.diff = s;
    }

    @NonNull
    @Override
    public String toString() {
        return this.diff;
    }
}
