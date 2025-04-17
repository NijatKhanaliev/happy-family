package com.happyfamily.enums;

public enum Species {
    CAT(false,4,true),
    DOG(false,4,false),
    SNAKE(false,0,true),
    CROW(true,2,false),
    UNKNOWN(false,null,false);

    private final boolean canFly;
    private final Integer numberOfLegs;
    private final boolean hasFur;

    Species(boolean canFly, Integer numberOfLegs, boolean hasFur) {
        this.canFly = canFly;
        this.numberOfLegs = numberOfLegs;
        this.hasFur = hasFur;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public Integer getNumberOfLegs() {
        return numberOfLegs;
    }

    public boolean isHasFur() {
        return hasFur;
    }
}
