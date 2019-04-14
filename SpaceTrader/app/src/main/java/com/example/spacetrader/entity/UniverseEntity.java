package com.example.spacetrader.entity;

import java.io.Serializable;

public class UniverseEntity implements Serializable {

    private final int EntityID;

    private String name;

    public UniverseEntity(int id, String name){
        this.EntityID = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEntityID() {
        return EntityID;
    }
}
