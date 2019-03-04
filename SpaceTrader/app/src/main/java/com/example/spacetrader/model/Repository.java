package com.example.spacetrader.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.spacetrader.entity.gamelogic.Difficulty;
import com.example.spacetrader.entity.gamelogic.Player;

/**
 * This class is an abstraction of the data storage for the business classes
 * Normally this would passthrough to our ROOM (database) objects.   To keep this assignment
 * simple, we are just using in-memory storage
 */
class Repository {

    /***
     * This provides a mechanism to generate simple unique numbers to be used as
     * keys in the application
     */
    private static int next_id = 1;

    private static int getNextUniqueID() {
        return next_id++;
    }

    /***************************************************************/

    /** all the players known in the application */
    private List<Player> allPlayers;

    /**
     * Make a new Repository object
     */
    public Repository() {
        allPlayers = new ArrayList<>();
        loadDummyData();
    }

    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when persistence functionality is present.
     */
    private void loadDummyData() {
        addPlayer(new Player("Bob", 4,4,4, 4, Difficulty.BEGINNER));

    }

    /**
     * get all the players in teh system
     * @return list of all players
     */
    public List<Player> getAllPlayers() { return allPlayers;}



    /** add a new players to the system
     *
     * @param player the players to add
     */
    public void addPlayer(Player player) {
        player.setID(Repository.getNextUniqueID());
        allPlayers.add(player);
    }

    /**
     * Updates the values stored in a Player
     * @param p the player to update
     */
    public void updatePlayer(Player p) {
        for (Player player: allPlayers) {
            if (player.getID() == p.getID()) {
                player.setName(p.getName());
                player.setDiff(p.getDiff());
                return;
            }
        }
        Log.d("APP", "Player not found with id = " + p.getID());
    }
}
