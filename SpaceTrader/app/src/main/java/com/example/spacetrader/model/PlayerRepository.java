package com.example.spacetrader.model;

import com.example.spacetrader.entity.Difficulty;
import com.example.spacetrader.entity.Player;

/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would passthrough to our ROOM (database) objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
class PlayerRepository {

    private Player player;

    /**
     * Make a new PlayerRepository object
     */
    public PlayerRepository() {
        loadDummyData();
    }

    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when persistence functionality is present.
     */
    private void loadDummyData() {
        newPlayer(new Player("Bob", 4,4,4, 4, Difficulty.BEGINNER));
    }

    /**
     * get all the players in teh system
     * @return list of all players
     */
    public Player getPlayer() {
        return player;
    }

    /** create a new player
     *
     * @param player the players to add
     */
    public void newPlayer(Player player) {
        this.player = player;
    }
}
