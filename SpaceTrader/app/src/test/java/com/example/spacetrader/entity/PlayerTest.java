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

    @Test
    public void subFuel() {
        //test subtracting more fuel than in the tank
        int maxFuel = play.getFuel();
        play.subFuel(maxFuel + 1);

        //test subtracting a negative amount of fuel
        assertEquals(play.getFuel(), maxFuel);
        play.subFuel(-1);

        //test subtracting fuel actually results in the correct subtraction
        assertEquals(play.getFuel(), maxFuel);
        play.subFuel(5);
        assertEquals(play.getFuel(), maxFuel - 5);
    }

    @Test
    public void addFuel() {

        int maxFuel = play.getShipMaxFuel();
        play.setFuel(0);

        // test adding fuel to exceed max fuel capacity
        play.addFuel(maxFuel + 1);
        assertEquals(0, play.getFuel());

        // test adding negative amounts of fuel
        play.addFuel(-1);
        assertEquals(0, play.getFuel());

        // test adding valid amounts of fuel
        play.addFuel(maxFuel - 1);
        assertEquals(maxFuel - 1, play.getFuel());
    }

    @Test
    public void refuel() {

        int maxFuel = play.getShipMaxFuel();

        // test refuel when player has < 50 credits
        play.setCredits(49);
        play.setFuel(0);
        play.refuel();
        assertEquals(0, play.getFuel());
        assertEquals((Integer)49, play.getCredits());

        // test refuel when player has max fuel
        play.setCredits(50);
        play.setFuel(maxFuel);
        play.refuel();
        assertEquals(maxFuel, play.getFuel());
        assertEquals((Integer)50, play.getCredits());

        // test refuel when player has less than max fuel and enough credits
        play.setCredits(55);
        play.setFuel(0);
        play.refuel();
        assertEquals(1, play.getFuel());
        assertEquals((Integer)5, play.getCredits());

    }
}