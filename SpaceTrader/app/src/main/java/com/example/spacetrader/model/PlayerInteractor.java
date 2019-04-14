package com.example.spacetrader.model;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;

/**
 * Provide the operations associated with Player Entity
 */
public class PlayerInteractor extends Interactor {

    public PlayerInteractor(PlayerRepository repo) {
        super(repo);
    }

    @Override
    public Universe getPlayerGame() {
        return getRepository().getPlayerGame();
    }

    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    public void newPlayer (Player p) {
        getRepository().newPlayer(p);
    }
}
