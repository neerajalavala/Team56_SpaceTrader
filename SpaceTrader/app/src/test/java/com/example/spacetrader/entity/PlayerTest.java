package com.example.spacetrader.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player play = new Player("Bob", 50, 50, 50, 60,
            Difficulty.HARD);

    @Test
    public void addCredits() {
        assertEquals(play.addCredits(50), true);
        int num = play.getCredits();
        assertEquals(num, 1050);
        assertEquals(play.addCredits(-100), false);
        assertEquals(num, 1050);
    }

    @Test
    public void subCredits() {
        assertEquals(play.subCredits(50), true);
        int num = play.getCredits();
        assertEquals(num, 950);
        play.subCredits(50);
        num =  play.getCredits();
        assertEquals(num, 900);
        assertEquals(play.subCredits(1001), false);
    }
}