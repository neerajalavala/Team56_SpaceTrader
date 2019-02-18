package com.example.spacetrader.model;

import android.util.Log;

import java.util.List;

import com.example.spacetrader.entity.Player;

/**
 * Provide the operations associated with Player Entity
 */
public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public List<Player> getAllPlayers() {
        return getRepository().getAllPlayers();
    }

    public void addPlayer (Player p) {
        getRepository().addPlayer(p);
    }

    public void updatePlayer(Player player) {
        getRepository().updatePlayer(player);
        Log.d("APP", "Interactor: updating player: " + player);
    }


}
