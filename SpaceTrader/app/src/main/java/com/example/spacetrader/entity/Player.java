package com.example.spacetrader.entity;

import java.io.Serializable;

public class Player implements Serializable {
    /** counter for id */
    private static Integer player_ids = 1;

    /** a globally unique number for this object */
    private int ID;

    /** total number of skill points */
    private final Integer skillPtCap = 16;

    /** starting number of credits */
    private final Integer startCredits = 1000;

    /** difficulty player is playing at */
    private Difficulty diff;

    /** name of player */
    private String Name;

    /** Number of pilot skill points */
    private Integer Pilot;

    /** Number of fighter skill points */
    private Integer Fighter;

    /** Number of trader skill points */
    private Integer Trader;

    /** Number of engineer skill points */
    private Integer Engineer;

    /** number of credits player has */
    private Integer Credits;

    /**player ship information*/
    private ShipType Ship_type = ShipType.GNAT;
    private int fuel = Ship_type.getMaxFuel();
    private CargoHold cargoHold;

    private Universe game;

    public Player(String name, Integer pilot, Integer fighter, Integer trader, Integer engineer, Difficulty diff) {
        this.ID = player_ids;
        this.Name = name;

        this.Pilot = pilot;
        this.Fighter = fighter;
        this.Trader = trader;
        this.Engineer = engineer;

        this.diff = diff;

        this.Credits = startCredits;

        this.game = new Universe(player_ids);

        player_ids++;

        cargoHold = new CargoHold(Ship_type.getCapacity(), this.ID);
    }
/*
    public static Player createPlayer(String name, Integer pilot, Integer fighter, Integer trader, Integer engineer, Difficulty diff) {
        if ((pilot + fighter + trader + engineer != 16) || (pilot < 0 || fighter < 0 || trader < 0 || engineer < 0)) {
            // throw new IllegalPointAllocationException
        }
        if (name.equals("") || name == null) {
            // throw new InvalidNameException
        }
        return new Player(name, pilot, fighter, trader, engineer, diff);
    }
*/
    public CargoHold getCargoHold() {
        return cargoHold;
    }

    public boolean addCredits(int q) {
        if (q < 0) return false;
        this.Credits += q;
        return true;
    }

    public boolean subCredits(int q) {
        if (this.Credits - q < 0) return false;
        this.Credits -= q;
        return true;
    }

    public Integer getPilot() {
        return Pilot;
    }

    public Integer getFighter() {
        return Fighter;
    }

    public Integer getTrader() {
        return Trader;
    }

    public Integer getEngineer() {
        return Engineer;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public Difficulty getDiff() {
        return this.diff;
    }

    /**
     * method for setting player id
     */
    public void setID(Integer id) {
        this.ID = id;
    }

    /**
     * method for getting player id
     * @return id of player
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * method for getting player name
     * @return name of player
     */
    public String getName() {
        return this.Name;
    }

    /**
     * method for changing player name
     * @param name name of player
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * method for getting number of player credits
     * @return number of player credits
     */
    public Integer getCredits() {
        return this.Credits;
    }

    /**
     * method for setting player credits
     * @param cred new number of credits
     */
    public void setCredits(Integer cred) {
        this.Credits = cred;
    }

    /**
     * method for getting ship type
     * @return type of ship
     */
    public ShipType getShipType() {
        return this.Ship_type;
    }

    /** method for changing type of ship
     * @param type type of ship
     */
    public void setShipType(ShipType type) {
        this.Ship_type = type;
     }

    public Universe getGame() {
        return game;
    }

    public void setGame(Universe game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public Planet getCurrentPlanet() {
        return game.getCurrentPlayerPlanet();
    }

    public void setCurrentPlayerPlanet(SolarSystem s, int i) {
        game.setCurrentPlayerPlanet(s, i);
    }

    public void setCargoHold(CargoHold cargoHold) {
        this.cargoHold = cargoHold;
    }

    public int getFuel() {
        return fuel;
    }

    public void addFuel(int q) {
        if (q < 0 || fuel + q > Ship_type.getMaxFuel()) {
            return;
        }
        fuel += q;
    }

    public void subFuel(int q) {
        if (q < 0 || fuel - q < 0) {
            return;
        }
        fuel -= q;
    }

    public int getShipMaxFuel() {
        return Ship_type.getMaxFuel();
    }
}