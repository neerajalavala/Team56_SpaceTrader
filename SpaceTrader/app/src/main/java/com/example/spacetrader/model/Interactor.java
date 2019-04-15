package com.example.spacetrader.model;

import com.example.spacetrader.entity.Universe;

/**
 * Interface for our concrete interactors
 */
public abstract class Interactor {

    private PlayerRepository myPlayerRepository;

    protected Interactor(PlayerRepository repo) {
        myPlayerRepository = repo;
    }

    protected PlayerRepository getRepository() {
        return myPlayerRepository;
    }

    /**
     *
     * @return universe to get from game
     */
    public abstract Universe getPlayerGame();
}
