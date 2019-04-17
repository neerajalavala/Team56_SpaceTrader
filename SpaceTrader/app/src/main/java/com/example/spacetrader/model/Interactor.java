package com.example.spacetrader.model;

import com.example.spacetrader.entity.Universe;

/**
 * Interface for our concrete interactors
 */
abstract class Interactor {

    private final PlayerRepository myPlayerRepository;

    Interactor(PlayerRepository repo) {
        myPlayerRepository = repo;
    }

    PlayerRepository getRepository() {
        return myPlayerRepository;
    }

    /**
     *
     * @return universe to get from game
     */
    public abstract Universe getPlayerGame();
}
