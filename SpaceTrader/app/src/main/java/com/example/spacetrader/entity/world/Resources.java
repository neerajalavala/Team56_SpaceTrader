package com.example.spacetrader.entity.world;

public enum Resources {
    NOSPECIALRESOURCES("No Special Resources", 0),
    MINERALRICH("Mineral Rich", 1),
    MINERALPOOR("Mineral Poor", 2),
    DESERT("Desert", 3),
    LOTSOFWATER("Lots of Water", 4),
    RICHSOIL("Rich Soil", 5),
    POORSOIL("Poor Soil", 6),
    RICHFAUNA("Rich Fauna", 7),
    LIFELESS("Lifeless", 8),
    WEIRDMUSHROOMS("Weird Mushrooms", 9),
    LOTSOFHERBS("Lots of Herbs", 10),
    ARTISTIC("Artistic", 11),
    WARLIKE("Warlike", 12),
    NONE("None", 13);

    private String name;
    private int index;

    private Resources(String s, int i){
        this.name = s;
        this.index = i;
    }

    public int index() {
        if (this == null) {
            return -1;
        }
        return index;
    }

    public String toString(){
        return this.name;
    }


}
