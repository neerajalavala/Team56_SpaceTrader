package com.example.spacetrader.model;

import com.example.spacetrader.entity.gamelogic.Player;

/**
 * Provide the operations associated with Player Entity
 */
public class PlayerInteractor extends Interactor {

    public PlayerInteractor(PlayerRepository repo) {
        super(repo);
    }

    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    public void newPlayer (Player p) {
        getRepository().newPlayer(p);
    }
}
