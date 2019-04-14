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

    public abstract Universe getPlayerGame();
}
