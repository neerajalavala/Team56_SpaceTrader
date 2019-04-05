package com.example.spacetrader.model;

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
}
