package com.example.spacetrader.entity;

import com.example.spacetrader.R;

/**
 * Represents types of ships
 */
public enum ShipType {
    FLEA("Flea", 10, 20, 2000, 15, 10, 80, R.mipmap.flea),
    GNAT("Gnat", 15, 15, 11000, 10, 15, 100, R.mipmap.gnat),
    FIREFLY("Firefly", 20, 17, 25000, 12, 20, 100, R.mipmap.firefly),
    MOSQUITO("Mosquito", 15, 13, 30000, 15, 20, 100, R.mipmap.mosquito),
    BUMBLEBEE("Bumblebee", 20, 15, 60000, 15, 25, 150, R.mipmap.bumblebee),
    BEETLE("Beetle", 50, 14, 80000, 18, 25, 150, R.mipmap.beetle),
    HORNET("Hornet", 20, 16, 100000, 20, 30, 150, R.mipmap.hornet),
    GRASSHOPPER("Grasshopper", 25, 15, 150000, 40, 20, 200, R.mipmap.grasshopper),
    TERMITE("Termite", 60, 13, 150000, 30, 30, 400, R.mipmap.termite),
    WASP("Wasp", 35, 14, 150000, 20, 40, 300, R.mipmap.wasp);

    private final String ship;
    private final int capacity;
    private final int maxFuel;
    private final int price;
    private final int speed;
    private final int baseAttack;
    private final int max_health;
    private final int pic_loc;


    ShipType(String s, int c, int maxFuel, int price, int speed, int baseAttack, int max_health, int pic) {
        this.ship = s;
        this.capacity = c;
        this.maxFuel = maxFuel;
        this.price = price;
        this.speed = speed;
        this.baseAttack = baseAttack;
        this.max_health = max_health;
        this.pic_loc = pic;

    }

    /**
     *
     * @return max capacity of ship type
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     *
     * @return max fuel of ship type
     */
    public Integer getMaxFuel() {
        return maxFuel;
    }

    public int getPrice() {
        return price;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMax_health() {
        return max_health;
    }

    @Override
    public String toString() {
        return this.ship;
    }

    public String getShip() {
        return ship;
    }

    public int getPic_loc() {
        return pic_loc;
    }
}
