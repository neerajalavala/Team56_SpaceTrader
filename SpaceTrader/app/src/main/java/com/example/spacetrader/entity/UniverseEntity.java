package com.example.spacetrader.entity;

import java.io.Serializable;

/**
 * Represents universe entity
 */
class UniverseEntity implements Serializable {

    public void setEntityID(int entityID) {
        EntityID = entityID;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int EntityID;

    private String name;

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

    public UniverseEntity() {}

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
