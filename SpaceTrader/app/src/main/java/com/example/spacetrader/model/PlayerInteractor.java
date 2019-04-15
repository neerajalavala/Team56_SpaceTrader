package com.example.spacetrader.model;

import com.example.spacetrader.entity.Player;
import com.example.spacetrader.entity.Universe;

/**
 * Provide the operations associated with Player Entity
 */
public class PlayerInteractor extends Interactor {

    /**
     *
     * @param repo repository of player
     */
    public PlayerInteractor(PlayerRepository repo) {
        super(repo);
    }

    @Override
    public Universe getPlayerGame() {
        return getRepository().getPlayerGame();
    }

    /**
     *
     * @return player of repository
     */
    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    /**
     *
     * @param p new player of repository
     */
    public void newPlayer (Player p) {
        getRepository().newPlayer(p);
    }
}
