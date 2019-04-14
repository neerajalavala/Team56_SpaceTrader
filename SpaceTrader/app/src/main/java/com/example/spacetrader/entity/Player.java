package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PlayerCreationException;
import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;

public class Player implements Serializable {
    /** counter for id */
    private static Integer player_ids = 1;
    /** total number of skill points */
    private static final Integer skillPtCap = 16;
    /** starting number of credits */
    private final Integer startCredits = 1000;
    /** a globally unique number for this object */
    private int ID;
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

    public static Player createPlayer(String name, Integer pilot, Integer fighter, Integer trader, Integer engineer, Difficulty diff) throws PlayerCreationException {
        if ((pilot + fighter + trader + engineer != skillPtCap) || (pilot < 0 || fighter < 0 || trader < 0 || engineer < 0)) {
            throw new PlayerCreationException("Invalid distribution of skill points!");
        }
        if (name == null || name.equals("")) {
            throw new PlayerCreationException("Name cannot be empty!");
        }
        return new Player(name, pilot, fighter, trader, engineer, diff);
    }

    public void buy(MarketGood marketGood, int quantity) throws PurchaseException {
        if (marketGood.getPrice() * quantity > this.Credits) {
            throw new PurchaseException("Not enough credits for this purchase!");
        }
        cargoHold.addGoods(marketGood.toString(), quantity);
        game.removeCurrentMarketPlaceQuantity(marketGood.toString(), quantity);
        Credits -= marketGood.getPrice() * quantity;
    }

    public void sell(MarketGood marketGood, int quantity) throws PurchaseException {
        cargoHold.removeGoods(marketGood.toString(), quantity);
        Credits += marketGood.getPrice() * quantity;
    }

    public void refuel() {
        while (Credits - 50 >= 0 && fuel < Ship_type.getMaxFuel()) {
            addFuel(1);
            subCredits(50);
        }
    }

    public CargoHold getCargoHold() {
        return cargoHold;
    }

    public void setCargoHold(CargoHold cargoHold) {
        this.cargoHold = cargoHold;
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

    public Difficulty getDiff() {
        return this.diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
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

    public MarketPlace getCurrentMarketPlace() {
        return game.getCurrentPlayerMarketPlace();
    }

    public Planet getCurrentPlanet() {
        return game.getCurrentPlayerPlanet();
    }

    public void setCurrentPlayerPlanet(SolarSystem s, Planet p) {
        game.setCurrentPlayerLocation(s, p);
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

    public void travelTo(SolarSystem s, Planet p, int turns) {
        game.setCurrentPlayerLocation(s, p);
        fuel -= turns;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
