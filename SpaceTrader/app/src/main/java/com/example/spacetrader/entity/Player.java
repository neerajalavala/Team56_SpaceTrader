package com.example.spacetrader.entity;

import com.example.spacetrader.exception.PlayerCreationException;
import com.example.spacetrader.exception.PurchaseException;

import java.io.Serializable;


/**
 * Represents a player
 */
public class Player implements Serializable {
    /** counter for id */
    private static Integer player_ids = 1;
    /** total number of skill points */
    private static final Integer skillPtCap = 16;
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

    /**
     * Creates a player
     *
     * @param name name of player
     * @param pilot number of points in pilot skill
     * @param fighter number of points in fighter skill
     * @param trader number of points in trader skill
     * @param engineer number of points in engineer skill
     * @param diff chosen difficulty
     */
    public Player(String name, Integer pilot, Integer fighter, Integer trader, Integer engineer,
                  Difficulty diff) {
        /* a globally unique number for this object */
        int ID = player_ids;
        this.Name = name;

        this.Pilot = pilot;
        this.Fighter = fighter;
        this.Trader = trader;
        this.Engineer = engineer;

        this.diff = diff;

        /* starting number of credits */
        this.Credits = 1000;

        this.game = new Universe(player_ids);

        player_ids++;

        cargoHold = new CargoHold(Ship_type.getCapacity(), ID);
    }

    /**
     * Method to create a player
     *
     * @param name name of player
     * @param pilot points in pilot skill
     * @param fighter points in fighter skill
     * @param trader points in trader skill
     * @param engineer points in engineer skill
     * @param diff chosen difficulty
     * @return newly created player
     * @throws PlayerCreationException if skill points in invadlidly distributed
     */
    public static Player createPlayer(String name, Integer pilot, Integer fighter, Integer trader,
                                      Integer engineer,
                                      Difficulty diff) throws PlayerCreationException {
        if ((pilot + fighter + trader + engineer != skillPtCap) || (pilot < 0 ||
                fighter < 0 || trader < 0 || engineer < 0)) {
            throw new PlayerCreationException("Invalid distribution of skill points!");
        }
        if (name == null || name.equals("")) {
            throw new PlayerCreationException("Name cannot be empty!");
        }
        return new Player(name, pilot, fighter, trader, engineer, diff);
    }

    /**
     * Buys a marketgood
     *
     * @param marketGood marketgood that is bought
     * @param quantity quantity that is bought
     * @throws PurchaseException if player doesnt have enough credits
     */
    public void buy(MarketGood marketGood, int quantity) throws PurchaseException {
        if (marketGood.getPrice() * quantity > this.Credits) {
            throw new PurchaseException("Not enough credits for this purchase!");
        }
        cargoHold.addGoods(marketGood.toString(), quantity);
        game.removeCurrentMarketPlaceQuantity(marketGood.toString(), quantity);
        Credits -= marketGood.getPrice() * quantity;
    }

    /**
     * Sells a marketgood
     *
     * @param marketGood marketgood that is sold
     * @param quantity quantity that is sold
     * @throws PurchaseException if marketgood can't be removed from cargoHold
     */
    public void sell(MarketGood marketGood, int quantity) throws PurchaseException {
        cargoHold.removeGoods(marketGood.toString(), quantity);
        Credits += marketGood.getPrice() * quantity;
    }

    /**
     * Refuels player ship
     */
    public void refuel() {
        while (Credits - 50 >= 0 && fuel < Ship_type.getMaxFuel()) {
            addFuel(1);
            subCredits(50);
        }
    }

    /**
     *
     * @return player's cargohold
     */
    public CargoHold getCargoHold() {
        return cargoHold;
    }

    /**
     * Sets player's cargohold
     *
     * @param cargoHold cargohold to be set
     */
    public void setCargoHold(CargoHold cargoHold) {
        this.cargoHold = cargoHold;
    }

    /**
     * Adds credits to player credit amount
     *
     * @param q quantity to add
     * @return True if adding a valid amount greater than 0, else false
     */
    public boolean addCredits(int q) {
        if (q < 0) {
            return false;
        }
        this.Credits += q;
        return true;
    }

    /**
     * Subtracts credits to player credit amount
     *
     * @param q quantity to subtract
     * @return True if subtracting less than player current credit amount, else false
     */
    public boolean subCredits(int q) {
        if (this.Credits - q < 0) {
            return false;
        }
        this.Credits -= q;
        return true;
    }

    /**
     *
     * @return pilot points of player
     */
    public Integer getPilot() {
        return Pilot;
    }

    /**
     *
     * @return fighter points of player
     */
    public Integer getFighter() {
        return Fighter;
    }

    /**
     *
     * @return trader points of player
     */
    public Integer getTrader() {
        return Trader;
    }

    /**
     *
     * @return engineer points of player
     */
    public Integer getEngineer() {
        return Engineer;
    }

    /**
     *
     * @return player's current difficulty
     */
    public Difficulty getDiff() {
        return this.diff;
    }

    /**
     *
     * @param diff difficulty level of player
     */
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

    /**
     *
     * @return gets player's game
     */
    public Universe getGame() {
        return game;
    }

    /**
     *
     * @param game game to set for player
     */
    public void setGame(Universe game) {
        this.game = game;
    }

    /**
     *
     * @return player's current marketplace
     */
    public MarketPlace getCurrentMarketPlace() {
        return game.getCurrentPlayerMarketPlace();
    }

    /**
     *
     * @return player's current planet
     */
    public Planet getCurrentPlanet() {
        return game.getCurrentPlayerPlanet();
    }

    /**
     * Sets planet of player
     *
     * @param s solar sytem of planet
     * @param p planet to set
     */
    public void setCurrentPlayerPlanet(SolarSystem s, Planet p) {
        game.setCurrentPlayerLocation(s, p);
    }

    /**
     *
     * @return gets fuel of player
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * Sets the player's fuel to an amount
     *
     * @param q amount to set fuel to
     */
    public void setFuel(int q) {
        this.fuel = q;
    }

    /**
     * Adds fuel to player
     *
     * @param q amount to add
     */
    public void addFuel(int q) {
        if (q < 0 || fuel + q > Ship_type.getMaxFuel()) {
            return;
        }
        fuel += q;
    }

    /**
     * Subtracts fuel from player
     *
     * @param q amount to subtract
     */
    public void subFuel(int q) {
        if (q < 0 || fuel - q < 0) {
            return;
        }
        fuel -= q;
    }

    /**
     *
     * @return max amount of fuel for player
     */
    public int getShipMaxFuel() {
        return Ship_type.getMaxFuel();
    }

    /**
     * Travels to planet of a solar system
     *
     * @param s solarsytem to travel to
     * @param p planet to travel to
     * @param turns number of turns passed
     */
    public void travelTo(SolarSystem s, Planet p, int turns) {
        game.setCurrentPlayerLocation(s, p);
        fuel -= turns;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
