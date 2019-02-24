package com.example.spacetrader.entity;

public enum TechLevel {
    PreAgriculture("Pre-agriculture"),
    Agriculture("Agriculture"),
    Medieval("Medieval"),
    Renaissance("Renaissance"),
    EarlyIndustrial("Early-Industrial"),
    Industrial("Industrial"),
    PostIndustrial("Post-Industrial"),
    HiTech("Hi-Tech");

    private String name;

    private TechLevel(String s){
        this.name = s;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
