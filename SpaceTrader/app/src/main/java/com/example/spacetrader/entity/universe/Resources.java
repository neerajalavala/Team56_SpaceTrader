package com.example.spacetrader.entity.universe;

public enum Resources {
    NOSPEC("No Special Resources"),
    MINRICH("Mineral Rich"),
    MINPOOR("Mineral Poor"),
    DSRT("Desert"),
    WATER("Lots of Water"),
    RSOIL("Rich Soil"),
    PSOIL("Poor Soil"),
    RFAUNA("Rich Fauna"),
    LFLSS("Lifeless"),
    WMUSH("Weird Mushrooms"),
    HERBS("Lots of Herbs"),
    ART("Artistic"),
    WAR("Warlike");

    private String label;
    Resources(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
