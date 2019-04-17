package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * Represents universe entity
 */
class UniverseEntity implements Serializable {

    private final int EntityID;

    private final String name;

    /**
     * Creates universe entity
     *
     * @param id universe id
     * @param name universe name
     */
    UniverseEntity(int id, String name){
        this.EntityID = id;
        this.name = name;
    }

    /**
     *
     * @return name of universe
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return entity id
     */
    public int getEntityID() {
        return EntityID;
    }
}
